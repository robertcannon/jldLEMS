package org.lemsml.jlems.schema;

import java.util.ArrayList;

import org.lemsml.jlems.core.lite.model.Arg;
import org.lemsml.jlems.core.lite.model.Array;
import org.lemsml.jlems.core.lite.model.ComponentArray;
import org.lemsml.jlems.core.lite.model.ConditionCheck;
import org.lemsml.jlems.core.lite.model.ConnectionProperties;
import org.lemsml.jlems.core.lite.model.Constant;
import org.lemsml.jlems.core.lite.model.DataSources;
import org.lemsml.jlems.core.lite.model.Delay;
import org.lemsml.jlems.core.lite.model.DiscreteUpdateComponent;
import org.lemsml.jlems.core.lite.model.Display;
import org.lemsml.jlems.core.lite.model.Emit;
import org.lemsml.jlems.core.lite.model.EventArguments;
import org.lemsml.jlems.core.lite.model.EventConnections;
import org.lemsml.jlems.core.lite.model.EventSource;
import org.lemsml.jlems.core.lite.model.EventTarget;
import org.lemsml.jlems.core.lite.model.EventTimes;
import org.lemsml.jlems.core.lite.model.File;
import org.lemsml.jlems.core.lite.model.FileSource;
import org.lemsml.jlems.core.lite.model.FromArrayConnector;
import org.lemsml.jlems.core.lite.model.IfCondition;
import org.lemsml.jlems.core.lite.model.Initialize;
import org.lemsml.jlems.core.lite.model.InputEventPort;
import org.lemsml.jlems.core.lite.model.InputEventRecording;
import org.lemsml.jlems.core.lite.model.Interface;
import org.lemsml.jlems.core.lite.model.LemsLite;
import org.lemsml.jlems.core.lite.model.Let;
import org.lemsml.jlems.core.lite.model.ListSource;
import org.lemsml.jlems.core.lite.model.OnCondition;
import org.lemsml.jlems.core.lite.model.OnEvent;
import org.lemsml.jlems.core.lite.model.Output;
import org.lemsml.jlems.core.lite.model.OutputEventPort;
import org.lemsml.jlems.core.lite.model.OutputEventRecording;
import org.lemsml.jlems.core.lite.model.OutputFiles;
import org.lemsml.jlems.core.lite.model.Parameter;
import org.lemsml.jlems.core.lite.model.Property;
import org.lemsml.jlems.core.lite.model.RecordableVariable;
import org.lemsml.jlems.core.lite.model.Recording;
import org.lemsml.jlems.core.lite.model.Simulation;
import org.lemsml.jlems.core.lite.model.SourceTargetConnector;
import org.lemsml.jlems.core.lite.model.State;
import org.lemsml.jlems.core.lite.model.StateVariable;
import org.lemsml.jlems.core.lite.model.Step;
import org.lemsml.jlems.core.lite.model.TargetIndices;
import org.lemsml.jlems.core.lite.model.TimedEvents;
import org.lemsml.jlems.core.lite.model.Update;
import org.lemsml.jlems.core.lite.model.Var;
import org.lemsml.jlems.core.lite.model.VariableDisplay;
import org.lemsml.jlems.core.lite.model.VariableRecording;
 

public final class LemsLiteClasses {

	
	public static LemsLiteClasses instance;
	
	private final ArrayList<LemsClass> classList;
	
	
	public static LemsLiteClasses getInstance() {
		synchronized(LemsLiteClasses.class) {
		if (instance == null) {
			instance = new LemsLiteClasses();
		}
		}
		return instance;
	}
	
	
	
	private LemsLiteClasses() {
		classList = new ArrayList<LemsClass>();
	
		classList.addAll(getLemsLiteClasses());
		classList.addAll(getDiscreteUpdateComponentClasses());
		classList.addAll(getNetworkClasses());
		classList.addAll(getSimulationClasses());
	 
	}

	public ArrayList<LemsClass> getClasses() {
 		return classList;
	}

	

	private ArrayList<LemsClass> getLemsLiteClasses() {
		ArrayList<LemsClass> ret =  new ArrayList<LemsClass>();
		String section = "root";
		ret.add(new LemsClass(LemsLite.class, section));
		
		return ret;
	}
	
	
	private ArrayList<LemsClass> getDiscreteUpdateComponentClasses() {
		ArrayList<LemsClass> ret =  new ArrayList<LemsClass>();
		String section = "component";
		ret.add(new LemsClass(DiscreteUpdateComponent.class, section));
		ret.add(new LemsClass(Interface.class, section));
		ret.add(new LemsClass(Parameter.class, section));
		ret.add(new LemsClass(InputEventPort.class, section));
		ret.add(new LemsClass(OutputEventPort.class, section));
		ret.add(new LemsClass(Constant.class, section));
	 	ret.add(new LemsClass(RecordableVariable.class, section));	
	 	ret.add(new LemsClass(State.class, section));
	 	ret.add(new LemsClass(StateVariable.class, section));
	 	ret.add(new LemsClass(Step.class, section));
	 	ret.add(new LemsClass(Var.class, section));
	 	ret.add(new LemsClass(Update.class, section));
	 	ret.add(new LemsClass(ConditionCheck.class, section));
	 	ret.add(new LemsClass(Output.class, section));

	 	ret.add(new LemsClass(OnEvent.class, section));
	 	ret.add(new LemsClass(OnCondition.class, section));
	 	ret.add(new LemsClass(Emit.class, section));
	 	ret.add(new LemsClass(IfCondition.class, section));
		return ret;
	}
	
	
	

	private ArrayList<LemsClass> getNetworkClasses() {
		ArrayList<LemsClass> ret =  new ArrayList<LemsClass>();
		String section = "network";
		ret.add(new LemsClass(ComponentArray.class, section));
		ret.add(new LemsClass(EventConnections.class, section));
		ret.add(new LemsClass(Let.class, section));
		ret.add(new LemsClass(Initialize.class, section));
		ret.add(new LemsClass(EventSource.class, section));
		ret.add(new LemsClass(EventTarget.class, section));
		
		ret.add(new LemsClass(SourceTargetConnector.class, section));
		ret.add(new LemsClass(FromArrayConnector.class, section));
	
		ret.add(new LemsClass(ConnectionProperties.class, section));
		ret.add(new LemsClass(Property.class, section));
		ret.add(new LemsClass(Delay.class, section));
		
		ret.add(new LemsClass(EventArguments.class, section));
		ret.add(new LemsClass(Arg.class, section));
		
		ret.add(new LemsClass(EventTimes.class, section));
		ret.add(new LemsClass(TimedEvents.class, section));
		ret.add(new LemsClass(TargetIndices.class, section));
	
		return ret;
	}

	

	private ArrayList<LemsClass> getSimulationClasses() {
		ArrayList<LemsClass> ret =  new ArrayList<LemsClass>();
		String section = "simulation";
		ret.add(new LemsClass(DataSources.class, section));
		ret.add(new LemsClass(ListSource.class, section));
		ret.add(new LemsClass(File.class, section));
		ret.add(new LemsClass(Array.class, section));
		ret.add(new LemsClass(FileSource.class, section));
		
		ret.add(new LemsClass(Simulation.class, section));
		ret.add(new LemsClass(OutputFiles.class, section));
		ret.add(new LemsClass(Recording.class, section));
		ret.add(new LemsClass(VariableRecording.class, section));
		ret.add(new LemsClass(InputEventRecording.class, section));
		ret.add(new LemsClass(OutputEventRecording.class, section));
		ret.add(new LemsClass(Display.class, section));
		ret.add(new LemsClass(VariableDisplay.class, section));
		return ret;
	}
	
 
}
