package org.lemsml.jld.model.core;

import org.lemsml.jld.model.Lems;


public class AbstractLemsElement extends AbstractElement {
 
	private Lems lems;
	
	protected AbstractLemsElement(Lems l, String s) {
		super(s);
		lems = l;
		 
	}
	 

}
