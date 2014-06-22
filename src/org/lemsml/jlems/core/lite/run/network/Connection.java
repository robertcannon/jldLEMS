package org.lemsml.jlems.core.lite.run.network;

import java.util.HashMap;

import org.lemsml.jlems.core.lite.run.component.Assignment;
import org.lemsml.jlems.core.lite.run.component.DiscreteUpdateStateInstance;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.run.DoublePointer;
import org.lemsml.jlems.core.run.RuntimeError;

public class Connection {
	
	DiscreteUpdateStateInstance src;
	DiscreteUpdateStateInstance dest;
	
	double delay = 0.;
	double weight = 0.;
	
	String tgtPort;
	
	EventBuilder eventBuilder;
	
	HashMap<String, DoublePointer> myVars = new HashMap<String, DoublePointer>();
	 
	
	public Connection(DiscreteUpdateStateInstance tgtDUSI, String pto) {
		dest = tgtDUSI;
		tgtPort = pto;
	}

	public void setEventBuilder(EventBuilder eb) {
		eventBuilder = eb;
	}

	public double getDelay() {
		return delay;
	}
 

	public void setDelay(double d) {
		 delay = d;
		 setVar("connection.delay", d);
	}
	
	
	public void setWeight(double d) {
		weight = d;
		setVar("connection.weight", d);
 	}
		
	
	private void setVar(String nm, double d) {
		 if (myVars.containsKey(nm)) {
			 myVars.get(nm).set(d);
		 } else {
			 myVars.put(nm, new DoublePointer(d));
		 }
	}
	
	
	public void setCustomProperty(String pnm, double v) {
		E.missing();
	}


	public void deliver(double t) throws RuntimeError {
		
		// HashMap<String, DoublePointer> vars = src.getVariables();
		
		
		if (eventBuilder != null) {
			for (Assignment ass : eventBuilder.getAssignments()) {
				// TODO, may need to evaluate some source variables too, at the tome the event is created
				String vnm = ass.getVariableName();
				double val = ass.evaluate(myVars);
				
				dest.setVariableMaybeNew(vnm, val);
				E.info("Set a variable in receiver cpt " + vnm + " " + val);
			}
		} 
		dest.setVariableMaybeNew("t", t);
		dest.receiveEvent(tgtPort);
	}

 
 
}
