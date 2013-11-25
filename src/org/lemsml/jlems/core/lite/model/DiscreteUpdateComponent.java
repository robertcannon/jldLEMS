package org.lemsml.jlems.core.lite.model;

import org.lemsml.jlems.core.dimensionless.AccumulatingInputVariable;
import org.lemsml.jlems.core.dimensionless.FloatAssignment;
import org.lemsml.jlems.core.dimensionless.InputEventPort;
import org.lemsml.jlems.core.dimensionless.InputVariable;
import org.lemsml.jlems.core.dimensionless.Interface;
import org.lemsml.jlems.core.dimensionless.OnCondition;
import org.lemsml.jlems.core.dimensionless.OnEvent;
 
import org.lemsml.jlems.core.dimensionless.Output;
import org.lemsml.jlems.core.dimensionless.OutputEventPort;
import org.lemsml.jlems.core.dimensionless.Parameter;
import org.lemsml.jlems.core.dimensionless.State;
import org.lemsml.jlems.core.dimensionless.StateVariable;
import org.lemsml.jlems.core.dimensionless.Step;
import org.lemsml.jlems.core.dimensionless.OutputVariable;
import org.lemsml.jlems.core.dimensionless.Update;
import org.lemsml.jlems.core.dimensionless.Var;
import org.lemsml.jlems.core.type.LemsCollection;


public class DiscreteUpdateComponent {
	String id;
	
	public String name;
	
	public LemsCollection<Interface> interfaces = new LemsCollection<Interface>();
	
	public LemsCollection<State> states = new LemsCollection<State>();
	
	public LemsCollection<Step> steps = new LemsCollection<Step>();
	
	 
	public LemsCollection<OnEvent> onEvents = new LemsCollection<OnEvent>();

	public LemsCollection<OnCondition> onConditions = new LemsCollection<OnCondition>();
	
	
	boolean includeRP = false;
	
	
	public DiscreteUpdateComponent(String sn) {
		name = sn;
	}
	
	
	public DiscreteUpdateComponent() {
	 
	}


	public String getID() {
		return id;
	}
	
	
	private State getState() {
		if (states.size() == 0) {
			states.add(new State());
		}
		State ret = states.get(0);
		return ret;
	}
	
	
	private Interface getInterface() {
		if (interfaces.size() == 0) {
			interfaces.add(new Interface());
		}
		Interface ret = interfaces.get(0);
		return ret;
	}
	
	
	private Step getStep() {
		if (steps.size() == 0) {
			steps.add(new Step());
		}
		Step ret = steps.get(0);
		return ret;
	}
	
	
	public void addStateVariable(String s) {
		getState().addStateVariable(s);
	}
	
	
	
	public void addIfNewStateVariable(String s) {
		getState().addIfNewStateVariable(s);
	
 	}
	
 
	
	public void addFloatExposure(String var, String as) {
		getInterface().addOutputVariable(new OutputVariable(as));
		getStep().addOutput(as, var);
	}

	
	public void addIndependentVariagble(String s) {
		getInterface().addInputVariable(new InputVariable(s));
	}

	public void addFloatAssignment(String variableName, String expressionString) {
		FloatAssignment fa = new FloatAssignment(variableName, expressionString);
		getStep().addUpdateAssignment(fa);
		
	}
	
	public void addFloatAssignment(FloatAssignment fa) {
		getStep().addUpdateAssignment(fa);
	}
	
	
	public void addUpdateFloatAssignment(String variableName, String expressionString) {
		FloatAssignment fa = new FloatAssignment(variableName, expressionString);
		getStep().addUpdateAssignment(fa);
		
	}

	public void addUpdateFloatAssignment(FloatAssignment fa) {
		getStep().addUpdateAssignment(fa);
	}
	
	 
	public LemsCollection<StateVariable> getStateVariables() { 
		return getState().getStateVariables();
	}
	
	public LemsCollection<OutputVariable> getVariableExposures() {
		return getInterface().getOutputVariables(); 
	}
	 
	public LemsCollection<Update> getUpdateAssignments() {
		return getStep().getUpdates(); 
	}


	public LemsCollection<Var> getVars() {
		return getStep().getVars();
	}

	

	public OnEvent addOnEvent(String portName) {
	 	 OnEvent oe = new OnEvent(portName);
	 	 onEvents.add(oe);
	 	 return oe;
	}

	public void addOnEvent(OnEvent oe) {
		onEvents.add(oe);
	}




	public OnCondition addOnCondition(String estr) {
		 OnCondition os = new OnCondition(estr);
		 onConditions.add(os);
		 return os;
	}

	public void addOnCondition(OnCondition os) {
		onConditions.add(os);
	}
	

	public LemsCollection<InputVariable> getInputVariables() {
		return getInterface().getInputVariables();
	}


	public boolean hasStateVariable(String str) {
		boolean ret = getState().hasStateVariable(str);
		
		return ret;
	}


	public LemsCollection<Output> getOutputs() {
		return getStep().getOutputs();
	}


	public String getName() {
		return name;
	}



}
