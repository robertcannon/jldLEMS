package org.lemsml.jld.model.dynamics;

import org.lemsml.jld.model.core.ListMap;

public class OnCondition extends AbstractDynamicsBlock {

	
	protected String test;
	

	
	
	public OnCondition(Dynamics d) {
		super(d);
	}
	
	public void setTest(String s) {
		test = s;
	}
	
	public String getTest() {
		return test;
	}
 

}
