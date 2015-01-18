package org.lemsml.jld.hrun;

import java.util.HashMap;

import org.lemsml.jld.io.E;
 

public abstract class AbstractPostBuilder extends BuilderElement {

	public abstract void postBuild(StateRunnable tgt, HashMap<String, StateRunnable> sihm, BuildContext bc) throws ConnectionError, RuntimeError;

	 
	
	public void postChildren(StateRunnable tgt, HashMap<String, StateRunnable> sihm, BuildContext bc) throws ConnectionError, RuntimeError {
		for (BuilderElement be : elts) {
			if (be instanceof AbstractPostBuilder) {
				((AbstractPostBuilder)be).postBuild(tgt, sihm, bc);
			} else {
				E.error("CHECK - is this OK? non post-builder child in post-builder?");
			}
		}
	}
	
}
