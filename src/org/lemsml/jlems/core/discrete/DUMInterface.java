package org.lemsml.jlems.core.discrete;

import java.util.HashSet;

public class DUMInterface {
	
	HashSet<String> parameters = new HashSet<String>();
	HashSet<String> inputEventPorts = new HashSet<String>();
	HashSet<String> outputEventPorts = new HashSet<String>();
	HashSet<String> accumulatingInputVariables = new HashSet<String>();
	HashSet<String> inputVariables = new HashSet<String>();
	HashSet<String> outputVariables = new HashSet<String>();
	

	public void addParameter(String enm) {
		parameters.add(enm);
		
	}

	public void addInputEventPort(String enm) {
		inputEventPorts.add(enm);	
	}

	public void addOutputEventPort(String enm) {
		outputEventPorts.add(enm);		
	}

	public void addAccumulatingInputVariable(String enm) {
		accumulatingInputVariables.add(enm);
		
	}

	public void addInputVariable(String enm) {
		inputVariables.add(enm);
	}

	public void addOutputVariable(String enm) {
		outputVariables.add(enm);	
	}

}
