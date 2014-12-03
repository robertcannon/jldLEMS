package org.lemsml.jlems.core.api;

public class LemsParameter {

	protected String name;
	protected LemsDimension dimension;
	
	protected LemsParameter(String n, LemsDimension d) {
		name = n;
		dimension = d;
	}
	
}
