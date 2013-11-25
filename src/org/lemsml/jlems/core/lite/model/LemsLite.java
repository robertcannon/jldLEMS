package org.lemsml.jlems.core.lite.model;

import java.util.HashMap;

import org.lemsml.jlems.core.type.LemsCollection;

public class LemsLite {

	public LemsCollection<DiscreteUpdateComponent> discreteUpdateComponents = new LemsCollection<DiscreteUpdateComponent>();

	public LemsCollection<DataSources> dataSourcess = new LemsCollection<DataSources>();
	
	public LemsCollection<ComponentArray> componentArrays = new LemsCollection<ComponentArray>();
	
	public LemsCollection<EventConnections> eventConnectionss = new LemsCollection<EventConnections>();

	public String getSummary() {
		// TODO Auto-generated method stub
		String ret= "(components=" + discreteUpdateComponents.size() +
				", dataSources=" + dataSourcess.size() + 
				", componentArrays=" + componentArrays.size() + 
				", eventConnections=" + eventConnectionss.size() + ")";
		return ret;
	}

	public LemsCollection<DataSources> getDataSourcess() {
		return dataSourcess;
	}

	
	public LemsCollection<ComponentArray> getComponentArrays() {
		return componentArrays;
	}
	
	
	public HashMap<String, DiscreteUpdateComponent> getComponentMap() {
		HashMap<String, DiscreteUpdateComponent> ret = new HashMap<String, DiscreteUpdateComponent>();
		
		for (DiscreteUpdateComponent duc : discreteUpdateComponents) {
			ret.put(duc.getName(), duc);
		}
		return ret;
	}

	public LemsCollection<EventConnections> getEventConnectionss() {
		return eventConnectionss;
	}
}
