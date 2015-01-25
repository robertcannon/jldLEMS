package org.lemsml.jld.model;

import org.lemsml.jld.imodel.IConstant;
import org.lemsml.jld.model.core.Element;

public class Constant extends Element implements IConstant {

	protected String dimension;
	protected double value;
	protected String symbol;
	
	private Dimension r_dimension;
	
	public String getSymbol() {
		return symbol;
	}


	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


	protected Constant(String name) {
		super(name);
	}
	
	
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	
	public void setValue(double value) {
		this.value = value;
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
