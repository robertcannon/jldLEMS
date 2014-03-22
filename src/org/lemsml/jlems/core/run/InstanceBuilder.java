package org.lemsml.jlems.core.run;

import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.Component;
 

public class InstanceBuilder extends AbstractChildBuilder {

	Component ctr;
	
	StateType stateType;
 
	 
	
	
	public void childInstantiate(StateInstance par) throws ContentError, ConnectionError, RuntimeError {
  		
		// MultiInstance mi = new MultiInstance(stateType.typeName, "");
		 
		// StateRunnable si = par.getScopeInstance(ctr.getID());
	
		E.missing();
		/*
		InstanceSet<StateRunnable> iset = si.getUniqueInstanceSet();
		
		iset.getParent().checkBuilt();
		
		for (StateRunnable psi : iset.getItems()) {
			StateInstance sr = stateType.newInstance();
			sr.coCopy((StateInstance)psi);
			par.addListChild(stateType.getTypeName(), sr.getID(), sr);
	 
		}
	 */
	}
	
	
	
	
	public boolean isInstantiator() {
		return true;
	}


	@Override
	public void consolidateStateTypes() throws ContentError {
		 if (stateType != null) {
			 stateType = stateType.getConsolidatedStateType("(instancebuilder)");
		 }	
	}

 

 
}
