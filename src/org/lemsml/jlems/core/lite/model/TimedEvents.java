package org.lemsml.jlems.core.lite.model;

import org.lemsml.jlems.core.type.LemsCollection;

public class TimedEvents {

	public String name;
 
	public String to;

	public String times;
	public String targets;
	 
	public LemsCollection<EventTarget> eventTargets = new LemsCollection<EventTarget>();
	

	
	public LemsCollection<ConnectionProperties> connectionPropertiess = new LemsCollection<ConnectionProperties>();
	
	 
	
	 
	
	public String getTo() {
		return to;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTimes() {
		return times;
	}
	
	public String getTArgets() {
		return targets;
	}
 
	
	public String getTargetPortName() {
		String ret = null;
		if (eventTargets.size() > 0) {
			ret = eventTargets.get(0).getPortName();
		}
		return ret;
	}

	 

	public ConnectionProperties getConnectionProperties() {
		ConnectionProperties ret = null;
		if (connectionPropertiess.size() > 0) {
			ret = connectionPropertiess.get(0);
		}
		return ret;
	}
 	
 
}
