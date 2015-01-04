package org.lemsml.jld.model.dynamics;

import org.lemsml.jld.model.Dimension;

public class StateVariable extends AbstractDynamicsElement {

	protected String name;
	protected String exposure;
	protected String dimension;
	
	private Dimension r_dimension;
	
	
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
	
	public void setDimension(Dimension d) {
		r_dimension = d;
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

	public Dimension getDimensionObject() {
		return r_dimension;
	}
	
	
	
	
}
