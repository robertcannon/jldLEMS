package org.lemsml.jlems.core.api;

public class LemsUnit {

	
	protected LemsDimension dimension = null;
	protected int power = 0;
	protected String symbol;
	
	protected LemsUnit(String s) {
	}


	public void setSymbol(String s) {
		symbol = s;
	}


	public void setDmension(LemsDimension d) {
		dimension = d;
	}


	public void setPower(int p) {
		power = p;
	}

}
