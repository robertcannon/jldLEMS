package org.lemsml.model;


public class Unit extends AbstractElement {

	
	protected Dimension dimension = null;
	protected int power = 0;
	protected String symbol;
	
	protected Unit(Lems l, String s) {
		super(l, s);
	}


	public void setSymbol(String s) {
		symbol = s;
	}


	public void setDimension(Dimension d) {
		dimension = d;
	}


	public void setPower(int p) {
		power = p;
	}

}
