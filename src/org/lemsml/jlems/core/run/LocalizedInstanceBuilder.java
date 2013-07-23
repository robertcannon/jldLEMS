package org.lemsml.jlems.core.run;

import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.LocalParameterValue;

import java.util.ArrayList;
import java.util.HashMap;
 

public class LocalizedInstanceBuilder extends AbstractChildBuilder {

	 
	RuntimeType runtimeType;
	HashMap<String, DoublePointer> lpvals;
 	
	public LocalizedInstanceBuilder(RuntimeType cb, HashMap<String, DoublePointer> lpv) {
		super(); 
		runtimeType = cb;
		lpvals = lpv;
	 
	}
	
	
	
	
	

	
	public void childInstantiate(StateInstance par) throws ContentError, ConnectionError, RuntimeError {
  		
		E.info("Child instantiating into " + par);
		
		String cnm = "instance_" + par.getChildCount();
		
		if (runtimeType instanceof StateType) {
			StateType stateType = (StateType)runtimeType;
			StateInstance sr = stateType.newInstance();
			sr.setParent(par);
			
			sr.setLocalValues(lpvals);
			
			
			E.info("Add list child " + stateType.getTypeName() + " " + sr.getID());
			
			par.addListChild(stateType.getTypeName(), sr.getID(), sr);
//			par.addChild(cnm, sr);
		
		} else {
			E.missing("Time to build from a non state type: " + runtimeType);
			//StateRunnable sr = runtimeType.newStateRunnable();
			//sr.setParent(parent);
			//parent.addChild(cnm, sr);
		}
		
		
		
	 
	}
	
	
	
	
	public boolean isInstantiator() {
		return true;
	}


	
	
	
	
	 

	public void addAssignment(String property, String expression) {
		// MUSTDO
		
	}

	@Override
	public void consolidateStateTypes() {
		 if (runtimeType instanceof StateType) {
			 runtimeType = ((StateType)runtimeType).getConsolidatedStateType("(child)");
		 }	
	}
 

}
