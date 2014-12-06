package org.lemsml.jlems.core.api;
 
import java.util.HashMap;
 

// NB this should be the only way to access lems components from outside - 
// no direct access to Component or ComponentType objects so we can refactor 
// them and not worry about breaking the API.
public class Lems {
 
	// track the last element we added to avoid doing things out of sequence which 
	// is likely to be an error
	AbstractElement focusElement;
	
	
	HashMap<String, Dimension> dimensionHM = new HashMap<String, Dimension>();

	HashMap<String, Unit> unitHM = new HashMap<String, Unit>();
	
	HashMap<String, ComponentType> typeHM = new HashMap<String, ComponentType>();
	
	HashMap<String, Component> cptHM = new HashMap<String, Component>();
	
	public Lems() {
 	}
	
	
	public Dimension addDimension(String name) {
		Dimension ret = new Dimension(this, name);
		dimensionHM.put(name, ret);
		return ret;
	}
	
	public Unit addUnit(String s) {
		Unit ret = new Unit(this, s);
		unitHM.put(s, ret);
		return ret;
	}


	public ComponentType addComponentType(String s) {
		ComponentType ret = new ComponentType(this, s);
		typeHM.put(s, ret);
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
		cptHM.put(id,ret);
		return ret;
	}

	public Component addComponent(String sid, String tn) {
		Component ret = addComponent(sid);
		ret.setTypeName(tn);
		return ret;
	}
	
	
}
