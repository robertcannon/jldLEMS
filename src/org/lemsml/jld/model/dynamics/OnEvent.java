package org.lemsml.jld.model.dynamics;

import java.util.List;

import org.lemsml.jld.imodel.dynamics.IEventOut;
import org.lemsml.jld.imodel.dynamics.IOnEvent;
import org.lemsml.jld.imodel.dynamics.IStateAssignment;
import org.lemsml.jld.imodel.dynamics.ITransition;

public class OnEvent extends AbstractDynamicsBlock implements IOnEvent {

	protected String port;
	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public OnEvent() {

 	}

	public String getPortName() {
		return port;
	}

	

	
}
