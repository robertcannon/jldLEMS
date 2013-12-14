package org.lemsml.jlems.core.lite.model;

import java.util.ArrayList;

import org.lemsml.jlems.core.logging.E;

public class OnCondition extends OnAbstract {
 
	
	public String x_if;
	
	public OnCondition() {
		 
	}

	
	public OnCondition(String estr) {
		x_if = estr;
	}


	public String getExpression() {
		return x_if;
	}
	
}

