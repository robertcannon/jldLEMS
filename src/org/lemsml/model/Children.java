package org.lemsml.model;


public class Children extends AbstractElement {

	
	private String typeName;
	protected ComponentType componentType;
	
	protected Children(Lems lm, String s) {
		super(lm, s);
	}

	public void setComponentType(String s) {
		typeName = s;
	}
	
	public void setComponentType(ComponentType ct) {
		componentType = ct;
	}
	
}
