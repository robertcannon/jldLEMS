package org.lemsml.model;

public class Target extends AbstractElement {

	protected String component;
	
	protected Target(Lems l, String s) {
		super(l, s);
	}
	
	public void setComponent(String s) {
		component = s;
	}
}
