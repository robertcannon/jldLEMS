package org.lemsml.jld.model.dynamics;

public class OnEvent extends AbstractDynamicsBlock {

	protected String port;
	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public OnEvent(Dynamics d) {
		super(d);
 	}

	
}
