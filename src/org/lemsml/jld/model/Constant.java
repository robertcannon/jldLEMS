package org.lemsml.jld.model;

import org.lemsml.jld.imodel.IConstant;
import org.lemsml.jld.model.core.AbstractLemsElement;

public class Constant extends AbstractLemsElement implements IConstant {

	protected String dimension;
	protected double value;
	protected String symbol;
	
	private Dimension r_dimension;
	
	public String getSymbol() {
		return symbol;
	}


	public void setSymbol(String s) {
		symbol = s;
	}


	protected Constant(Lems l, String s) {
		super(l, s);
	}
	
	
	public void setDimension(String s) {
		dimension = s;
	}
	
	public void setValue(double d) {
		value = d;
	}


	public String getDimension() {
		return dimension;
	}


	public double getValue() {
		return value;
	}


	public void setDimension(Dimension d) {
		r_dimension = d;
	}


	public Dimension getDimensionObject() {
		return r_dimension;
	}
	
	
}
