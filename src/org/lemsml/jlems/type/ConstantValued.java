package org.lemsml.jlems.type;

import org.lemsml.jlems.expression.Valued;

public class ConstantValued implements Valued {

	String name;
	Dimension dimension;
	double value;
	
	
	public ConstantValued(String s, Dimension d, double v) {
		name = s;
		dimension = d;
		value = v;
	}


	@Override
	public String getName() {
		return name;
	}


	@Override
	public double getValue() {
		return value;
	}
 
	
}
