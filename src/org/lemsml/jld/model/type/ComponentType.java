package org.lemsml.jld.model.type;

import java.util.ArrayList;
import java.util.List;

import org.lemsml.jld.api.APIException;
import org.lemsml.jld.imodel.IComponentType;
import org.lemsml.jld.imodel.dynamics.IDynamics;
import org.lemsml.jld.imodel.simulation.ISimulation;
import org.lemsml.jld.imodel.structure.IStructure;
import org.lemsml.jld.model.Dimension;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.core.Element;
import org.lemsml.jld.model.core.ListMap;
import org.lemsml.jld.model.core.Single;
import org.lemsml.jld.model.dynamics.Dynamics;
import org.lemsml.jld.model.dynamics.DynamicsBuilder;
import org.lemsml.jld.model.simulation.Simulation;
import org.lemsml.jld.model.simulation.SimulationBuilder;
import org.lemsml.jld.model.structure.Structure;
import org.lemsml.jld.model.structure.StructureBuilder;
 

public class ComponentType extends Element implements IComponentType {
 
	protected String eXtends;
		
	protected ListMap<Parameter> parameterMap = new ListMap<Parameter>();
	protected ListMap<Text> textMap = new ListMap<Text>();
	protected ListMap<Path> pathMap = new ListMap<Path>();
	protected ListMap<ReceivePort> receivePortMap = new ListMap<ReceivePort>();
	protected ListMap<SendPort> sendPortMap = new ListMap<SendPort>();
	protected ListMap<Fixed> fixedMap = new ListMap<Fixed>();
	protected ListMap<Property> propertyMap = new ListMap<Property>();
	
	protected ListMap<Requirement> requirementMap = new ListMap<Requirement>();
	protected ListMap<Exposure> exposureMap = new ListMap<Exposure>();
	
	
	protected ListMap<Child> childMap = new ListMap<Child>();
	protected ListMap<Children> childrenMap = new ListMap<Children>();

	protected Single<Dynamics> dynamicsSingle = new Single<Dynamics>();
	protected Single<Structure> structureSingle = new Single<Structure>();
	protected Single<Simulation> simulationSingle = new Single<Simulation>();
	
	
	private ComponentType r_supertype = null;
	
	protected ComponentType(String s) {
		super(s);
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
	
	public Property addProperty(String s) {
		Property ret = new Property(this, s);
		propertyMap.put(s, ret);
		return ret;
	}

	public List<Property> getPropertys() {
		return propertyMap.getItems();
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
	

	public List<String> getReceivePortNames() {
		ArrayList<String> ret = new ArrayList<String>();
		for (ReceivePort rp : receivePortMap.getItems()) {
			ret.add(rp.getName());
		}
		return ret;
 	}

	public List<SendPort> getSendPorts() {
		return sendPortMap.getItems();
	}

	public List<String> getSendPortNames() {
		ArrayList<String> ret = new ArrayList<String>();
		for (SendPort rp : sendPortMap.getItems()) {
 			ret.add(rp.getName());
		}
		return ret;
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

	
	public boolean hasChild(String elt) {
		return childMap.containsKey(elt);
	}

	public Child getChild(String elt) {
		return childMap.get(elt);
	}
 
	public boolean hasChildren(String elt) {
		return childrenMap.containsKey(elt);
	}

	public Children getChildren(String elt) {
		return childrenMap.get(elt);
	}


	public int getChildCount() {
		return childMap.size();
	}
 
	public int getChildrenCount() {
		return childrenMap.size();
	}

	public String getChildNameForType(String tnm) {
		String ret = null;
		Child ch = getChildByType(tnm);
		if (ch != null) {
			ret = ch.getName();
		}
		return ret;
	}
	
	public String getChildrenNameForType(String tnm) {
		String ret = null;
		Children ch = getChildrenByType(tnm);
		if (ch != null) {
			ret = ch.getName();
		}
		return ret;
	}
	

	public boolean hasChildType(String tnm) {
		Child ch = getChildByType(tnm);
		boolean ret = false;
		if (ch != null) {
			ret = true;
		}
		return ret;
	}
	
	private Child getChildByType(String tnm) {	
		Child ret = null;
		for (Child ch : childMap.getItems()) {
			String tt = ch.getType();
			if (tt != null && tt.equals(tnm)) {
				ret = ch;
				break;
			}
		}
		return ret;
	}

	public boolean hasChildrenType(String tnm) {
		Children ch = getChildrenByType(tnm);
		boolean ret = false;
		if (ch != null) {
			ret = true;
		}
		return ret;
	}
	
	private Children getChildrenByType(String tnm) {	
		Children ret = null;
		for (Children ch : childrenMap.getItems()) {
			String tt = ch.getType();
			if (tt != null && tt.equals(tnm)) {
				ret = ch;
				break;
			}
		}
		return ret;
	}


	public String getSoleChildrenName() {
		String ret = null;
		if (childrenMap.size() == 0) {
			ret = childrenMap.get(0).getName();
		}
		return ret;
	}

	public String getSoleChildName() {
		String ret = null;
		if (childMap.size() == 0) {
			ret = childMap.get(0).getName();
		}
		return ret;
	}

	

	public Exposure getExposure(String expo) {
		return exposureMap.get(expo);
	}


	@Override
	public List<String> getParameterNames() {
		return parameterMap.getKeys();
	}


	@Override
	public List<String> getRequirementNames() {
		 return requirementMap.getKeys();
	}


	@Override
	public List<String> getExposureNames() {
		return exposureMap.getKeys();
	}


	@Override
	public List<String> getPropertyNames() {
		return propertyMap.getKeys();
	}


	@Override
	public List<String> getTextNames() {
		return textMap.getKeys();
	}


	@Override
	public String getFieldDimension(String rn) {
		String ret = null;
	
		if (parameterMap.containsKey(rn)) {
			ret = parameterMap.get(rn).getDimension();
			
		} else if (exposureMap.containsKey(rn)) {
			ret = exposureMap.get(rn).getDimension();
		
		} else if (requirementMap.containsKey(rn)) {
			ret = requirementMap.get(rn).getDimension();
		}
		
		return ret;
 	}


	@Override
	public IDynamics getIDynamics() {
		return getDynamics();
	}
	

	@Override
	public ISimulation getISimulation() {
		return getSimulation();
	}

	@Override
	public IStructure getIStructure() {
		return getStructure();
	}
}
