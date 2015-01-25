package org.lemsml.jld.model.type;

import org.lemsml.jld.model.core.TargetTypeElement;
import org.lemsml.jld.model.core.TypeElement;


public class Children extends TargetTypeElement {
 	
	protected Children(ComponentType ct, String s) {
		super(ct, s);
 	}
  
	public String toString() {
		return "Children, type=" + getTargetTypeName() + ", parent=" + getParentTypeName();
	}
 
}
