package org.lemsml.jld.io.generation;

import java.util.ArrayList;

import org.lemsml.jld.model.Assertion;
import org.lemsml.jld.model.Component;
import org.lemsml.jld.model.Constant;
import org.lemsml.jld.model.Dimension;
import org.lemsml.jld.model.Insertion;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.Target;
import org.lemsml.jld.model.Unit;
import org.lemsml.jld.model.dynamics.ConditionalDerivedVariable;
import org.lemsml.jld.model.dynamics.DerivedParameter;
import org.lemsml.jld.model.dynamics.DerivedScalarField;
import org.lemsml.jld.model.dynamics.DerivedVariable;
import org.lemsml.jld.model.dynamics.Dynamics;
import org.lemsml.jld.model.dynamics.EventOut;
import org.lemsml.jld.model.dynamics.KineticScheme;
import org.lemsml.jld.model.dynamics.OnCondition;
import org.lemsml.jld.model.dynamics.OnEntry;
import org.lemsml.jld.model.dynamics.OnEvent;
import org.lemsml.jld.model.dynamics.OnStart;
import org.lemsml.jld.model.dynamics.Regime;
import org.lemsml.jld.model.dynamics.StateAssignment;
import org.lemsml.jld.model.dynamics.StateVariable;
import org.lemsml.jld.model.dynamics.Super;
import org.lemsml.jld.model.dynamics.TimeDerivative;
import org.lemsml.jld.model.dynamics.Transition;
import org.lemsml.jld.model.simulation.DataDisplay;
import org.lemsml.jld.model.simulation.DataWriter;
import org.lemsml.jld.model.simulation.Recording;
import org.lemsml.jld.model.simulation.Run;
import org.lemsml.jld.model.simulation.Simulation;
import org.lemsml.jld.model.structure.EventConnection;
import org.lemsml.jld.model.structure.ForEach;
import org.lemsml.jld.model.structure.Instance;
import org.lemsml.jld.model.structure.MultiInstance;
import org.lemsml.jld.model.structure.Structure;
import org.lemsml.jld.model.structure.Tunnel;
import org.lemsml.jld.model.structure.With;
import org.lemsml.jld.model.type.About;
import org.lemsml.jld.model.type.Attachments;
import org.lemsml.jld.model.type.Child;
import org.lemsml.jld.model.type.Children;
import org.lemsml.jld.model.type.ComponentType;
import org.lemsml.jld.model.type.Exposure;
import org.lemsml.jld.model.type.Fixed;
import org.lemsml.jld.model.type.InstanceRequirement;
import org.lemsml.jld.model.type.Parameter;
import org.lemsml.jld.model.type.Path;
import org.lemsml.jld.model.type.Property;
import org.lemsml.jld.model.type.ReceivePort;
import org.lemsml.jld.model.type.Requirement;
import org.lemsml.jld.model.type.SendPort;
import org.lemsml.jld.model.type.Text;


public final class LemsClasses {

	
	public static LemsClasses instance;
	
	private final ArrayList<LemsClass> classList;
	
	
	public static LemsClasses getInstance() {
		synchronized(LemsClasses.class) {
		if (instance == null) {
			instance = new LemsClasses();
		}
		}
		return instance;
	}
	
	
	
	private LemsClasses() {
		classList = new ArrayList<LemsClass>();
	
		classList.addAll(getLemsClasses());
		classList.addAll(getComponentTypeClasses());
	 	
		classList.addAll(getDynamicsClasses());
		classList.addAll(getStructureClasses());
		classList.addAll(getSimulationClasses());
		 
	}

	public ArrayList<LemsClass> getClasses() {
 		return classList;
	}

	

	private ArrayList<LemsClass> getLemsClasses() {
		ArrayList<LemsClass> ret =  new ArrayList<LemsClass>();
		String section = "root";
		ret.add(new LemsClass(Lems.class, section));
		ret.add(new LemsClass(Target.class, section));
		ret.add(new LemsClass(Constant.class, section));	
		ret.add(new LemsClass(Dimension.class, section));
		ret.add(new LemsClass(Unit.class, section));
		ret.add(new LemsClass(Assertion.class, section));
	 		
		return ret;
	}
	
	
	private ArrayList<LemsClass> getDynamicsClasses() {
		ArrayList<LemsClass> ret =  new ArrayList<LemsClass>();
		String section = "dynamics";
		ret.add(new LemsClass(Dynamics.class, section));
		ret.add(new LemsClass(StateVariable.class, section));
		ret.add(new LemsClass(StateAssignment.class, section));
		ret.add(new LemsClass(TimeDerivative.class, section));
		ret.add(new LemsClass(DerivedVariable.class, section));
		ret.add(new LemsClass(OnStart.class, section));
	 	ret.add(new LemsClass(OnCondition.class, section));	
	 	ret.add(new LemsClass(OnEvent.class, section));
	 	ret.add(new LemsClass(EventOut.class, section));
	 	ret.add(new LemsClass(KineticScheme.class, section));
	 	ret.add(new LemsClass(Regime.class, section));
	 	ret.add(new LemsClass(OnEntry.class, section));
	 	ret.add(new LemsClass(Transition.class, section));
	   	ret.add(new LemsClass(Super.class, section));
	 	
	 	ret.add(new LemsClass(ConditionalDerivedVariable.class, section));
	 	return ret;
	}

	private ArrayList<LemsClass> getStructureClasses() {
		ArrayList<LemsClass> ret =  new ArrayList<LemsClass>();
		String section = "structure";
		ret.add(new LemsClass(Structure.class, section));
		ret.add(new LemsClass(Instance.class, section));
		ret.add(new LemsClass(MultiInstance.class, section));
		ret.add(new LemsClass(ForEach.class, section));
  	 	ret.add(new LemsClass(EventConnection.class, section));
	 	ret.add(new LemsClass(Tunnel.class, section));
	 	ret.add(new LemsClass(With.class, section));
 		return ret;
	}
 
	
	
	private ArrayList<LemsClass> getComponentTypeClasses() {
		ArrayList<LemsClass> ret =  new ArrayList<LemsClass>();
		
		String section = "componenttypes";
		ret.add(new LemsClass(ComponentType.class, section));
		ret.add(new LemsClass(Parameter.class, section));
		ret.add(new LemsClass(Text.class, section));
		ret.add(new LemsClass(Path.class, section));
		ret.add(new LemsClass(Fixed.class, section));
		ret.add(new LemsClass(Property.class, section));
		ret.add(new LemsClass(DerivedParameter.class, section));
 		ret.add(new LemsClass(Requirement.class, section));
 		ret.add(new LemsClass(InstanceRequirement.class, section));	
		ret.add(new LemsClass(Exposure.class, section));
		ret.add(new LemsClass(Child.class, section));
		ret.add(new LemsClass(Children.class, section));
  		ret.add(new LemsClass(SendPort.class, section));
		ret.add(new LemsClass(ReceivePort.class, section));
  		ret.add(new LemsClass(Attachments.class, section));
 		ret.add(new LemsClass(Insertion.class, section));
  		ret.add(new LemsClass(About.class, section));
 		 
		return ret;
	}

	private ArrayList<LemsClass> getSimulationClasses() {
		ArrayList<LemsClass> ret =  new ArrayList<LemsClass>();
		String section = "simulation";
		ret.add(new LemsClass(Simulation.class, section));
		ret.add(new LemsClass(Recording.class, section));
		ret.add(new LemsClass(DataDisplay.class, section));
		ret.add(new LemsClass(DataWriter.class, section));
		ret.add(new LemsClass(Run.class, section));
		return ret;
	}
	 
	   
}
