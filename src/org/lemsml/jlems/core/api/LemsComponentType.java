package org.lemsml.jlems.core.api;

import java.util.HashMap;

public class LemsComponentType {

	protected String name;
	
	
	protected HashMap<String, LemsParameter> parameterHM = new HashMap<String, LemsParameter>();
	
	
	protected LemsComponentType(String s) {
		name = s;
	}
	
	
	

	public void addParameter(String s, LemsDimension d) {
		LemsParameter p = new LemsParameter(s, d);
		parameterHM.put(s, p);
	}
	
	
}
