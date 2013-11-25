package org.lemsml.jlems.core.dimensionless;

import org.lemsml.jlems.core.type.LemsCollection;

public class State {

	
	public LemsCollection<StateVariable> stateVariables = new LemsCollection<StateVariable>();


	
	public void addStateVariable(String s) {
		stateVariables.add(new StateVariable(s));
	}
	
	
	public void addIfNewStateVariable(String s) {
		// TODO Auto-generated method stub
		
		boolean got = false;
		for (StateVariable sv : stateVariables) {
			if (sv.getName().equals(s)) {
				got = true;
			}
		}
		if (!got) {
			addStateVariable(s);
		}
	}


	public LemsCollection<StateVariable> getStateVariables() {
		return stateVariables;
	}


	public boolean hasStateVariable(String str) {
		boolean ret = false;

		for (StateVariable sv : stateVariables) {
			if (str.equals(sv.getName())) {
				ret = true;
			}
		}
		return ret;
		
	}
	
	
	
}
