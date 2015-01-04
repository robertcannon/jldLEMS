package org.lemsml.jld.model.type;

import org.lemsml.jld.model.Dimension;
import org.lemsml.jld.model.Lems;


public class Parameter extends AbstractField {
	
	
	protected Parameter(ComponentType ct, String n) {
		super(ct, n);
	}
 
	public String toString() {
		return "Parameter, name=" + getName();
	}

	
}
