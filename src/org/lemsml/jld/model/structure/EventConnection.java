package org.lemsml.jld.model.structure;

import org.lemsml.jld.imodel.structure.IEventConnection;

public class EventConnection extends AbstractStructureElement implements IEventConnection {

	protected String from;
	protected String to;
	protected String sourcePort;
	protected String targetPort;
	protected String delay;
	protected String receiver;
	protected String receiverContainer;
	

	public EventConnection(AbstractStructureBlock b) {
		 super(b);
	}
	
	
	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}


	public String getSourcePort() {
		return sourcePort;
	}


	public void setSourcePort(String sourcePort) {
		this.sourcePort = sourcePort;
	}


	public String getTargetPort() {
		return targetPort;
	}


	public void setTargetPort(String targetPort) {
		this.targetPort = targetPort;
	}


	public String getDelay() {
		return delay;
	}


	public void setDelay(String delay) {
		this.delay = delay;
	}


	public String getReceiver() {
		return receiver;
	}


	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


	public String getReceiverContainer() {
		return receiverContainer;
	}


	public void setReceiverContainer(String receiverContainer) {
		this.receiverContainer = receiverContainer;
	}



 

}
