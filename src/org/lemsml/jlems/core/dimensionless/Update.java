package org.lemsml.jlems.core.dimensionless;

public class Update {

	
	 
	public String variable;
	public String value;
	 
	public String rpExpression;
	
	public Update(String vnm, String expr) {
		variable = vnm;
		value = expr;
	}
	
	
	
	public Update() {
		 
	}



	public String getVariableName() {
		return variable;
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
