package org.lemsml.jlems.core.lite.run.component;

public class FloatAssignment {

	public String variableName;
	public String expression;
	public String rpExpression;
	
	public FloatAssignment(String vnm, String expr) {
		variableName = vnm;
		expression = expr;
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
