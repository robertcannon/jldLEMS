package org.lemsml.jlems.core.lite.model;


public class OnCondition extends OnAbstract {
 
	
	public String test;
	
	public OnCondition() {
		 super();
	}

	
	public OnCondition(String estr) {
		super();
		test = estr;
	}


	public String getExpression() {
		return test;
	}
	
}

