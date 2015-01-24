package org.lemsml.jld.model;

import org.lemsml.jld.model.core.AbstractElement;
 

public class Target extends AbstractElement {

	protected String component;
	
	private Component r_component;
	
	protected Target(String s) {
		super(s);
	}
	
	public void setComponent(String s) {
		component = s;
	}

	public String getComponent() {
		return component;
	}
	
	public void setComponent(Component cpt) {
		r_component = cpt;
	}
	

	public Component getComponentObject() {
		return r_component;
	}

}
