package org.lemsml.model.core;

import org.lemsml.model.Lems;


public class AbstractLemsElement extends AbstractElement {
 
	private Lems lems;
	
	protected AbstractLemsElement(Lems l, String s) {
		super(s);
		lems = l;
		 
	}
	 

}
