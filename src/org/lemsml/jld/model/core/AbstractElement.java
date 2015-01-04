package org.lemsml.jld.model.core;



public class AbstractElement {
 
	protected String name;
	
	protected AbstractElement(String s) {
		name = s;
		 
	}
	
	 
	
	public String getName() {
		return name;
	}
	
	
	
	protected void checkFocus() throws ModelException {
	 
	}

}
