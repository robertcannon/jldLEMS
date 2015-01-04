package org.lemsml.jld.model.type;

import org.lemsml.jld.model.Lems;

public class ComponentTypeBuilder {

	public ComponentType newComponentType(Lems lems, String s) {
		ComponentType ret=  new ComponentType(lems, s);
		return ret;
	}

	public RootComponentType newRootType(Lems lems) {
		 RootComponentType ret = new RootComponentType(lems);
		 return ret;
	}

}
