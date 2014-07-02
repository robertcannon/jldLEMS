package org.lemsml.jlems.core.lite.model;


public class IfCondition extends OnAbstract {
 
	
	public String test;
	
	public IfCondition() {
		 super();
	}

	
	public IfCondition(String estr) {
		super();
		test = estr;
	}


	public String getExpression() {
		return test;
	}
	
}

