package org.lemsml.jld.imodel.structure;

import java.util.List;

public interface IStructureBlock {

	List<? extends IMultiInstance> getIMultiInstances();
	
	List<? extends IEventConnection> getIEventConnections();
	
	List<? extends IForEach> getIForEachs();
}
