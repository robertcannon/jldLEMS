package org.lemsml.jlems.core.lite.run;

public class EventResponse {

	String portName;
	
	ActionBlock actionBlock;
	
	
	
	public EventResponse(String pn) {
		portName = pn;
	}
	
	public void setActionBlock(ActionBlock ab) {
		actionBlock = ab;
	}
	
	public ActionBlock getActionBlock() {
		return actionBlock;
	}
	
	
}
