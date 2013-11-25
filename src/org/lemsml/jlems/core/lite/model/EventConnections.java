package org.lemsml.jlems.core.lite.model;

import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.LemsCollection;

public class EventConnections {

	public String name;
	
	public String from;
	
	public String to;
	
	
	public LemsCollection<EventSource> eventSources = new LemsCollection<EventSource>();
	
	public LemsCollection<EventTarget> eventTargets = new LemsCollection<EventTarget>();
	
	
	public LemsCollection<SourceTargetConnector> sourceTargetConnectors = new LemsCollection<SourceTargetConnector>();
	
	public LemsCollection<ConnectionProperties> connectionPropertiess = new LemsCollection<ConnectionProperties>();
	
	public LemsCollection<EventArguments> eventArgumentss = new LemsCollection<EventArguments>();

	public String getFrom() {
		return from;
	}
	
	public String getTo() {
		return to;
	}
	
	public String getSourcePortName() {
		String ret = null;
		if (eventSources.size() > 0) {
			ret = eventSources.get(0).getPortName();
		}
		return ret;
	}
	
	public String getTargetPortName() {
		String ret = null;
		if (eventTargets.size() > 0) {
			ret = eventTargets.get(0).getPortName();
		}
		return ret;
	}

	public FromArrayConnector getConnector() throws ContentError {
		if (sourceTargetConnectors.size() == 0) {
			throw new ContentError("No connection on event connections");
		}
		SourceTargetConnector scs = sourceTargetConnectors.get(0);
		FromArrayConnector fac = scs.getConnector();
		return fac;
	}

	public ConnectionProperties getConnectionProperties() {
		ConnectionProperties ret = null;
		if (connectionPropertiess.size() > 0) {
			ret = connectionPropertiess.get(0);
		}
		return ret;
	}
 	
	
}
