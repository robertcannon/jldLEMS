package org.lemsml.jld.model.structure;

public class EventConnection extends AbstractStructureElement {

	protected String from;
	protected String to;
	
	
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


	public EventConnection(AbstractStructureBlock b) {
		 super(b);
	}

}
