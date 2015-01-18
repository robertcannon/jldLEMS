package org.lemsml.jld.hrun;

import org.lemsml.jlems.core.sim.ContentError;

public interface RuntimeType {

	String getID();

	
	public StateRunnable newStateRunnable() throws ContentError, ConnectionError, RuntimeError;
	
}
