package org.lemsml.jld.model;

public class ParameterValue {

	protected String name;
	protected String value;
	
	private double doubleValue;
	
	
	public ParameterValue(String name, String value) {
		this.name = name;
		this.value = value;
	}
 

	public String toString() {
		return "ParameterValue, name=" + name + ", value=" + value;
	}
	
	
	public String getValue() {
		return value;
	}


	public String getName() {
		return name;
	}


	public ParameterValue makeCopy() {
		 ParameterValue ret = new ParameterValue(name, value);
		 return ret;
	}


	public void setDoubleValue(double v) {
		 doubleValue = v;		
	}
	
	public double getDoubleValue() {
		return doubleValue;
	}
 
}
