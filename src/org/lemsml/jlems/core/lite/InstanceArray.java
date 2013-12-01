package org.lemsml.jlems.core.lite;

import org.lemsml.jlems.core.display.DataViewer;
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
	
	

	public void setParameter(String p, double d) throws ContentError {
		for (int i = 0; i < elements.length; i++) {
			elements[i].setLocalParameter(p, d);
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
		for (int i = 0; i < elements.length; i++) {
			elements[i].setEventManager(em);
		}
	}

	public DiscreteUpdateStateInstance getElement(int ind) {
		 return elements[ind];
	}
	
	
	public void advance(double t, double dt) throws RuntimeError, ContentError {
		for (DiscreteUpdateStateInstance dusi : elements) {
			dusi.advance(null,  t,  dt);
		}
	}

	public int size() {
		 return elements.length;
	}

	public String getElementValues(String varname, int[] indices) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < indices.length; i++) {
			double v = elements[indices[i]].getValue(varname);
			sb.append("" + v + " ");
		}
		return sb.toString();
	}

	public void display(DataViewer dv, String varname, int[] indices, double t, String col) {
		for (int i = 0; i < indices.length; i++) {
			int ind = indices[i];
			double v = elements[ind].getValue(varname);
			dv.addPoint(name + "[" + ind + "]." + varname, t, v, col);
		}
	}


}
