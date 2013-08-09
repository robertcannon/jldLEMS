package org.lemsml.jlems.core.run;

import java.util.ArrayList;
import java.util.HashMap;

import org.lemsml.jlems.core.eval.DBase;
import org.lemsml.jlems.core.eval.DoubleEvaluator;
import org.lemsml.jlems.core.expression.DoubleParseTreeNode;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.sim.ContentError;

public class MultiCollectorBuilder extends AbstractPostBuilder {

	String childrenName;
	  	
	public MultiCollectorBuilder(String cnm) {
		super();
		childrenName = cnm;
 	}
	
	@Override
	public void postBuild(StateRunnable tgt, HashMap<String, StateRunnable> sihm, BuildContext bc) throws ConnectionError,
			ContentError, RuntimeError {
	 
		// this specifies that the target component is to be treated  
		// as a list so we can access child components by index
		tgt.setList(childrenName);
	}
	
	
	
	
	public boolean isInstantiator() {
		return false;
	}

 

	 
	@Override
	public void consolidateStateTypes() {
		// nothing to do here
	}
	
	
	
}
