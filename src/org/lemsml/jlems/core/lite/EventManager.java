package org.lemsml.jlems.core.lite;

import java.util.ArrayList;

import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.run.RuntimeError;

public class EventManager {

	
	double time;
	double timestep = 0.;
	
	double maxDelay = 0.;
	
	int nbin;
	int ibin = 0;
	
	ConnectionArray[] bins;
	
	ConnectionTable ct;
	
	
	public EventManager(ConnectionTable connectionTable) {
		ct = connectionTable;
	}

	
	public void setTimestep(double dt) {
		timestep = dt;
	}

	public void setMaxDelay(double d) {
		maxDelay = d;
	}
	
	
	private void createBins() throws RuntimeError {
		if (maxDelay <= 0 || timestep <= 0) {
			throw new RuntimeError("Must set both max delay and timestep in event manager");
		}
		nbin = (int)Math.ceil(maxDelay / timestep);
		bins = new ConnectionArray[nbin];
		E.info("Created bins " + nbin);
	}
	
	
	


	public void addEvents(ArrayList<Connection> cs) throws RuntimeError {	
		if (bins == null) {
			createBins();
		}
		
		for (Connection c : cs) {
			 double d = c.getDelay();
			 if (d > maxDelay) {
				 throw new RuntimeError("Delay exceeds specified max: " + d + " > " + maxDelay);
			 }
			 int ns = (int)Math.round(d / timestep);
			 
			 int ind = (ibin + ns) % nbin;
			 if (bins[ind] == null) {
				 bins[ind] = new ConnectionArray();
			 }
			 bins[ind].add(c);
		}	
	}

	
	public void advance(double t, double dt) throws RuntimeError {
		if (bins == null) {
			createBins();
		}
		
		if (bins != null && bins[ibin] != null) {
			ConnectionArray ca = bins[ibin];
			if (ca.size() > 0) {
				for (Connection c : ca.getConnections()) {
					c.deliver();
				}
				ca.clear();
			}
		}
		ibin += 1;
		if (ibin == nbin) { 
			ibin = 0;
		}
 	}
	
}
