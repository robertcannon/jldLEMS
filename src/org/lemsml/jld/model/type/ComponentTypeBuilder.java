package org.lemsml.jld.model.type;

import org.lemsml.jld.model.Lems;

public class ComponentTypeBuilder {

	public ComponentType newComponentType(String s) {
		ComponentType ret=  new ComponentType(s);
		return ret;
	}

	public RootComponentType newRootType() {
		 RootComponentType ret = new RootComponentType();
		 return ret;
	}

}
