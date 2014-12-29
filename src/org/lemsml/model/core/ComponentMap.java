package org.lemsml.model.core;

import java.util.ArrayList;
import java.util.List;

import org.lemsml.model.Component;

public class ComponentMap {

		
	ArrayList<Component> components = new ArrayList<Component>();
	
	
	public void put(String id, Component ret) {
		components.add(ret);
		
	}

	public List<Component> getComponents() {
		return components;
	}

}
