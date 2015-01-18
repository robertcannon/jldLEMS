package org.lemsml.jld.eval;

public class DoublePointer {

	double value;
	
	boolean unassigned = false;
	
	public DoublePointer(double d) {
		value = d;
	}

        @Override
        public String toString() {
            return "DP(" + value + ")";
        }
        
	
	public double get() {
		return value;
	}
	
	public double getValue() {
		return value;
	}
	
	
	public void set(double d) {
		value = d;
	}

	public void setUnassigned() {
		unassigned = true;
	}
}
