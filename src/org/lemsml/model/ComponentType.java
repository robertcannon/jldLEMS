package org.lemsml.model;

import java.util.HashMap;

import org.lemsml.api.APIException;
import org.lemsml.model.dynamics.Dynamics;
import org.lemsml.model.simulation.Simulation;
import org.lemsml.model.structure.Structure;

public class ComponentType extends AbstractElement {

		
	protected ListMap<Parameter> parameterMap = new ListMap<Parameter>();
	protected ListMap<Text> textMap = new ListMap<Text>();
	protected ListMap<Path> pathMap = new ListMap<Path>();
	
	protected ListMap<Requirement> requirementMap = new ListMap<Requirement>();
	protected ListMap<Exposure> exposureMap = new ListMap<Exposure>();
	
	
	protected ListMap<Child> childMap = new ListMap<Child>();
	protected ListMap<Children> childrenMap = new ListMap<Children>();

	protected Single<Dynamics> dynamicsSingle = new Single<Dynamics>();
	protected Single<Structure> structureSingle = new Single<Structure>();
	protected Single<Simulation> simulationSingle = new Single<Simulation>();
	
	
	protected ComponentType(Lems l, String s) {
		super(l, s);
	}
	
	
	public Parameter addParameter(String s) {
		Parameter p = new Parameter(lems, s);
		parameterMap.put(s, p);
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

	
	public Text addText(String s) {
		Text ret = new Text(lems, s);
		textMap.put(s, ret);
		return ret;
	}
	
	public Path addPath(String s) {
		Path ret = new Path(lems, s);
		pathMap.put(s, ret);
		return ret;
	}

	
	public Requirement addRequirement(String s) {
		Requirement p = new Requirement(lems, s);
		requirementMap.put(s, p);
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
		exposureMap.put(s, p);
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
		childrenMap.put(s, ret);
		return ret;
	}
	
	public void addChildren(String n, String t) {
		Children ret = addChildren(n);
		ret.setComponentType(t);
	}
	
	
	

	public Child addChild(String s) {
		Child ret = new Child(lems, s);
		childMap.put(s, ret);
		return ret;
	}
	
	public void addChild(String n, String t) {
		Child ret = addChild(n);
		ret.setComponentType(t);
	}
	
	
	public Dynamics addDynamics() throws APIException {
		Dynamics ret = new Dynamics();
		dynamicsSingle.setValue(ret);
		return ret;
	}


	public Structure addStructure() throws APIException {
		Structure ret = new Structure();
		structureSingle.setValue(ret);
		return ret;
	}


	public Simulation addSimulation() throws APIException {
		Simulation ret = new Simulation();
		simulationSingle.setValue(ret);
		return ret;
	}
	
	
}
