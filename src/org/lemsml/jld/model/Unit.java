package org.lemsml.jld.model;

import org.lemsml.jld.model.core.Element;
 


public class Unit extends Element {

	
	protected String dimension = null;
	protected int power = 0;
	protected String symbol = null;
	protected double offset = 0.;
	protected double scale = 1.0;
	
	
	private Dimension r_dimension = null;

	
	protected Unit(String name) {
		super(name);
	}


	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	
	public void setOffset(double d) {
		offset = d;
	}


	public void setScale(double scale) {
		this.scale = scale;
	}


	public void setPower(int power) {
		this.power = power;
	}


	public void setDimension(Dimension dimension) {
		r_dimension = dimension;
	}

	public Dimension getDimensionObject() {
		return r_dimension;
	}
	
	
	public String getDimension() {
		return dimension;
	}


	public String getSymbol() {
		return symbol;
	}
	
	public int getPower() {
		return power;
	}
	
	public double getOffset() {
		return offset;
	}
	
	public double getScale() {
		return scale;
	}
	
}
