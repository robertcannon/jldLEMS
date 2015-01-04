package org.lemsml.jld.model.type;

import org.lemsml.jld.model.core.AbstractTypeElement;


public class Children extends AbstractTypeElement {
 	
	protected Children(ComponentType ct, String s) {
		super(ct, s);
 	}
  
	public String toString() {
		return "Children, type=" + getTargetTypeName() + ", parent=" + getParentTypeName();
	}
 
}
