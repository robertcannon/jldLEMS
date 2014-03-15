package org.lemsml.jlems.core.lite.model;

import java.util.HashMap;

import org.lemsml.jlems.core.type.LemsCollection;

public class Interface {

	
	
	public LemsCollection<Parameter> parameters = new LemsCollection<Parameter>();

	public LemsCollection<Constant> constants = new LemsCollection<Constant>();
	
	public LemsCollection<InputVariable> inputVariables = new LemsCollection<InputVariable>();

	public LemsCollection<OutputVariable> outputVariables = new LemsCollection<OutputVariable>();

	public LemsCollection<InputEventPort> inputEventPorts = new LemsCollection<InputEventPort>();

	public LemsCollection<OutputEventPort> outputEventPorts = new LemsCollection<OutputEventPort>();
	
	
	

	
	
	public void addOutputVariable(OutputVariable ov) {
		outputVariables.add(ov);
	}
	
	public void addInputVariable(InputVariable ov) {
		inputVariables.add(ov);
	}

	public LemsCollection<OutputVariable> getOutputVariables() {
		 return outputVariables;
	}

	public LemsCollection<InputVariable> getInputVariables() {
		return inputVariables;
	}

	public LemsCollection<Parameter> getParameters() {
		 return parameters;
	}

	public LemsCollection<Constant> getConstants() {
		 return constants;
	}

	
}
