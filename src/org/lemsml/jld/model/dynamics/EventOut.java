package org.lemsml.jld.model.dynamics;

public class EventOut extends AbstractDynamicsBlockElement {

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
