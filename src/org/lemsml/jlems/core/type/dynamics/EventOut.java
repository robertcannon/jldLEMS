package org.lemsml.jlems.core.type.dynamics;

import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.ComponentType;
import org.lemsml.jlems.core.type.SendPort;

public class EventOut {

	public String port;
	
	public SendPort r_sendPort;

	
	public void resolve(ComponentType base) throws ContentError {
		r_sendPort = base.getSendPort(port);
		
	}


	public String getPortName() {
		return r_sendPort.getName();
	}


	public EventOut makeCopy() {
		EventOut ret = new EventOut();
		ret.port = port;
		return ret;
	}
	
}
