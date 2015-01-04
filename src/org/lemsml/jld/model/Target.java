package org.lemsml.jld.model;

import org.lemsml.jld.model.core.AbstractLemsElement;

public class Target extends AbstractLemsElement {

	protected String component;
	
	protected Target(Lems l, String s) {
		super(l, s);
	}
	
	public void setComponent(String s) {
		component = s;
	}

	public String getComponent() {
		return component;
	}

}
