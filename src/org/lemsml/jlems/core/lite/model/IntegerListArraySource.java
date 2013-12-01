package org.lemsml.jlems.core.lite.model;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class IntegerListArraySource {

	
	public String values;
	
	int[] dat = null;
	
	
	
	public int[] getValues() {
		if (dat == null) {
			readData();
		}
		return dat;
	}
		
	
	
	
	private void readData() {
			StringTokenizer st = new StringTokenizer(values, ", ");
			ArrayList<Integer> wk = new ArrayList<Integer>();
			
			while (st.hasMoreTokens()) {
				String tok = st.nextToken();
				int v = Integer.parseInt(tok);
				wk.add(v);
			}
			
			int n = wk.size();
			dat = new int[n];
			for (int i = 0; i < n; i++) {
				dat[i] = wk.get(i);
			}
	}
	
}


