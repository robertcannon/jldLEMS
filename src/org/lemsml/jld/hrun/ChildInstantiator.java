package org.lemsml.jld.hrun;




public interface ChildInstantiator {

	void childInstantiate(StateInstance si) throws ConnectionError, RuntimeError;
	
}
