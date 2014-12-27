package org.lemsml.io.jld.generation;

import java.util.ArrayList;
 
import org.lemsml.model.About;
import org.lemsml.model.Assertion;
import org.lemsml.model.Attachments; 
import org.lemsml.model.Child;
import org.lemsml.model.Children; 
import org.lemsml.model.Component;
import org.lemsml.model.ComponentReference; 
import org.lemsml.model.ComponentType; 
import org.lemsml.model.Constant;
import org.lemsml.model.DerivedParameter;
import org.lemsml.model.Dimension; 
import org.lemsml.model.Exposure;  
import org.lemsml.model.Insertion;
import org.lemsml.model.InstanceRequirement;
import org.lemsml.model.IntegerParameter;
import org.lemsml.model.Lems; 
import org.lemsml.model.LocalParameters;  
import org.lemsml.model.Parameter; 
import org.lemsml.model.Path;
import org.lemsml.model.Property;
import org.lemsml.model.ReceivePort;
import org.lemsml.model.Requirement;
import org.lemsml.model.SendPort; 
import org.lemsml.model.Target;
import org.lemsml.model.Text;
import org.lemsml.model.Unit; 
import org.lemsml.model.dynamics.ConditionalDerivedVariable; 
import org.lemsml.model.dynamics.DerivedScalarField;
import org.lemsml.model.dynamics.DerivedVariable;
import org.lemsml.model.dynamics.Dynamics; 
import org.lemsml.model.dynamics.EventOut; 
import org.lemsml.model.dynamics.KineticScheme;
import org.lemsml.model.dynamics.OnCondition;
import org.lemsml.model.dynamics.OnEntry;
import org.lemsml.model.dynamics.OnEvent;
import org.lemsml.model.dynamics.OnStart;
import org.lemsml.model.dynamics.Regime;
import org.lemsml.model.dynamics.StateAssignment; 
import org.lemsml.model.dynamics.StateVariable;
import org.lemsml.model.dynamics.Super;
import org.lemsml.model.dynamics.TimeDerivative;
import org.lemsml.model.dynamics.Transition; 
import org.lemsml.model.simulation.DataDisplay;
import org.lemsml.model.simulation.DataWriter;
import org.lemsml.model.simulation.Record;
import org.lemsml.model.simulation.Run;
import org.lemsml.model.simulation.Simulation; 
import org.lemsml.model.structure.ChildInstance; 
import org.lemsml.model.structure.EventConnection; 
import org.lemsml.model.structure.Instance;
import org.lemsml.model.structure.MultiInstance;
import org.lemsml.model.structure.MultiInstantiate; 
import org.lemsml.model.structure.Structure;
import org.lemsml.model.structure.Tunnel;
import org.lemsml.model.structure.With;


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
		ret.add(new LemsClass(Component.class, section));
		
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
		ret.add(new LemsClass(MultiInstantiate.class, section));
		ret.add(new LemsClass(Instance.class, section));
		ret.add(new LemsClass(MultiInstance.class, section));
 	 	ret.add(new LemsClass(ChildInstance.class, section));	
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

 		ret.add(new LemsClass(LocalParameters.class, section));
		
		ret.add(new LemsClass(Property.class, section));
		ret.add(new LemsClass(DerivedParameter.class, section));
 		ret.add(new LemsClass(Requirement.class, section));
 		ret.add(new LemsClass(InstanceRequirement.class, section));	
		ret.add(new LemsClass(Exposure.class, section));
		ret.add(new LemsClass(Child.class, section));
		ret.add(new LemsClass(Children.class, section));
 		ret.add(new LemsClass(ComponentReference.class, section));
  		ret.add(new LemsClass(SendPort.class, section));
		ret.add(new LemsClass(ReceivePort.class, section));
  		ret.add(new LemsClass(Attachments.class, section));
 		ret.add(new LemsClass(Insertion.class, section));
		ret.add(new LemsClass(IntegerParameter.class, section));
 		ret.add(new LemsClass(About.class, section));
 		 
		return ret;
	}

	private ArrayList<LemsClass> getSimulationClasses() {
		ArrayList<LemsClass> ret =  new ArrayList<LemsClass>();
		String section = "simulation";
		ret.add(new LemsClass(Simulation.class, section));
		ret.add(new LemsClass(Record.class, section));
		ret.add(new LemsClass(DataDisplay.class, section));
		ret.add(new LemsClass(DataWriter.class, section));
		ret.add(new LemsClass(Run.class, section));
		return ret;
	}
	 
	   
}
