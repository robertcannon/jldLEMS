package org.lemsml.jlems.core.lite.model;
 

import org.lemsml.jlems.core.type.LemsCollection;

public class Interface {

	
	public LemsCollection<InputEventPort> inputEventPorts = new LemsCollection<InputEventPort>();
	
	public LemsCollection<OutputEventPort> outputEventPorts = new LemsCollection<OutputEventPort>();

	public LemsCollection<Parameter> parameters = new LemsCollection<Parameter>();

	public LemsCollection<Constant> constants = new LemsCollection<Constant>();
	
	public LemsCollection<InputVariable> inputVariables = new LemsCollection<InputVariable>();

	public LemsCollection<RecordableVariable> recordableVariables = new LemsCollection<RecordableVariable>();


	
	
	

	
	
	public void addRecordableVariable(RecordableVariable ov) {
		recordableVariables.add(ov);
	}
	
	public void addInputVariable(InputVariable ov) {
		inputVariables.add(ov);
	}

	public LemsCollection<RecordableVariable> getRecordableVariables() {
		 return recordableVariables;
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

	public void addInputEvent(String portName) {
		InputEventPort iep = new InputEventPort(portName);
		inputEventPorts.add(iep);
	}

	public void ensureOutPort(String pn) {
		boolean got = false;
		for (OutputEventPort oep : outputEventPorts) {
			if (oep.getName().equals(pn)) {
				got = true;
			}
		}
		if (!got) {
			OutputEventPort oep = new OutputEventPort(pn);
			outputEventPorts.add(oep);
		}
	}
	
}
