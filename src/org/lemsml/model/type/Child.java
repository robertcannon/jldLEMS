package org.lemsml.model.type;

import org.lemsml.model.core.AbstractTypeElement;


public class Child extends AbstractTypeElement {

	protected String type;
	
	protected Child(ComponentType ct, String s) {
		super(ct, s);	 
	}
	
	public void setType(String s) {
		type = s;
	}

	public String getType() {
		return type;
	}

 
}
