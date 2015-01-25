package org.lemsml.jld.model.core;

import org.lemsml.jld.model.type.ComponentType;


public abstract class TypeElement extends Element {
 
	protected String type;
	
	private ComponentType parentType;
	 
	
	protected TypeElement(ComponentType ct, String s) {
		super(s);
		parentType = ct;
		
		 
	}

	public abstract String toString();
	
	
	public void setType(String s) {
		type = s;
	}

	public String getType() {
		return type;
	}


	
	
	public String getParentTypeName() {
		String ret = "null";
		if (parentType != null) {
			ret = parentType.getName();
		}
		return ret;
	}
	
}
