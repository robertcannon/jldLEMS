package org.lemsml.jlems.core.lite.model;


public class OnCondition extends OnAbstract {
 
	
	public String x_if;
	
	public OnCondition() {
		 super();
	}

	
	public OnCondition(String estr) {
		super();
		x_if = estr;
	}


	public String getExpression() {
		return x_if;
	}
	
}

