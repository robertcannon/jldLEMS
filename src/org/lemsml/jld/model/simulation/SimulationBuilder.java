package org.lemsml.jld.model.simulation;

import org.lemsml.jld.model.type.ComponentType;

public class SimulationBuilder {

	// see DynamicsBuilder
	public Simulation newSimulation(ComponentType ct) {
		Simulation ret =  new Simulation(ct);
		return ret;
	}
	
	
}
