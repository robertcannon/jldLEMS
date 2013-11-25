package org.lemsml.jlems.core.lite;

import org.lemsml.jlems.core.lite.run.DiscreteUpdateStateInstance;
import org.lemsml.jlems.core.lite.run.DiscreteUpdateStateType;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.run.ConnectionError;
import org.lemsml.jlems.core.run.RuntimeError;
import org.lemsml.jlems.core.sim.ContentError;

public class InstanceArray {

	String name;
	
	int index;
	
	DiscreteUpdateStateInstance[] elements = null;
	
	
	public InstanceArray(String cnm) {
		name = cnm;
	}
	
	public String getName() {
		return name;
	}
	
	
	public void populate(DiscreteUpdateStateType duct, int n) throws ContentError, ConnectionError, RuntimeError {
		elements = new DiscreteUpdateStateInstance[n];
		for (int i = 0; i < n; i++) {
			elements[i] = duct.newStateInstance();
		}
	}

	public void setParameter(String p, double[] ds) throws ContentError {
		if (ds.length < elements.length) {
			throw new ContentError("Array too short for setting parameter: got " + ds.length + " need " + elements.length);
		}
		
		for (int i = 0; i < elements.length; i++) {
			elements[i].setLocalParameter(p, ds[i]);
		}
	}
	
	public void setVariable(String p, double[] ds) throws ContentError {
		if (ds.length < elements.length) {
			throw new ContentError("Array too short for setting parameter: got " + ds.length + " need " + elements.length);
		}
		
		for (int i = 0; i < elements.length; i++) {
			elements[i].setVariable(p, ds[i]);
		}
	}

	public void setIndex(int ctr) {
		index = ctr;
	}

	public int getIndex() {
		return index;
	}

	public void connectEventManager(EventManager em) {
		E.missing();
	}
	

}
