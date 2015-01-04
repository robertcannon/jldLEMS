package org.lemsml.jld.model.dynamics;

import java.util.List;

import org.lemsml.jld.model.core.ListMap;

public class AbstractDynamicsBlock {

	private Dynamics dynamics;
	
	protected ListMap<StateAssignment> stateAssignmentMap = new ListMap<StateAssignment>();

	protected ListMap<EventOut> eventOutMap = new ListMap<EventOut>();
	
	
	public AbstractDynamicsBlock(Dynamics d) {
		dynamics = d;
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

	
}


