package org.lemsml.jld.model.core;

import org.lemsml.jld.model.type.ComponentType;


public abstract class AbstractTypeElement extends AbstractElement {
 
	protected String type;
	private ComponentType parentType;
	
	private ComponentType targetType;
	
	protected AbstractTypeElement(ComponentType ct, String s) {
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

	public void setTargetType(ComponentType ct) {
		targetType = ct;
	}
	
	public String getTargetTypeName() {
		String ret = "null";
		if (targetType != null) {
			ret = targetType.getName();
		}
		return ret;
	}
	
	
	public String getParentTypeName() {
		String ret = "null";
		if (parentType != null) {
			ret = parentType.getName();
		}
		return ret;
	}
	
}
