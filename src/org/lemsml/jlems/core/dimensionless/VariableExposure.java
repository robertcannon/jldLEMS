package org.lemsml.jlems.core.dimensionless;

public class VariableExposure {

	
	public String localName;
	public String exposedName;
	
	
	public VariableExposure(String ln, String en) {
		localName = ln;
		exposedName = en;
	}


	public String getExposedName() {
		return exposedName;
	}
	
	public String getLocalName() {
		return localName;
	}
	
}
