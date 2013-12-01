package org.lemsml.jlems.core.dimensionless;

public class Var {
 

	public String name;
	public String value;
	
	// public String expression;
	public String rpExpression;
	
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
		rpExpression = s;
	}
	
	public String getReversePolishExpression() {
		return rpExpression;
	}
	
	
}
