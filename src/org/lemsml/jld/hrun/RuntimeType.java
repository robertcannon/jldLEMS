package org.lemsml.jld.hrun;
 

public interface RuntimeType {

	String getID();

	
	public StateRunnable newStateRunnable() throws ConnectionError, RuntimeError;
	
}
