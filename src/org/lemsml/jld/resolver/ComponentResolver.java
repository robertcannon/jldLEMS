package org.lemsml.jld.resolver;

import org.lemsml.jld.model.Component;
import org.lemsml.jld.model.Lems;

public class ComponentResolver {

	private Lems lems;
	private Component targetComponent;
	
	
	public ComponentResolver(Lems lems, Component cpt) {
		this.lems = lems;
		this.targetComponent = cpt;
	}
	
	public void resolve() {
		
	}

}
