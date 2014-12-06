package org.lemsml.jlems.core.api;

import java.util.HashMap;

public class ComponentType extends AbstractElement {

		
	protected HashMap<String, Parameter> parameterHM = new HashMap<String, Parameter>();
	protected HashMap<String, Requirement> requirementHM = new HashMap<String, Requirement>();
	protected HashMap<String, Exposure> exposureHM = new HashMap<String, Exposure>();
	
	protected HashMap<String, Child> childHM = new HashMap<String, Child>();
	protected HashMap<String, Children> childrenHM = new HashMap<String, Children>();

	
	
	
	protected ComponentType(Lems l, String s) {
		super(l, s);
	}
	
	
	public Parameter addParameter(String s) {
		Parameter p = new Parameter(lems, s);
		parameterHM.put(s, p);
		return p;
	}

	public Parameter addParameter(String s, Dimension d) {
		Parameter ret = addParameter(s);
		ret.setDimension(d);
		return ret;
	}

	public Parameter addParameter(String s, String dname) {
		Parameter ret = addParameter(s);
		ret.setDimension(dname);
		return ret;
	}

	
	
	

	public Requirement addRequirement(String s) {
		Requirement p = new Requirement(lems, s);
		requirementHM.put(s, p);
		return p;
	}

	public Requirement addRequirement(String s, Dimension d) {
		Requirement ret = addRequirement(s);
		ret.setDimension(d);
		return ret;
	}

	public Requirement addRequirement(String s, String dname) {
		Requirement ret = addRequirement(s);
		ret.setDimension(dname);
		return ret;
	}

	
	
	
	

	public Exposure addExposure(String s) {
		Exposure p = new Exposure(lems, s);
		exposureHM.put(s, p);
		return p;
	}

	public Exposure addExposure(String s, Dimension d) {
		Exposure ret = addExposure(s);
		ret.setDimension(d);
		return ret;
	}

	public Exposure addExposure(String s, String dname) {
		Exposure ret = addExposure(s);
		ret.setDimension(dname);
		return ret;
	}

	
	


	public Children addChildren(String s) {
		Children ret = new Children(lems, s);
		childrenHM.put(s, ret);
		return ret;
	}
	
	public void addChildren(String n, String t) {
		Children ret = addChildren(n);
		ret.setComponentType(t);
	}
	
	
	

	public Child addChild(String s) {
		Child ret = new Child(lems, s);
		childHM.put(s, ret);
		return ret;
	}
	
	public void addChild(String n, String t) {
		Child ret = addChild(n);
		ret.setComponentType(t);
	}
	
	
}
