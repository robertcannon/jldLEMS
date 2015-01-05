package org.lemsml.jld.model.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lemsml.jld.model.Component;

public class ComponentMap {

		
	ArrayList<Component> components = new ArrayList<Component>();
	HashMap<String, Component> componentHM = new HashMap<String, Component>();
	
	public void put(String id, Component ret) {
		components.add(ret);
		componentHM.put(id,  ret);
	}

	public List<Component> getComponents() {
		return components;
	}

	public Component get(String ext) {
	 		Component ret = null;
		if (componentHM.containsKey(ext)) {
			ret = componentHM.get(ext);
		}
		return ret;
	}

}
