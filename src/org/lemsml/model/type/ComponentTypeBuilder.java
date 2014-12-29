package org.lemsml.model.type;

import org.lemsml.model.Lems;

public class ComponentTypeBuilder {

	public ComponentType newComponentType(Lems lems, String s) {
		ComponentType ret=  new ComponentType(lems, s);
		return ret;
	}

}
