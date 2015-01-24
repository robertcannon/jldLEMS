package org.lemsml.jld.hrun;

import java.util.HashMap;
import java.util.Set;

import org.lemsml.jld.eval.DoublePointer;

public class LocalValues {
	
	
	HashMap<String, DoublePointer> valueMap = new HashMap<String, DoublePointer>();
	
	 

	public Set<String> keySet() {
		return valueMap.keySet();
	}
	
	public double getValue(String s) {
		return valueMap.get(s).getValue();
	}


	public void addValue(String name, double value) {
		 valueMap.put(name, new DoublePointer(value));
	}

}
