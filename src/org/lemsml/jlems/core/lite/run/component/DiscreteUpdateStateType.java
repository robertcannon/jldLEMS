package org.lemsml.jlems.core.lite.run.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.run.ConnectionError;
import org.lemsml.jlems.core.run.DoublePointer;
import org.lemsml.jlems.core.run.RuntimeError;
import org.lemsml.jlems.core.run.RuntimeType;
import org.lemsml.jlems.core.run.StateRunnable;
import org.lemsml.jlems.core.sim.ContentError;


public class DiscreteUpdateStateType implements RuntimeType {
 
	
	public String id;
	
	boolean resolved = false;

	ArrayList<Fetch> fetches = new ArrayList<Fetch>();
	
	ArrayList<Assignment> assignments = new ArrayList<Assignment>();
	
	ArrayList<Copy> exposures = new ArrayList<Copy>();
	
	ArrayList<Condition> conditions = new ArrayList<Condition>();
	
	HashMap<String, EventResponse> eventHM = new HashMap<String, EventResponse>();
	
	
	HashMap<String, Double> constants = new HashMap<String, Double>();
	HashSet<String> parameters = new HashSet<String>();
	HashSet<String> inputs = new HashSet<String>();
	HashSet<String> stateVariables = new HashSet<String>();
	 
	
	 
	
	
	public String toString() {
		String ret = "DU type, id=" + getID();
		return ret;
	}
	
	
	public void setID(String s) {
		id = s;
	}
	
	public String getID() {
		return id;
	}

	 
	
	public void addFetch(Fetch f) {
		fetches.add(f);
	}
	
	public void addAssignment(Assignment a) {
		assignments.add(a);
	}
	
	public void addExposure(Copy e) {
		exposures.add(e);
	}
	
	public void addCondition(Condition c) {
		conditions.add(c);
	}
	
	public void addEvent(String pnm, EventResponse er) {
		eventHM.put(pnm, er);
	}
	 
	public void addConstant(String s, double d) {
		constants.put(s, d);
	}
	
	public void addParameter(String s) {
		parameters.add(s);
	}
	
	public void addInputVariable(String s) {
		inputs.add(s);
	}
	
	public void addStateVariable(String s) {
		stateVariables.add(s);
	}
	
	@Override
	public StateRunnable newStateRunnable() throws ContentError, ConnectionError, RuntimeError {
		StateRunnable ret=  newStateInstance();
		return ret;
	}
	
	
	public DiscreteUpdateStateInstance newStateInstance() throws ContentError, ConnectionError, RuntimeError {
 		
	
		
		HashMap<String, DoublePointer> variables = new HashMap<String, DoublePointer>();
	 

		for (String s : constants.keySet()) {
			DoublePointer dp = new DoublePointer(constants.get(s));
			variables.put(s, dp);
		}
		
		for (String p : parameters) {
			DoublePointer dp = new DoublePointer(0.);
			dp.setUnassigned();
			variables.put(p, dp);
		}
		
		for (String s : inputs) {
			variables.put(s, new DoublePointer(0.));
		}
		
		for (String s : stateVariables) {
			variables.put(s, new DoublePointer(0.));
		}
	
		for (Assignment a : assignments) {
			variables.put(a.getVariableName(), new DoublePointer(0.));
		}
		
		 
		variables.put("dt", new DoublePointer(0.));
		
		DiscreteUpdateStateInstance dur = new DiscreteUpdateStateInstance(this, variables);
		 
		
		return dur;
		
	}
	
	
	public void advance(double dt, HashMap<String, DoublePointer> variables, 
			DiscreteUpdateStateInstance src, StateRunnable parent) throws RuntimeError {
		variables.get("dt").set(dt);
		
		for (Fetch f : fetches) {
			f.exec(variables, parent);
		}
		
		
		for (Assignment a : assignments) {
			a.exec(variables);
		}
		
		for (Copy c : exposures) {
			c.exec(variables);
		}
		
		for (Condition c : conditions) {
			boolean b = c.exec(variables);
			if (b) {
				
				ActionBlock ab = c.getActionBlock();
				if (ab != null) {
					execActionBlock(ab, variables, src);
				}
			}
		}
	}


	public void handleEvent(DiscreteUpdateStateInstance dusi, String tgtPort,
								HashMap<String, DoublePointer> variables) throws RuntimeError {
		if (eventHM.containsKey(tgtPort)) {
			EventResponse er = eventHM.get(tgtPort);
			ActionBlock ab = er.getActionBlock();
			if (ab != null) {
				execActionBlock(ab, variables, dusi);
			} else {
				E.warning("No action block?");
			}
		} else {
			E.warning("No handler for event on port " + tgtPort);
		}
	}
	
	
	
	
	private void execActionBlock(ActionBlock ab, HashMap<String, DoublePointer> variables, DiscreteUpdateStateInstance src) throws RuntimeError {
		for (Assignment a : ab.getAssignments()) {
			a.exec(variables);
		}
		for (EmitAction ea : ab.getEmitActions()) {
			src.sendEvent(ea.getPort());
		}
	}
	
	
	
	public boolean hasStateVariable(String str) {
		boolean ret = false;
		if (stateVariables.contains(str)) {
			ret = true;
		}
		return ret;
	}


	public boolean hasParameter(String pnm) {
		boolean ret = false;
		if (parameters.contains(pnm)) {
			ret = true;
		}
		return ret;
	}
	
	
}
