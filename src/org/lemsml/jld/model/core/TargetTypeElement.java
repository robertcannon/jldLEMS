package org.lemsml.jld.model.core;

import org.lemsml.jld.model.type.ComponentType;

public abstract class TargetTypeElement extends TypeElement {


	private ComponentType targetType;
	
	protected TargetTypeElement(ComponentType ct, String s) {
		super(ct, s);
	}
	
	
	public void setTargetType(ComponentType ct) {
		targetType = ct;
	} 
	
	public ComponentType getTargetType() {
		return targetType;
	}
	
	
	
	public String getTargetTypeName() {
		String ret = "null";
		if (targetType != null) {
			ret = targetType.getName();
		}
		return ret;
	}

}
