package org.lemsml.jld.model.dynamics;

import org.lemsml.jld.imodel.dynamics.IStateVariable;
import org.lemsml.jld.model.Dimension;
import org.lemsml.jld.model.type.Exposure;

public class StateVariable extends AbstractDynamicsElement implements IStateVariable {

	protected String name;
	protected String exposure;
	protected String dimension;
	
	private Dimension r_dimension;
	
	private Exposure r_exposure;
	
	
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



	public void setExposure(Exposure e) {
		r_exposure = e;
	}
	
	public Exposure getExposureObject() {
		return r_exposure;
	}
	
	
	
}
