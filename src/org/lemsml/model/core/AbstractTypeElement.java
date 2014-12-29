package org.lemsml.model.core;

import org.lemsml.model.type.ComponentType;


public class AbstractTypeElement extends AbstractElement {
 
	private ComponentType componentType;
	
	protected AbstractTypeElement(ComponentType ct, String s) {
		super(s);
		componentType = ct;
		
		 
	}
	
	  

}
