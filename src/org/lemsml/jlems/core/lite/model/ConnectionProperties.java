package org.lemsml.jlems.core.lite.model;

import org.lemsml.jlems.core.type.LemsCollection;

public class ConnectionProperties {

	
	public LemsCollection<Property> propertys = new LemsCollection<Property>();
	
	public LemsCollection<Delay> delays = new LemsCollection<Delay>();

	public Delay getDelay() {
		Delay ret = null;
		if (delays.size() > 0) {
			ret = delays.get(0);
		}
		return ret;
	}
	
	public LemsCollection<Property> getProperties() {
		return propertys;
	}
	
}
