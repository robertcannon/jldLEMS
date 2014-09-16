package org.lemsml.jlems.core.lite.run.network;

import org.lemsml.jlems.core.display.DataViewer;
import org.lemsml.jlems.core.lite.run.component.DiscreteUpdateStateInstance;
import org.lemsml.jlems.core.lite.run.component.DiscreteUpdateStateType;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.run.ConnectionError;
import org.lemsml.jlems.core.run.RuntimeError;
import org.lemsml.jlems.core.sim.ContentError;

public class InputEvents {
 
	
	String name;
	
	double[] times;
	int[] targetIndices;
	
	double tnext;
	boolean finished = true;
	
	double weight;
	double[] weights;
	
	int position;
	
	EventBuilder eventBuilder;
	
	String targetPort;
	InstanceArray targets;
	
	public InputEvents(String sn) {
		 name = sn;
	}
	
	public String toString() {
		return name + " (" + times.length + ")";
	}
	 
	
	public void setTargetArray(InstanceArray a) {
		targets = a;
	}
	
	public void setTargetPort(String s) {
		targetPort = s;
	}
	
	
	public void setTimes(double[] d) {
		times = d;
		if (times.length > 0) {
			tnext = times[0];
			position = 0;
			finished = false;
		}
	}
	
	public void setTargetIndices(int[] itgt) {
		targetIndices = itgt;
	}

 
	 
	
	public void advance(double t, double dt) throws RuntimeError, ContentError {
		if (finished) {
			return;
		}
		
		double tplus = t + dt;
 	
		if (tplus > tnext) {
			
			
			while (!finished && tplus > tnext) {
				
				int itgt = targetIndices[position];
				Connection conn = new Connection(targets.getElement(itgt), targetPort);
				if (eventBuilder != null) {
					conn.setEventBuilder(eventBuilder);
				}
				if (weights != null && position < weights.length) {
					double w = weights[position];
					conn.setWeight(w);
					E.info("Set indexed weight " + w);
				} else {
					conn.setWeight(weight);
					E.info("Set default weight " + weight);
				}
				
				E.info("Sending an event! ... " + itgt);
				conn.deliver(tplus);
			
				if (position < times.length - 1) {
					position += 1;
					tnext = times[position];
				} else {
					finished = true;
				}
			}
		}
	}

	public void setWeight(double v) {
		E.info("Set weight for input " + v);
		weight = v;
	}

	public void setEventBuilder(EventBuilder eb) {
		eventBuilder = eb;
	}

	public void setWeights(double[] values) {
		weights = values;
	}

	 


}
