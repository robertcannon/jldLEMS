package org.lemsml.jlems.core.dimensionless;

public class Var {
 

	public String variableName;
	public String expression;
	public String rpExpression;
	
	public Var(String vnm, String expr) {
		variableName = vnm;
		expression = expr;
	}
	
	
	
	public Var() {
		 
	}



	public String getVariableName() {
		return variableName;
	}
	
	public String getExpression() {
		return expression;
	}
	
	public void setReversePolishExpression(String s) {
		rpExpression = s;
	}
	
	public String getReversePolishExpression() {
		return rpExpression;
	}
	
	
}
