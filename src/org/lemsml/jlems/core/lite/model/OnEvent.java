package org.lemsml.jlems.core.lite.model;

import java.util.ArrayList;

import org.lemsml.jlems.core.logging.E;

public class OnEvent extends OnAbstract {

	public String port;
	
	public ArrayList<Var> vars = new ArrayList<Var>();
	
	
	public OnEvent(String pn) {
		port = pn;
	}
	
	
	public OnEvent() {
	 
	}

 
	public String getPortName() {
 		return port;
	}


	
}
