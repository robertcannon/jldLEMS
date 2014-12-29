package org.lemsml.model;

public class ParameterValue {

	protected String name;
	protected String value;
	
	public ParameterValue(String sn, String sv) {
		name = sn;
		value = sv;
	}
 

	public String getValue() {
		return value;
	}


	public String getName() {
		return name;
	}
 
}
