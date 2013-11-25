package org.lemsml.jlems.core.lite.model;

import org.lemsml.jlems.core.type.LemsCollection;

public class ComponentArray {

	public String name;
	
	public String component;
	
	public int size;
	
	public LemsCollection<Let> lets = new LemsCollection<Let>();
	
	
	public LemsCollection<Initialize> initializes = new LemsCollection<Initialize>();


	public String getComponentName() {
		return component;
	}


	public int getComponentCount() {
		return size;
	}


	public String getName() {
		return name;
	}


	public LemsCollection<Let> getLets() {
		return lets;
	}


	public LemsCollection<Initialize> getInitializes() {
		return initializes;
	}
	
}
