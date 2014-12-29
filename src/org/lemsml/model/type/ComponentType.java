package org.lemsml.model.type;

import java.util.HashMap;
import java.util.List;

import org.lemsml.api.APIException;
import org.lemsml.io.jld.E;
import org.lemsml.model.Dimension;
import org.lemsml.model.Lems;
import org.lemsml.model.core.AbstractElement;
import org.lemsml.model.core.ListMap;
import org.lemsml.model.core.Single;
import org.lemsml.model.dynamics.Dynamics;
import org.lemsml.model.dynamics.DynamicsBuilder;
import org.lemsml.model.simulation.Simulation;
import org.lemsml.model.simulation.SimulationBuilder;
import org.lemsml.model.structure.Structure;
import org.lemsml.model.structure.StructureBuilder;

public class ComponentType extends AbstractElement {

	
	private Lems lems;
		
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
		super(s);
		lems = l;
	}
	
	
	public Parameter addParameter(String s) {
		Parameter p = new Parameter(this, s);
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
		Text ret = new Text(this, s);
		textMap.put(s, ret);
		return ret;
	}
	
	public Path addPath(String s) {
		Path ret = new Path(this, s);
		pathMap.put(s, ret);
		return ret;
	}

	
	public Requirement addRequirement(String s) {
		Requirement p = new Requirement(this, s);
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
		Exposure p = new Exposure(this, s);
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
		Children ret = new Children(this, s);
		childrenMap.put(s, ret);
		return ret;
	}
	
	public void addChildren(String n, String t) {
		Children ret = addChildren(n);
		ret.setType(t);
	}
	
	
	

	public Child addChild(String s) {
		Child ret = new Child(this, s);
		childMap.put(s, ret);
		return ret;
	}
	
	public void addChild(String n, String t) {
		Child ret = addChild(n);
		ret.setType(t);
	}
	
	
	// TODO - get rid of these builder objects
	
	public Dynamics createDynamics() throws APIException {
		DynamicsBuilder db = new DynamicsBuilder();
		Dynamics ret = db.newDynamics(this);
		dynamicsSingle.setValue(ret);
		return ret;
	}


	public Structure createStructure() throws APIException {
		StructureBuilder sb = new StructureBuilder();
		Structure ret = sb.newStructure(this);
		structureSingle.setValue(ret);
		return ret;
	}


	public Simulation createSimulation() throws APIException {
		SimulationBuilder sb = new SimulationBuilder();
		Simulation ret = sb.newSimulation(this);
		simulationSingle.setValue(ret);
		return ret;
	}


	public List<Parameter> getParameters() {
		 return parameterMap.getItems(); 
	}


	public List<Text> getTexts() {
		 return textMap.getItems();
	}
	
	public List<Path> getPaths() {
		return pathMap.getItems();
	}
	
	public List<Requirement> getRequirements() {
		return requirementMap.getItems();
	}


	public List<Exposure> getExposures() {
		return exposureMap.getItems();
	}


	public List<Child> getChilds() {
		return childMap.getItems();
	}
	 
	public List<Children> getChildrens() {
		return childrenMap.getItems();
	}


	public Dynamics getDynamics() {
		return dynamicsSingle.getItem();
	}


	public Structure getStructure() {
		return structureSingle.getItem();
	}


	public Simulation getSimulation() {
		return simulationSingle.getItem();
	}

 
	
}
