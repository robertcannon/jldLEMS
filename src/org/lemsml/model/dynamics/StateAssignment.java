package org.lemsml.model.dynamics;

public class StateAssignment extends AbstractDynamicsBlockElement {

	protected String value;
	protected String variable;
	
	public StateAssignment(AbstractDynamicsBlock b, String n) {
			super(b, n);
	}

	
	public void setVariable(String s) {
		variable = s;
	}
	
	public String getVariable() {
		return variable;
	}
	 
	
	public void setValue(String s) {
		value = s;
	}
	
	public String getValue() {
		return value;
	}
	
}
