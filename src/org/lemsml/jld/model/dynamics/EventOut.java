package org.lemsml.jld.model.dynamics;

import org.lemsml.jld.imodel.dynamics.IEventOut;

public class EventOut extends AbstractDynamicsBlockElement implements IEventOut {

	protected String port;
	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public EventOut(AbstractDynamicsBlock b, String n) {
		super(b, n);
 	}

}
