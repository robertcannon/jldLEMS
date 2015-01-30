package org.lemsml.jld.hrun;

import org.lemsml.jld.display.LineDisplay;
import org.lemsml.jld.io.E;
 


// TODO this shouldn't implement StateRunnable 
public class StateWrapper {

	
	StateInstance unitInstance = null;
	RegimeStateInstance regimeInstance = null;
	
	protected String varname;

	
	public StateWrapper(String snm) {
		varname = snm;
	}
	
	
	public StateWrapper(StateInstance ui, String snm) {
		unitInstance = ui;
		varname = snm;
	}

	public StateWrapper(RegimeStateInstance ui, String snm) {
		regimeInstance = ui;
		varname = snm;
	}

	
	
	public StateRunnable getChild(String snm) {
		E.missing();
		return null;
	}

	
	public double getValue() throws RuntimeError {
		double ret = Double.NaN;
		if (unitInstance != null) {
 			ret = unitInstance.getVariable(varname);
			
		} else if (regimeInstance != null) {
			ret = regimeInstance.getVariable(varname);
		} else {
			throw new RuntimeError("State wrapper empty");
		}
		return ret;
	}

 
	public void advance(StateInstance parent, double t, double dt) {
		// nothing to do
	}
 
	public void exportState(String pfx, double t, LineDisplay ld) {
		E.missing();
	}
	
	
}
