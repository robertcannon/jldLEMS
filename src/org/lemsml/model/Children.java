package org.lemsml.model;


public class Children extends AbstractElement {

	
	protected String type;
	
	private ComponentType componentType;
	
	
	protected Children(Lems lm, String s) {
		super(lm, s);
	}

	public void setComponentType(String s) {
		type = s;
	}
	
	public void setComponentType(ComponentType ct) {
		componentType = ct;
	}
	
	public void setType(String s) {
		type = s;
	}
	
}
