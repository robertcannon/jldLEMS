package org.lemsml.jld.model.type;



public class Parameter extends AbstractField {
	
	
	protected Parameter(ComponentType ct, String n) {
		super(ct, n);
	}
 
	public String toString() {
		return "Parameter, name=" + getName();
	}

	
}
