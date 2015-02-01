package org.lemsml.jld.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lemsml.jld.imodel.IComponent;
import org.lemsml.jld.imodel.IComponentType;
import org.lemsml.jld.io.E;
import org.lemsml.jld.model.core.ListMap;
import org.lemsml.jld.model.core.Quantity;
import org.lemsml.jld.model.type.ComponentType;


public class Component implements IComponent {

	protected String element;   // "Component", or the type ("iaf"), or the role in the parent ("Forward")

	protected String id;
	protected String type;
	protected String eXtends;
	
	protected ListMap<ParameterValue> parameterValueMap = new ListMap<ParameterValue>();
	
	protected ListMap<Component> componentMap = new ListMap<Component>();
 	
	
	// after resolving, everything in componentMap should be in childHM or childrenHM
	private HashMap<String, Component> childHM = new HashMap<String, Component>();
	private HashMap<String, ArrayList<Component>> childrenHM = new HashMap<String, ArrayList<Component>>();
	
	
	
	private ComponentType r_componentType;
	private Component r_parent;
	
	
	protected Component(String id) {
		this.id = id;
	}
	 
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String toString() {
		return "Component, elt=" + element + ", type=" + type + ", id=" + id + ", extends=" + eXtends;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setElement(String element) {
		this.element = element;
	}
	
	
	public void setParameterValue(String sn, String sv) {
		ParameterValue pv = new ParameterValue(sn, sv);
		parameterValueMap.put(sn,  pv);
	}


	public void setParameterValue(String sn, Quantity q) {
		// TODO Auto-generated method stub
		E.missing();
	}

 

	public Component addComponent(String id) {
		Component ret = new Component(id);
		ret.r_parent = this;
		componentMap.put(id,  ret);
		return ret;
	}

	public Component getParent() {
		return r_parent;
	}

	public void setParent(Component cpt) {
		 r_parent = cpt;
	}

	public String getType() {
		return type;
	}
	
	public String getId() {
		return id;
	}
	
	public String getElement() {
		return element;
	}


	public List<ParameterValue> getParameterValues() {
		 return parameterValueMap.getItems();
	}

	@Override
	public double getNumericalParameterValue(String s) {	
		return parameterValueMap.get(s).getDoubleValue();
	}
	
	
	@Override
	public String getStringParameterValue(String s) {
		String ret = null;
		ParameterValue pv = parameterValueMap.get(s);
		if (pv != null) {
			ret = pv.getValue();
		} 
		return ret;
	}
	


	public List<Component> getComponents() {
		return componentMap.getItems();
	}

 
	public void setExtends(String s) {
		eXtends = s;
	}
	
	public String getExtends() {
		return eXtends;
	}

	public boolean hasParameter(String name) {
		boolean ret = false;
		if (parameterValueMap.containsKey(name)) {
			ret = true;
		}
		return ret;
	}

	public void setComponentType(ComponentType ct) {
		r_componentType = ct;
	}

	public ComponentType getComponentType() {
		return r_componentType;
	}

	public IComponentType getIComponentType() {
		return r_componentType;
	}
	
	
	public void allocateToChild(Component cpt, String elt) {
		 if (childHM.containsKey(elt)) {
			 E.error("Tried to overwriting child " + elt + " in " + this);
		 } else {
			 childHM.put(elt, cpt);
		 }
	}

	public void allocateToChildren(Component cpt, String elt) {
		 if (!childrenHM.containsKey(elt)) {
			 childrenHM.put(elt, new ArrayList<Component>());
		 }
		 childrenHM.get(elt).add(cpt);
	}

	public List<Component> getAllSubcomponents() {
		 ArrayList<Component> ret = new ArrayList<Component>();
		 for (String s : childHM.keySet()) {
			 ret.add(childHM.get(s));
		 }
		 for (String s : childrenHM.keySet()) {
			 ret.addAll(childrenHM.get(s));
		 }
		 return ret;
	}


	public ParameterValue getParameterValue(String name) {
		return parameterValueMap.get(name);
	}

	
	
	public List<String> getChildNames() {
		ArrayList<String> ret = new ArrayList<String>();
		// List or Set = do we ever need to care about the order?
		ret.addAll(childHM.keySet());
		return ret;
	}
	
	public List<String> getChildrenNames() {
		ArrayList<String> ret = new ArrayList<String>();
		// List? as above
		ret.addAll(childrenHM.keySet());
		return ret;
	}
	

	public Component getChild(String s) {
		Component ret = null;
		if (childHM.containsKey(s)) {
			ret = childHM.get(s);
		}
		return ret;
	}
	
	public IComponent getIChild(String s) {
		return getChild(s);
	}
	

	public List<Component> getChildren(String s) {
		return childrenHM.get(s);
	}
	
	
	public List<? extends IComponent> getIChildren(String s) {
		return getChildren(s);
	}
	
	
}
