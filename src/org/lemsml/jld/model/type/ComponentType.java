package org.lemsml.jld.model.type;

import java.util.HashMap;
import java.util.List;

import org.lemsml.jld.api.APIException;
import org.lemsml.jld.io.E;
import org.lemsml.jld.model.Dimension;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.core.AbstractElement;
import org.lemsml.jld.model.core.ListMap;
import org.lemsml.jld.model.core.Single;
import org.lemsml.jld.model.dynamics.Dynamics;
import org.lemsml.jld.model.dynamics.DynamicsBuilder;
import org.lemsml.jld.model.simulation.Simulation;
import org.lemsml.jld.model.simulation.SimulationBuilder;
import org.lemsml.jld.model.structure.Structure;
import org.lemsml.jld.model.structure.StructureBuilder;

public class ComponentType extends AbstractElement {

	
	private Lems lems;
	
	protected String eXtends;
		
	protected ListMap<Parameter> parameterMap = new ListMap<Parameter>();
	protected ListMap<Text> textMap = new ListMap<Text>();
	protected ListMap<Path> pathMap = new ListMap<Path>();
	protected ListMap<ReceivePort> receivePortMap = new ListMap<ReceivePort>();
	protected ListMap<SendPort> sendPortMap = new ListMap<SendPort>();
	protected ListMap<Fixed> fixedMap = new ListMap<Fixed>();
	
	protected ListMap<Requirement> requirementMap = new ListMap<Requirement>();
	protected ListMap<Exposure> exposureMap = new ListMap<Exposure>();
	
	
	protected ListMap<Child> childMap = new ListMap<Child>();
	protected ListMap<Children> childrenMap = new ListMap<Children>();

	protected Single<Dynamics> dynamicsSingle = new Single<Dynamics>();
	protected Single<Structure> structureSingle = new Single<Structure>();
	protected Single<Simulation> simulationSingle = new Single<Simulation>();
	
	
	private ComponentType r_supertype = null;
	
	
	protected ComponentType(Lems l, String s) {
		super(s);
		lems = l;
	}
	
	
	public String toString() {
		return "ComponetType, name=" + name;
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

	public Fixed addFixed(String s) {
		Fixed ret = new Fixed(this, s);
		fixedMap.put(s, ret);
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

	

	public ReceivePort addReceivePort(String eltname) {
		ReceivePort ret = new ReceivePort(this, eltname);
		receivePortMap.put(eltname, ret);
		return ret;
	}

	public SendPort addSendPort(String eltname) {
		SendPort ret = new SendPort(this, eltname);
		sendPortMap.put(eltname, ret);
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


	public String getExtends() {
		return eXtends;
	}


	public void setExtends(String eXtends) {
		this.eXtends = eXtends;
	}


	public List<ReceivePort> getReceivePorts() {
		 return receivePortMap.getItems();
	}

	public List<SendPort> getSendPorts() {
		return sendPortMap.getItems();
	}


	public List<Fixed> getFixeds() {
		return fixedMap.getItems();
	}


	public void setSupertype(ComponentType sct) {
		r_supertype = sct;
	}


	public ComponentType getSupertype() {
		return r_supertype;
	}
 


	
}
