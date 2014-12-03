package org.lemsml.jlems.core.api;

import java.util.HashMap;

import org.lemsml.jlems.core.type.Dimension;

public class LemsDimension extends LemsElement {

	public String name;

	
	protected int m;
	protected int l;
	protected int t;
	protected int i;
	protected int k;
	protected int n;
	protected int j;
	
	public static enum SI {
		MASS {void apply(LemsDimension d, int i) { d.setM(i); }},
		LENGTH {void apply(LemsDimension d, int i) { d.setL(i); }},
		TIME {void apply(LemsDimension d, int i) { d.setT(i); }},
		CURRENT {void apply(LemsDimension d, int i) { d.setI(i); }},
		TEMPERATURE {void apply(LemsDimension d, int i) { d.setK(i); }},
		AMOUNT {void apply(LemsDimension d, int i) 	{ d.setN(i); }},
		LUMINOUS_INTENSITY {void apply(LemsDimension d, int i) { d.setJ(i); }};
		abstract void apply(LemsDimension d, int i);
	}
	
	
	HashMap<SI, Integer> dims = new HashMap<SI, Integer>();
 

	protected LemsDimension(LemsModel m, String s) {
		super(m);
		name = s;
		
	}
	
	
	protected void setJ(int v) {
			j = v;
	}

	protected void setN(int v) {
		n = v;
	}


	protected void setK(int v) {
		k = v;
	}


	protected void setI(int v) {
		i = v;
	}


	protected void setT(int v) {
		t = v;
	}


	protected void setL(int v) {
		l = v;
	}


	protected void setM(int v) {
		m = v;
	}


	public void set(SI bd, int i) throws APIException, APISequenceException {
		//throw exception on resetting?
		if (dims.containsKey(bd)) {
			throw new APIException("After it isset, the power of a base diemsion cannot be changed");
		}
		
		checkFocus();
		
		dims.put(bd, i);
		bd.apply(this, i);
	}
	
	

	
	
}
