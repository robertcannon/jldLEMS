package org.lemsml.jlems.core.lite.model;

import java.util.ArrayList;

import org.lemsml.jlems.core.lite.run.component.FloatAssignment;
import org.lemsml.jlems.core.logging.E;

public class OnEvent extends OnAbstract {

	public String port;
	
	public ArrayList<FloatAssignment> floatAssignments = new ArrayList<FloatAssignment>();
	
	
	public OnEvent(String pn) {
		port = pn;
	}
	
	
	public OnEvent() {
	 
	}

 
	public String getPortName() {
 		return port;
	}


	
}
