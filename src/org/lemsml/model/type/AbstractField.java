package org.lemsml.model.type;

import org.lemsml.model.Dimension;
import org.lemsml.model.core.AbstractTypeElement;


public class AbstractField extends AbstractTypeElement {
	
	protected String dimension = null;
	
	private Dimension r_dimension = null;
	
	
	protected AbstractField(ComponentType ct, String n) {
		super(ct, n);
	}
	
	public void setDimension(Dimension d) {
		r_dimension = d;
	}
	
	public void setDimension(String n) {
		dimension = n;
	}

	public String getDimension() {
		return dimension;
	}
	
}
