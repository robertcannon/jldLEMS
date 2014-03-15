package org.lemsml.jlems.core.lite.model;

public class Update {

	
	 
	public String variable;
	public String value;
	 
	public String rp;
	
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
		rp = s;
	}
	
	public String getReversePolishExpression() {
		return rp;
	}
	
	
}
