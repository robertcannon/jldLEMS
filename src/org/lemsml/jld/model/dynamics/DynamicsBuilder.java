package org.lemsml.jld.model.dynamics;

import org.lemsml.jld.model.type.ComponentType;

public class DynamicsBuilder {

	// this is to avoid making the constructor of Dynamics public
	// ...must be a better solution (and not one the involves putting everything in the same package)
	public Dynamics newDynamics(ComponentType ct) {
		Dynamics ret =  new Dynamics(ct);
		return ret;
	}
	
	
}
