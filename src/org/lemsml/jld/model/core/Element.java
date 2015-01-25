package org.lemsml.jld.model.core;



public class Element {
 
	protected String name;
	
	protected Element(String name) {
		this.name = name;
		 
	}
	
	
	public String getName() {
		return name;
	}
	
	
	protected void checkFocus() throws ModelException {
	 
	}

}
