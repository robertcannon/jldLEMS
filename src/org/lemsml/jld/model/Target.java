package org.lemsml.jld.model;

import org.lemsml.jld.model.core.Element;
 

public class Target extends Element {

	protected String component;
	
	private Component r_component;
	
	protected Target(String name) {
		super(name);
	}
	
	public void setComponent(String component) {
		this.component = component;
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
