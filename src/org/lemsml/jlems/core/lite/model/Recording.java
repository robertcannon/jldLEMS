package org.lemsml.jlems.core.lite.model;

import org.lemsml.jlems.core.type.LemsCollection;

public class Recording {

	public double startTime;
	
	public double endTime;

	public double interval;
	
	public LemsCollection<VariableRecording> variableRecordings = new LemsCollection<VariableRecording>();
	
	public LemsCollection<InputEventRecording> inputEventRecordings = new LemsCollection<InputEventRecording>();
	
	public LemsCollection<OutputEventRecording> outputEventRecordings = new LemsCollection<OutputEventRecording>();

	public LemsCollection<VariableRecording> getVariableRecordings() {
		return variableRecordings;
	}
 
 
	
	
}
