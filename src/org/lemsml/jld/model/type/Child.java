package org.lemsml.jld.model.type;

import org.lemsml.jld.model.core.TargetTypeElement;
 


public class Child extends TargetTypeElement {
 
	protected Child(ComponentType ct, String s) {
		super(ct, s);	 
	}
	
	public String toString() {
		return "Child, type=" + getTargetTypeName() + ", parent=" + getParentTypeName();
	}

	 
 
}
