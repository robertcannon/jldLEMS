package org.lemsml.jlems.core.dimensionless;

import org.lemsml.jlems.core.type.LemsCollection;

public class Interface {

	
	
	public LemsCollection<Parameter> parameters = new LemsCollection<Parameter>();
	
	public LemsCollection<InputEventPort> inputEventPorts = new LemsCollection<InputEventPort>();

	public LemsCollection<OutputEventPort> outputEventPorts = new LemsCollection<OutputEventPort>();
	
	public LemsCollection<Constant> constants = new LemsCollection<Constant>();
	
	public LemsCollection<OutputVariable> outputVariables = new LemsCollection<OutputVariable>();
	
	public LemsCollection<InputVariable> inputVariables = new LemsCollection<InputVariable>();

	
	
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
	
	
}
