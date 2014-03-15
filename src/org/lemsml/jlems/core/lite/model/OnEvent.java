package org.lemsml.jlems.core.lite.model;

import java.util.ArrayList;

public class OnEvent extends OnAbstract {

	public String port;
	
	public ArrayList<Var> vars = new ArrayList<Var>();
	
	
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
