package org.lemsml.jld.model.core;

import org.lemsml.jld.expression.Dim;
import org.lemsml.jld.model.Dimension;

public class DimensionalQuantity {

	private double value;
	private Dimension dimension;
	private String originalText;
	
	
	public void setNoValue() {
		value = Double.NaN;
	}


	public void setValue(double d, Dimension dim) {
		value = d;
		dimension = dim;
	}

	public double getDoubleValue() {
		return value;
	}
	
	public Dimension getDimensionObject() {
		return dimension;
	}
	

	public void setOriginalText(String s) {
		originalText = s;
	}


	public boolean isZero() {
		boolean ret = false;
		if (value == 0.0) {
			ret = true;
		}
		return ret;
	}

}
