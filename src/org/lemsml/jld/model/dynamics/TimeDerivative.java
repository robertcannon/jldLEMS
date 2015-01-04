package org.lemsml.jld.model.dynamics;

public class TimeDerivative extends AbstractDynamicsElement {

	
	protected String variable;
	protected String value;

	
	public TimeDerivative(Dynamics d, String n) {
		super(d);
		variable = n;
 	}


	public void setVariable(String s) {
		variable = s;
	}
	
	public void setValue(String s) {
		value = s;
	} 

	public String getValue() {
		 return value;
	}

	public String getVariable() {
		 return variable;
	}
}
