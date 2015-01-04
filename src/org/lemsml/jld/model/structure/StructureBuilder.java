package org.lemsml.jld.model.structure;

import org.lemsml.jld.model.type.ComponentType;

public class StructureBuilder {

	// see DynamicsBuilder
	public Structure newStructure(ComponentType ct) {
		Structure ret =  new Structure(ct);
		return ret;
	}
	
	
}
