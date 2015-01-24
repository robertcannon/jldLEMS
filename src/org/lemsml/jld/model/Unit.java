package org.lemsml.jld.model;

import org.lemsml.jld.model.core.AbstractElement;
 


public class Unit extends AbstractElement {

	
	protected String dimension = null;
	protected int power = 0;
	protected String symbol = null;
	protected double offset = 0.;
	protected double scale = 1.0;
	
	
	private Dimension r_dimension = null;

	
	protected Unit(String s) {
		super(s);
	}


	public void setSymbol(String s) {
		symbol = s;
	}


	public void setDimension(String s) {
		dimension = s;
	}
	
	public void setDimension(Dimension d) {
		r_dimension = d;
	}

	public Dimension getDimensionObject() {
		return r_dimension;
	}
	
	
	public void setPower(int p) {
		power = p;
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
	
	public void setOffset(double d) {
		offset = d;
	}
	
	public double getOffset() {
		return offset;
	}
	
	public void setScale(double s) {
		scale = s;
	}
	
	public double getScale() {
		return scale;
	}
	
}
