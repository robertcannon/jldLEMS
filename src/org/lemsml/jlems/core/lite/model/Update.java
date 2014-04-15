package org.lemsml.jlems.core.lite.model;

public class Update {

	
	 
	public String variable;
	public String value;
	 
	public String p_rp;
	
	public Update(String vnm, String expr) {
		variable = vnm;
		value = expr;
	}
	
	 


	public Update() {
		// called from generated factory on input
	}




	public String getVariableName() {
		return variable;
	}
	
	public String getExpression() {
		return value;
	}
	
	public void setReversePolishExpression(String s) {
		p_rp = s;
	}
	
	public String getReversePolishExpression() {
		return p_rp;
	}
	
	
}
