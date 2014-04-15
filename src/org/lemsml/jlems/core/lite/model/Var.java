package org.lemsml.jlems.core.lite.model;

public class Var {
 

	public String name;
	public String value;
	
	 
	public String p_rp;
	
	public Var(String vnm, String expr) {
		name = vnm;
		value = expr;
	}
	 

	public Var() {
		// TODO Auto-generated constructor stub
	}


	public String getVariableName() {
		return name;
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
