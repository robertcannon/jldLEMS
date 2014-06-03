package org.lemsml.jlems.core.lite.model;

import java.util.ArrayList;

public class ListSource {

	
	public String values;
	
	int[] dat = null;
	
	
	
	public int[] getValues() {
		if (dat == null) {
			readData();
		}
		return dat;
	}
		
	
	
	
	private void readData() {
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
		dat = new int[n];
		for (int i = 0; i < n; i++) {
			dat[i] = wk.get(i);
		}
	}
	
}


