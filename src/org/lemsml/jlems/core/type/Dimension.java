package org.lemsml.jlems.core.type;

import org.lemsml.jlems.core.annotation.ModelElement;
import org.lemsml.jlems.core.annotation.ModelProperty;
import org.lemsml.jlems.core.expression.Dimensional;
import org.lemsml.jlems.core.logging.E;

@ModelElement(info="A Dimenson element associated a name with a particular combination " +
		"of  the standards SI base dimensions, mass, lenght, time, current, temperature " +
		"and amount if substance (moles). Fractional dimensions are not currently supported.")
public class Dimension implements Named, Summaried, DataMatchable, Dimensional {

    public static final String NO_DIMENSION = "none";

    @ModelProperty(info="The name to be used when referring to this dimension from variable declaration or units")
    public String name;
    
    @ModelProperty(info="Mass")
    public int m;   
    
    @ModelProperty(info="Length")
    public int l;  

    @ModelProperty(info="Time")
    public int t;   

    @ModelProperty(info="Current")
    public int i;   

    @ModelProperty(info="Temperature")
    public int k;   

    @ModelProperty(info="Amunt of substance")
    public int n;   
    
    @ModelProperty(info="Luminous intensity")
    public int j;  
    
    
    private double dval = Double.NaN; // bit messy, just for constant powers
     
    public static Dimension timeDimension;
    

    public Dimension() {
    	// maybe only one constructor?
    }
    

    public Dimension(String sn) {
        name = sn;
    }
    
    public Dimension(int im, int il, int it, int ii, int ik, int in, int ij) {
    	m = im;
    	l = ik;
    	t = it;
    	i = ii;
    	k = ik;
    	n = in;
    	j = ij;
    }
  
   
    public boolean dataMatches(Object obj) {
        boolean ret = false;
        if (obj instanceof Dimension) {
            Dimension d = (Dimension) obj;
            if (m == d.m && l == d.l && t == d.t && i == d.i && k == d.k && n == d.n) {
                ret = true;
            }
        }
        return ret;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Dimension[" + summary() + "]";
    }

    public String summary() {
     	String ret = (name != null ? name : " (unnamed) ");
    	int[] vals = {m, l, t, i, k, n};
    	String[] names= {"m", "l", "t", "i", "k", "n"};
    	int nnz = 0;
    	for (int i = 0; i < vals.length; i++) {
    		if (vals[i] != 0) {
    			ret += " " + names[i] + "=" + vals[i];
    			nnz += 1;
    		}
    	}
    	if (nnz == 0) {
    		ret += " dimensionless";
    	}
    	return ret;
    }

   

    public boolean matches(Dimension d) {
     	
        boolean ret = false;
        if (this.equals(d)) {
            ret = true;

        } else if (m == d.m && l == d.l && t == d.t && i == d.i && k == d.k && n == d.n) {
            ret = true;
        } else {
            ret = false;
        }
        return ret;
    }

    public Dimension getTimes(Dimensional d) {
      	
    	Dimension ret = new Dimension("");
        ret.m = m + d.getM();
        ret.l = l + d.getL();
        ret.t = t + d.getT();
        ret.i = i + d.getI();
        ret.k = k + d.getK();
        ret.n = n + d.getN();
        return ret;
    }

    public Dimensional getDivideBy(Dimensional d) {
      	
    	Dimension ret = new Dimension("");
        ret.m = m - d.getM();
        ret.l = l - d.getL();
        ret.t = t - d.getT();
        ret.i = i - d.getI();
        ret.k = k - d.getK();
        ret.n = n - d.getN();
        return ret;
    }

    public boolean isDimensionless() {
    	
        boolean ret = false;
        if (m == 0 && l == 0 && t == 0 && i == 0 && k == 0 && n == 0) {
            ret = true;
        }
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

    public void setN(int n) {
        this.n = n;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setK(int k) {
        this.k = k;
    }

    public void setL(int l) {
        this.l = l;
    }

    public void setM(int m) {
        this.m = m;
    }

    public void setT(int t) {
        this.t = t;
    }
    
    public void setJ(int j) {
        this.j = j;
    }

    

    public boolean matches(Dimensional d) {
         boolean ret = false;
        if (m == d.getM() && l == d.getL() && t == d.getT() && i == d.getI() && k == d.getK() && n == d.getN()) {
            ret = true;
        }
        return ret;
    }

    public Dimensional power(double dbl) {
       Dimensional ret = null;
        if (dbl - Math.round(dbl) < 1.e-6) {
            int ifac = (int) (Math.round(dbl));
            Dimension d = new Dimension("");
            d.m = ifac * m;
            d.l = ifac * l;
            d.t = ifac * t;
            d.i = ifac * i;
            d.k = ifac * k;
            d.n = ifac * n;
            ret = d;
        } else {
            E.missing("Can't work with fractional dimensions yet");
        }
        return ret;
    }

    public boolean isAny() {
        return false;
    }

    public void setDoubleValue(double d) {
        dval = d;
    }

    public double getDoubleValue() {
        return dval;
    }

	public static Dimension getTimeDimension() {
		if (timeDimension == null) {
			timeDimension = new Dimension("time");
			timeDimension.setT(1);
		}
		return timeDimension;
	}

	 public static String getSIUnit(Dimension d) {
 
	        int m = d.getM();
	        int l = d.getL();
	        int t = d.getT();
	        int i = d.getI();
	        int n = d.getN();
	        int k = d.getK();
	        int j = d.getJ();
	       
	        String[] symbols = {"kg", "m", "s", "A", "K", "mol", "lum"};
	        int[] powers = {m, l, t, i, k, n, j};
	        
	        String ret = "";
	        for (int jj = 0; jj < powers.length; jj++) {
	        	int p = powers[jj];
	        	if (p != 0) {
	        		ret += symbols[jj];
	        		if (p != 1) {
	        			ret += "^" + p;
	        		}
	        		ret += " ";
	        	}
	        }
	        ret = ret.trim();
	        return ret;
	    }


	 
	 // the dimension string is a comma-separated list of dimension powers in the order
	 // mass, length, time, current, temperature, amount, brightness
	public String getDimensionString() {
		int[] vals = {m, l, t, i, k, n, j};
		String ret = "" + vals[0];
		for (int i = 1; i < vals.length; i++) {
			ret += "," + vals[i];
		}
		return ret;
	}
   
}
