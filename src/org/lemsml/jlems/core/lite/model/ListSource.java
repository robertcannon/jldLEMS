package org.lemsml.jlems.core.lite.model;

import java.util.ArrayList;

public class ListSource {

	
	public String values;
	
	
	
	int[] intDat = null;
	double[] dblDat = null;
	
	
	public int[] getIntValues() {
		if (intDat == null) {
			readIntData();
		}
		return intDat;
	}
		
	
	
	
	private void readIntData() {
		String[] bits = new String[0];
		
		if (values.indexOf(",") > 0) {
			bits = values.split(",");
		} else {
			bits = values.split(" ");
		}
			
		ArrayList<Integer> wk = new ArrayList<Integer>();
			
		for (int i = 0; i < bits.length; i++) {
			String tok = bits[i].trim();
			if (tok.length() > 0) {
				int v = Integer.parseInt(tok);
				wk.add(v);				
			}
		}
 	 
		int n = wk.size();
		intDat = new int[n];
		for (int i = 0; i < n; i++) {
			intDat[i] = wk.get(i);
		}
	}
	
	
	public double[] getFloatValues() {
		if (dblDat == null) {
			readFloatData();
		}
		return dblDat;
	}
		
	
	
	
	private void readFloatData() {
		String[] bits = new String[0];
		
		if (values.indexOf(",") > 0) {
			bits = values.split(",");
		} else {
			bits = values.split(" ");
		}
			
		ArrayList<Double> wk = new ArrayList<Double>();
			
		for (int i = 0; i < bits.length; i++) {
			String tok = bits[i].trim();
			if (tok.length() > 0) {
				double d = Double.parseDouble(tok);
				wk.add(d);				
			}
		}
 	 
		int n = wk.size();
		dblDat = new double[n];
		for (int i = 0; i < n; i++) {
			dblDat[i] = wk.get(i);
		}
	}
	
}


