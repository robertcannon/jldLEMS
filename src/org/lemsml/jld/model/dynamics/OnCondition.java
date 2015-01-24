package org.lemsml.jld.model.dynamics;


public class OnCondition extends AbstractDynamicsBlock {

	
	protected String test;
	

	
	
	public OnCondition() {
		super();
	}
	
	public void setTest(String s) {
		test = s;
	}
	
	public String getTest() {
		return test;
	}
 

}
