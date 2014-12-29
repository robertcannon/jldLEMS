package org.lemsml.model;

import org.lemsml.model.core.AbstractLemsElement;


public class Unit extends AbstractLemsElement {

	
	protected String dimension = null;
	protected int power = 0;
	protected String symbol = null;
	
	private Dimension r_dimension = null;

	
	protected Unit(Lems l, String s) {
		super(l, s);
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

}
