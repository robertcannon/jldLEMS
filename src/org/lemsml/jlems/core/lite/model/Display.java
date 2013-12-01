package org.lemsml.jlems.core.lite.model;

import org.lemsml.jlems.core.type.LemsCollection;

public class Display {
 
	public String id;
	
	public double interval;
 
	public LemsCollection<VariableDisplay> variableDisplays = new LemsCollection<VariableDisplay>();
	
 
	public LemsCollection<VariableDisplay> getVariableDisplays() {
		return variableDisplays;
	}


	public String getID(String dflt) {
		if (id == null) {
			id = dflt;
		}
		return id;
	}
 
 
	
	
}
