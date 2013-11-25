package org.lemsml.jlems.core.dimensionless;

import java.util.ArrayList;

import org.lemsml.jlems.core.logging.E;

public class OnCondition {

	public String port;
	
	public String x_if;
	
	public ArrayList<FloatAssignment> floatAssignments = new ArrayList<FloatAssignment>();
	
	public ArrayList<Emit> emits = new ArrayList<Emit>();
	
	public OnCondition(String pn) {
		port = pn;
	}
	
	
	public OnCondition() {
		 
	}


	public void addFloatAssignment(FloatAssignment fa) {
		floatAssignments.add(fa);
	}


	public ArrayList<FloatAssignment> getFloatAssignments() {
		return floatAssignments;
	}
	
	public String getPortName() {
 		return port;
	}

 

	public void addUpdateAssignment(FloatAssignment fa) {
		floatAssignments.add(fa);
	}
	
	

	public void addSend(String s) {
		emits.add(new Emit(s));
	}

	
	public void addEmit(String p) {
		Emit e = new Emit(p);
		emits.add(e);
	}
}
