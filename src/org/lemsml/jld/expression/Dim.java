package org.lemsml.jld.expression;

import org.lemsml.jld.model.Dimension;
import org.lemsml.jlems.core.logging.E;

public class Dim {

	 
	
	int m;
	int l;
	int t;
	int i;
	int k;
	int n;
	int j;
	
	boolean isZero = false;
	boolean isWild = false;
	
	double doubleValue = Double.NaN; // bit of a hack to get powers through in the case of dimensionless constants

	
	public Dim() {
		m = 0; 
		l = 0;
		t = 0;
		i = 0;
		k = 0;
		n = 0;
		j = 0;
	}

 
    public Dim(Dimension d) {
    	m = d.getM();
		l = d.getL();
		t = d.getT();
		i = d.getI();
		k = d.getK();
		n = d.getN();
		j = d.getJ();
		if (d.isAny()) {
			isWild = true;
		}
	}


	@Override
	public String toString() {
    	String[] lbls = {"m", "l", "t", "i", "k", "n", "j"};
    	int[] vals = {m, l, t, i, k, n, j};
     	String sd = "";
    	for (int i = 0; i < lbls.length; i++) {
    		if (vals[i] != 0) {
    			if (sd.length() > 0) {
    				sd += ", ";
    			}
    			sd += lbls[i] + "=" + vals[i];
    		}
    	}
    	String ret = "ExprDimensional[" + (sd.length() > 0 ? sd : "dimensionless") + "]";
    	return ret;
    }
     
	
	public Dim getDivideBy(Dim d) {
		Dim ret = new Dim();
		ret.m = m - d.getM();
		ret.l = l - d.getL();
		ret.t = t - d.getT();
		ret.i = i - d.getI();
		ret.k = k - d.getK();
		ret.n = n - d.getN();
		ret.j = j - d.getJ();
		return ret;
	}

  
	public int getI() {
		return i;
	}
 
  
	public int getL() {
		return l;
	}

  
	public int getM() {
		return m;
	}

 
	public int getT() {
		return t;
	}
 
	public int getK() {
		return k;
	}
 
	public int getN() {
		return n;
	}
 
	public int getJ() {
		return j;
	}

  
	public Dim getTimes(Dim d) {
		Dim ret = new Dim();
		ret.m = m + d.getM();
		ret.l = l + d.getL();
		ret.t = t + d.getT();
		ret.i = i + d.getI();
		ret.k = k + d.getK();
		ret.n = n + d.getN();
		ret.j = j + d.getJ();
		return ret;
	}
    
	public boolean isDimensionless() {
		boolean ret = false;
		if (m == 0 && l == 0 && t == 0 && i == 0 && k == 0 && n == 0 && j == 0) {
			ret = true;
		}
		return ret;
	}

  
	public boolean matches(Dim d) {
		boolean ret = false;
		if (m == d.getM() && l == d.getL() && t == d.getT() && i == d.getI() && k == d.getK() && n == d.getN() && j == d.getJ()) {
			ret = true;
		}
		return ret;
	}

 
	public Dim power(double dbl) {
		Dim ret = null;
		if (dbl - Math.round(dbl) < 1.e-6) {
			int id = (int)(Math.round(dbl));
			Dim re = new Dim();
			re.m = id * m;
			re.l = id * l;
			re.t = id * t;
			re.i = id * i;
			re.k = id * k;
			re.n = id * n;
			re.j = id * j;
			ret = re;
		} else {
			E.missing("Can't work with fractional dimensions yet");
		}
		return ret;
	}

	public void setZero() {
		isZero = true;
		
	}

	public boolean isAny() {
		return (isZero || isWild);
	}

	public void setDoubleValue(double dval) {
		doubleValue = dval;	
	}
 
	public double getDoubleValue() {
		return doubleValue;
	}

	public void setT(int j) {
		t = j;
	}
	
}
