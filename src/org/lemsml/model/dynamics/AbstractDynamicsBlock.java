package org.lemsml.model.dynamics;

import java.util.List;

import org.lemsml.model.core.ListMap;

public class AbstractDynamicsBlock {

	private Dynamics dynamics;
	
	protected ListMap<StateAssignment> stateAssignmentMap = new ListMap<StateAssignment>();

	
	
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
  
}


