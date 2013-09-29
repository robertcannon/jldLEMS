package org.lemsml.jlems.core.dimensionless;

import java.util.ArrayList;

public class OnState {
 	
	String condition;
	
	public ArrayList<FloatAssignment> floatAssignments = new ArrayList<FloatAssignment>();
	
	public ArrayList<Emit> emits = new ArrayList<Emit>();
	
	public OnState(String estr) {
		condition = estr;
	}
	
	
	public void addFloatAssignment(FloatAssignment fa) {
		floatAssignments.add(fa);
	}


	public ArrayList<FloatAssignment> getFloatAssignments() {
		return floatAssignments;
	}


	public void addSend(String s) {
		emits.add(new Emit(s));
	}


	public String getCondition() {
		 return condition;
	}
	
	 
	
	public ArrayList<Emit> getEmits() {
		return emits;
	}


	public void setCondition(String c) {
		condition = c;
	}
	
	 
	public void addUpdateAssignment(FloatAssignment fa) {
		floatAssignments.add(fa);
	}


	public void addEmit(String p) {
		Emit e = new Emit(p);
		emits.add(e);
	}
	
}
