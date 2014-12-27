package org.lemsml.model;

public class Constant extends AbstractElement {

	protected String dimension;
	protected double value;
	protected String symbol;
	
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
	
	
}
