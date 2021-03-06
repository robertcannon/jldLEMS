package org.lemsml.jld.model.structure;
 
import org.lemsml.jld.imodel.structure.IStructure;
import org.lemsml.jld.model.type.ComponentType;

public class Structure extends AbstractStructureBlock implements IStructure {

	ComponentType componentType;

	
	protected Structure(AbstractStructureBlock p) {
		super(p);
	}

	
	protected Structure(ComponentType ct) {
		super(null);
		componentType = ct;
	}
}
