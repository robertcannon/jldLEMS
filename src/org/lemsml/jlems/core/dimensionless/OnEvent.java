package org.lemsml.jlems.core.dimensionless;

import java.util.ArrayList;

import org.lemsml.jlems.core.logging.E;

public class OnEvent {

	public String port;
	
	public ArrayList<FloatAssignment> floatAssignments = new ArrayList<FloatAssignment>();
	
	
	public OnEvent(String pn) {
		port = pn;
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


	public void addEmit(String attribute) {
		 E.missing();
	}


	public void addUpdateAssignment(FloatAssignment fa) {
		floatAssignments.add(fa);
	}
	
	
	
}
