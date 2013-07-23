package org.lemsml.jlems.core.type;

public class LocalParameterValue {

	String name;
	DimensionalQuantity dqValue;
	
	public LocalParameterValue(String nm, DimensionalQuantity dq) {
		name = nm;
		dqValue = dq;
	}

	
	public String toString() {
		return "{" + name + ", " + dqValue.getValue() + ", " + dqValue.getDimension() + "}";
	}


	public String getName() {
		return name;
	}
	
	public double getValue() {
		return dqValue.getValue();
	}
	
}
