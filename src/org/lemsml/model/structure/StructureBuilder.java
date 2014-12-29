package org.lemsml.model.structure;

import org.lemsml.model.type.ComponentType;

public class StructureBuilder {

	// see DynamicsBuilder
	public Structure newStructure(ComponentType ct) {
		Structure ret =  new Structure(ct);
		return ret;
	}
	
	
}
