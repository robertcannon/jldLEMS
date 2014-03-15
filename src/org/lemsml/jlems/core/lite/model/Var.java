package org.lemsml.jlems.core.lite.model;

public class Var {
 

	public String name;
	public String value;
	
	// public String expression;
	public String rp;
	
	public Var(String vnm, String expr) {
		name = vnm;
		value = expr;
	}
	
	
	
	public Var() {
		 
	}



	public String getVariableName() {
		return name;
	}
	
	public String getExpression() {
		return value;
	}
	
	public void setReversePolishExpression(String s) {
		rp = s;
	}
	
	public String getReversePolishExpression() {
		return rp;
	}
	
	
}
