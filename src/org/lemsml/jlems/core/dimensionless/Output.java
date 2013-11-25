package org.lemsml.jlems.core.dimensionless;

public class Output {

	public String variable;
	
	public String value;
	
	
	public Output(String var) {
		variable = var;
	}

	
	
	public Output() {
	 
	}



	public void setExpression(String v) {
		value = v;
	}



	
	public String getLocalName() {
		// TODO - only works if value is a simple var name
		return value;
	}
	
	public String getExposedName() {
		return variable;
	}
	
}




