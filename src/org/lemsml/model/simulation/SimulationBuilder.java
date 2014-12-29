package org.lemsml.model.simulation;

import org.lemsml.model.type.ComponentType;

public class SimulationBuilder {

	// see DynamicsBuilder
	public Simulation newSimulation(ComponentType ct) {
		Simulation ret =  new Simulation(ct);
		return ret;
	}
	
	
}
