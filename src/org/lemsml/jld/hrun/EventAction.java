package org.lemsml.jld.hrun;
 
public class EventAction {

	String portName;
	
	ActionBlock action;
	
	
	public EventAction(String pn) {
		 portName = pn;
	}

	public EventAction(String pn, ActionBlock ab) {
		portName = pn;
		action = ab;
	}
	
	public String getPortName() {
		return portName;
	}
	
	public ActionBlock getAction() {
		return action;
	}


	public void setAction(ActionBlock ea) {
		action = ea; 
	}

	
}
