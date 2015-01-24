package org.lemsml.jld.hrun;

import java.util.ArrayList;
import java.util.HashMap;
 
public class Builder {

	
	ArrayList<BuilderElement> builderElements = new ArrayList<BuilderElement>();
	
	 

 
	public void postBuild(StateRunnable si) throws ConnectionError, RuntimeError {
		HashMap<String, StateRunnable> siHM = new HashMap<String, StateRunnable>();

		BuildContext bc = new BuildContext();
		
		for (BuilderElement be : builderElements) {
			if (be instanceof AbstractPostBuilder) {
				AbstractPostBuilder pb = (AbstractPostBuilder)be;
				pb.postBuild(si, siHM, bc);
				
			}
		}
	}
	
 

	public void add(BuilderElement be) {
		builderElements.add(be);
	}
 

	public boolean isPostBuilder() {
		boolean ret = false;
		for (BuilderElement be : builderElements) {
			if (be instanceof AbstractPostBuilder) {
				ret = true;
			}
		}
		return ret;
	}


	public boolean isChildInstantiator() {
		boolean ret = false;
		for (BuilderElement be : builderElements) {
			if (be instanceof ChildInstantiator) {
				ret = true;
			}
		}
		return ret;
	}
	
	public void childInstantiate(StateInstance si) throws ConnectionError, RuntimeError {
		for (BuilderElement be : builderElements) {
			if (be instanceof ChildInstantiator) {
				((ChildInstantiator)be).childInstantiate(si);
			}
		}
	}


/*
	public void consolidateStateTypes() throws RuntimeError {
		for (BuilderElement be : builderElements) {
			be.consolidateStateTypes();
		}
	}
*/


	public boolean isSubstitutionBuilder() {
		boolean ret = false;
		for (BuilderElement be : builderElements) {
			if (be instanceof SubstitutionBuilder) {
				ret = true;
			}
		}
		return ret;
	}
	
	
	public SubstitutionBuilder getSubstitutionBuilder() {
		SubstitutionBuilder ret = null;
		for (BuilderElement be : builderElements) {
			if (be instanceof SubstitutionBuilder) {
				ret = (SubstitutionBuilder)be;
			}
		}
		return ret;
	}
	
}
