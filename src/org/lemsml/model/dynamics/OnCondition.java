package org.lemsml.model.dynamics;

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
