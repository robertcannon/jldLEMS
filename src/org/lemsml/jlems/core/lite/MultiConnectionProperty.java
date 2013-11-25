package org.lemsml.jlems.core.lite;

import org.lemsml.jlems.core.sim.ContentError;

public class MultiConnectionProperty {

	double dvalue;
	
	double[] avalue;
	
	String name;
	
	boolean hasArray = false;
	boolean bdelay = false;
	
	
	public void setIsDelay() {
		bdelay = true;
	}
	
	
	public double getValue(int i) throws ContentError {
		double ret = 0.;
		if (hasArray) {
			if (i < avalue.length) {
				ret= avalue[i];
			} else {
				throw new ContentError("out of range for MultiConnectionProperty " + i + " " + avalue.length);
			}
		} else {
			ret=  dvalue;
		}
		return ret;
	}
	
	
	public void setValue(double d) {
		dvalue = d;
	}
	
	public void setArray(double[] a) {
		hasArray = true;
		avalue = a;
	}


	public boolean isDelay() {
		return bdelay;
	}


	public String getName() {
		return name;
	}


	public void setName(String s) {
		name = s;
	}

}
