package org.lemsml.jld.model.dynamics;

import java.util.List;

import org.lemsml.jld.imodel.dynamics.IEventOut;
import org.lemsml.jld.imodel.dynamics.IOn;
import org.lemsml.jld.imodel.dynamics.IStateAssignment;
import org.lemsml.jld.imodel.dynamics.ITransition;
import org.lemsml.jld.model.core.ListMap;

public class AbstractDynamicsBlock implements IOn {
	
	protected ListMap<StateAssignment> stateAssignmentMap = new ListMap<StateAssignment>();

	protected ListMap<EventOut> eventOutMap = new ListMap<EventOut>();
	
	protected ListMap<Transition> transitionMap = new ListMap<Transition>();
	
 	
	public AbstractDynamicsBlock() {
		
	}
	
	public List<StateAssignment> getStateAssignments() {
		return stateAssignmentMap.getItems();
	}
	
	
	public StateAssignment addStateAssignment(String eltname) {
		StateAssignment sa = new StateAssignment(this, eltname);
		stateAssignmentMap.put(eltname, sa);
		return sa;
	}
	
	public EventOut addEventOut(String eltname) {
		EventOut oe = new EventOut(this, eltname);
		eventOutMap.put(eltname, oe);
		return oe;
	}
	
	public List<EventOut> getEventOuts() {
		return eventOutMap.getItems();
	}

	
	public List<Transition> getTransitions() {
		return transitionMap.getItems();
	}
	
	
	@Override
	public List<? extends IStateAssignment> getIStateAssignments() {
		 return getStateAssignments();
	}

	@Override
	public List<? extends IEventOut> getIEventOuts() {
		return getEventOuts();
	}

	@Override
	public List<? extends ITransition> getITransitions() {
		 return getTransitions();
	}
	 
	
}


