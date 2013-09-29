package org.lemsml.jlems.core.discrete;

import java.util.ArrayList;

import org.lemsml.jlems.core.dimensionless.AccumulatingInputVariable;
import org.lemsml.jlems.core.dimensionless.FloatAssignment;
import org.lemsml.jlems.core.dimensionless.InputEventPort;
import org.lemsml.jlems.core.dimensionless.InputVariable;
import org.lemsml.jlems.core.dimensionless.OnEvent;
import org.lemsml.jlems.core.dimensionless.OnState;
import org.lemsml.jlems.core.dimensionless.OutputEventPort;
import org.lemsml.jlems.core.dimensionless.Parameter;
import org.lemsml.jlems.core.dimensionless.StateVariable;
import org.lemsml.jlems.core.dimensionless.VariableExposure;

public class DiscreteUpdateModel {

	String id;
	
	ArrayList<Parameter> parameters = new ArrayList<Parameter>();
	
	ArrayList<InputEventPort> inputEventPorts = new ArrayList<InputEventPort>();
	ArrayList<OutputEventPort> outputEventPorts = new ArrayList<OutputEventPort>();
	
	ArrayList<AccumulatingInputVariable> accumulatingInputVariables = new ArrayList<AccumulatingInputVariable>();
	
	ArrayList<InputVariable> inputVariables = new ArrayList<InputVariable>();
		 
	ArrayList<StateVariable> stateVariables = new ArrayList<StateVariable>();
	
	
	
	ArrayList<VariableExposure> variableExposures = new ArrayList<VariableExposure>();
	
	ArrayList<FloatAssignment> floatAssignments = new ArrayList<FloatAssignment>();

	ArrayList<FloatAssignment> updateAssignments = new ArrayList<FloatAssignment>(); 
	
	ArrayList<OnEvent> onEvents = new ArrayList<OnEvent>();

	ArrayList<OnState> onStates = new ArrayList<OnState>();
	
	
	boolean includeRP = false;
	
	
	public DiscreteUpdateModel(String id) {
		this.id = id;
	}
	
	
	public String getID() {
		return id;
	}
	
	
	
	public void addStateVariable(String s) {
		stateVariables.add(new StateVariable(s));
	}
	
	public void addIfNewStateVariable(String s) {
		boolean got = false;
		for (StateVariable sv : stateVariables) {
			if (sv.getName().equals(s)) {
				got = true;
			}
		}
		if (!got) {
			addStateVariable(s);
		}
 	}
	


	
	
	public void addFloatExposure(String var, String as) {
		variableExposures.add(new VariableExposure(var, as));
 	}

	
	public void addIndependentVariagble(String s) {
		inputVariables.add(new InputVariable(s));
	}

	public void addFloatAssignment(String variableName, String expressionString) {
		FloatAssignment fa = new FloatAssignment(variableName, expressionString);
		floatAssignments.add(fa);
		
	}
	
	public void addFloatAssignment(FloatAssignment fa) {
		floatAssignments.add(fa);
	}
	
	
	public void addUpdateFloatAssignment(String variableName, String expressionString) {
		FloatAssignment fa = new FloatAssignment(variableName, expressionString);
		updateAssignments.add(fa);
		
	}

	public void addUpdateFloatAssignment(FloatAssignment fa) {
		updateAssignments.add(fa);
	}
	
	 
	public ArrayList<StateVariable> getStateVariables() { 
		return stateVariables;
	}
	
	public ArrayList<VariableExposure> getVariableExposures() {
		return variableExposures; 
	}
	
	public ArrayList<FloatAssignment> getFloatAssignments() {
		return floatAssignments;  
	}

	public ArrayList<FloatAssignment> getUpdateAssignments() {
		return updateAssignments; 
	}


	public OnEvent addOnEvent(String portName) {
	 	 OnEvent oe = new OnEvent(portName);
	 	 onEvents.add(oe);
	 	 return oe;
	}

	public void addOnEvent(OnEvent oe) {
		onEvents.add(oe);
	}




	public OnState addOnState(String estr) {
		 OnState os = new OnState(estr);
		 onStates.add(os);
		 return os;
	}

	public void addOnState(OnState os) {
		onStates.add(os);
	}
	

	public ArrayList<InputVariable> getInputVariables() {
		return inputVariables;
	}


	public boolean hasStateVariable(String str) {
		boolean ret = false;
		for (StateVariable sv : stateVariables) {
			if (str.equals(sv.getName())) {
				ret = true;
			}
		}
		return ret;
	}


 
	
	
	
}
