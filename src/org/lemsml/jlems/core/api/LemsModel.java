package org.lemsml.jlems.core.api;
 
import java.util.HashMap;

import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.type.Dimension;
import org.lemsml.jlems.core.type.Lems;
 


// NB this should be the only way to access lems components from outside - 
// no direct access to Component or ComponentType objects so we can refactor 
// them and not worry about breaking the API.
public class LemsModel {
 
	// track the last element we added to avoid doing things out of sequence which 
	// is likely to be an error
	LemsElement focusElement;
	
	
	HashMap<String, LemsDimension> dimensionHM = new HashMap<String, LemsDimension>();

	public LemsModel() {
 	}
	
	
	public LemsDimension addDimension(String name) {
		LemsDimension ret = new LemsDimension(this, name);
		dimensionHM.put(name, ret);
		return ret;
	}
	
	public LemsUnit addUnit(String s) {
		LemsUnit ret = new LemsUnit(s);
		return ret;
	}


	public LemsComponentType addComponentType(String s) {
		LemsComponentType ret = new LemsComponentType(s);
		return ret;
	}




	public LemsComponentType addExtendingComponentType(String string,
			LemsComponentType lct) {
		// TODO Auto-generated method stub
		return null;
	}


	public void setFocusElement(LemsElement le) {
		focusElement = le;
	}
	
	
}
