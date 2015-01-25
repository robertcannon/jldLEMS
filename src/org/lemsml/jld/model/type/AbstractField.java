package org.lemsml.jld.model.type;

import org.lemsml.jld.model.Dimension;
import org.lemsml.jld.model.core.TypeElement;


public abstract class AbstractField extends TypeElement {
	
	protected String dimension = null;
	
	private Dimension r_dimension = null;
	
	
	protected AbstractField(ComponentType ct, String n) {
		super(ct, n);
	}
	
	public void setDimension(Dimension d) {
		r_dimension = d;
	}
	
	public Dimension getDimensionObject() {
 		return r_dimension;
	}
	
	
	public void setDimension(String n) {
		dimension = n;
	}

	public String getDimension() {
		return dimension;
	}
	
}
