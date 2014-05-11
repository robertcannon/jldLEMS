package org.lemsml.jlems.core.lite.model;
 

public class OnEvent extends OnAbstract {

	public String port;
 	
	public OnEvent(String pn) {
		super();
		port = pn;
	}
	
	
	public OnEvent() {
		super();
	}

 
	public String getPortName() {
 		return port;
	}


	
}
