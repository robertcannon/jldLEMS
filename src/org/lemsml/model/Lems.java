package org.lemsml.model;
 
import java.util.HashMap;
 

// NB this should be the only way to access lems components from outside - 
// no direct access to Component or ComponentType objects so we can refactor 
// them and not worry about breaking the API.
public class Lems {
 
	// track the last element we added to avoid doing things out of sequence which 
	// is likely to be an error
	AbstractElement focusElement;
	
	protected ListMap<Target> targetMap = new ListMap<Target>();

	protected ListMap<Dimension> dimensionMap = new ListMap<Dimension>();
	
	protected ListMap<Unit> unitMap = new ListMap<Unit>();

	protected ListMap<Constant> constantMap = new ListMap<Constant>();
	
	protected ListMap<ComponentType> componentTypeMap = new ListMap<ComponentType>();
	
	
	
	protected ComponentMap componentMap = new ComponentMap();
	
	
	public Lems() {
 	}
	
	
	public Dimension addDimension(String name) {
		Dimension ret = new Dimension(this, name);
		dimensionMap.put(name, ret);
		return ret;
	}
	
	public Unit addUnit(String s) {
		Unit ret = new Unit(this, s);
		unitMap.put(s, ret);
		return ret;
	}


	public ComponentType addComponentType(String s) {
		ComponentType ret = new ComponentType(this, s);
		componentTypeMap.put(s, ret);
		return ret;
	}

	


	public ComponentType addExtendingComponentType(String string,
			ComponentType lct) {
		// TODO Auto-generated method stub
		return null;
	}


	public void setFocusElement(AbstractElement le) {
		focusElement = le;
	}


	public Unit addUnit(String s, Dimension d, int p, String sym) {
		Unit ret = addUnit(s);
		ret.setDimension(d);
		ret.setPower(p);
		ret.setSymbol(sym);
		return ret;
		
	}

	
	public Component addComponent(String id) {
		Component ret = new Component(this, id);
		componentMap.put(id, ret);
		return ret;
	}

	public Component addComponent(String sid, String tn) {
		Component ret = addComponent(sid);
		ret.setTypeName(tn);
		return ret;
	}


	public Target addTarget(String eltname) {
		Target ret = new Target(this, eltname);
		targetMap.put(eltname, ret);
		return ret;
	}
	
	public Constant addConstant(String eltname) {
		Constant ret = new Constant(this, eltname);
		constantMap.put(eltname, ret);
		return ret;
	}
	
}
