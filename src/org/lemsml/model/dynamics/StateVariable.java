package org.lemsml.model.dynamics;

public class StateVariable extends AbstractDynamicsElement {

	protected String name;
	protected String exposure;
	protected String dimension;
	
	
	
	public StateVariable(Dynamics d, String n) {
		super(d);
		name = n;
	}



	public void setExposure(String s) {
		exposure = s;
	}
	
	public void setDimension(String s) {
		dimension = s;
	}



	public String getName() {
		return name;
	}



	public String getExposure() {
		return exposure;
	}



	public String getDimension() {
		return dimension;
	}

	
	
	
	
	
}
