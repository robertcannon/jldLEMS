package org.lemsml.jld.model;

import java.util.HashMap;

import org.lemsml.jld.api.APIException;
import org.lemsml.jld.model.core.AbstractLemsElement;
import org.lemsml.jld.model.core.ModelException;
 
public class Dimension extends AbstractLemsElement {
 
	
	protected int m;
	protected int l;
	protected int t;
	protected int i;
	protected int k;
	protected int n;
	protected int j;
	
	private boolean any = false;
	
	
	public static enum SI {
		MASS {void apply(Dimension d, int i) { d.setM(i); }},
		LENGTH {void apply(Dimension d, int i) { d.setL(i); }},
		TIME {void apply(Dimension d, int i) { d.setT(i); }},
		CURRENT {void apply(Dimension d, int i) { d.setI(i); }},
		TEMPERATURE {void apply(Dimension d, int i) { d.setK(i); }},
		AMOUNT {void apply(Dimension d, int i) 	{ d.setN(i); }},
		LUMINOUS_INTENSITY {void apply(Dimension d, int i) { d.setJ(i); }};
		abstract void apply(Dimension d, int i);
	}
	
	
	HashMap<SI, Integer> dims = new HashMap<SI, Integer>();
 

	protected Dimension(Lems m, String s) {
		super(m, s);
	}
	
	
	public void setJ(int v) {
			j = v;
	}

	public void setN(int v) {
		n = v;
	}


	public void setK(int v) {
		k = v;
	}


	public void setI(int v) {
		i = v;
	}


	public void setT(int v) {
		t = v;
	}


	public void setL(int v) {
		l = v;
	}


	public void setM(int v) {
		m = v;
	}


	public void set(SI bd, int i) throws ModelException {
		//throw exception on resetting?
		if (dims.containsKey(bd)) {
			throw new ModelException("After it is set, the power of a base diemsion cannot be changed");
		}
		
		checkFocus();
		
		dims.put(bd, i);
		bd.apply(this, i);
	}


	public int getM() {
		return m;
	}
	
	public int getL() {
		return l;
	}
	
	public int getT() {
		return t;
	}
	
	public int getN() {
		return n;
	}
	
	public int getK() {
		return k;
	}
	
	public int getJ() {
		return j;
	}

	public int getI() {
		return i;
	}


	public void setAny() {
		any = true;
	}
	
	public boolean isAny() {
		return any;
	}

	
	
}
