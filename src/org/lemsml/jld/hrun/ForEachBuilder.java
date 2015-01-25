package org.lemsml.jld.hrun;

import java.util.ArrayList;
import java.util.HashMap;

import org.lemsml.jld.io.E;
 

public class ForEachBuilder extends AbstractPostBuilder {

	String path;
	String var;
	
	
	public ForEachBuilder(String instances, String as) {
		super();
		path = instances;
		var = as;
	}


	 
	public void postBuild(StateRunnable base, HashMap<String, StateRunnable> sihm, BuildContext bc) throws ConnectionError, RuntimeError {


		//E.info("postBuild on: " + base + ", bc: " + bc);
		HashMap<String, StateRunnable> passHM = null;

		if (sihm == null) {
			passHM = new HashMap<String, StateRunnable>();
		} else {
			passHM = sihm;
		}
		// MUSTDO base is not the right starting point: should be relative to the link target in enclosing cpt
		ArrayList<StateRunnable> asi = base.getStateInstances(path);
 		
		E.info("Running a for-each build n=" + asi.size());
		
		for (StateRunnable si : asi) {
			E.info("running with " + var + "=" + si + " " + si.hashCode());
			passHM.put(var, si);		
	 		postChildren(base, passHM, bc);	
		}
	}


 
	

 

}
