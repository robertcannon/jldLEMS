package org.lemsml.jld.model.structure;

import java.util.List;

import org.lemsml.jld.imodel.structure.IEventConnection;
import org.lemsml.jld.imodel.structure.IForEach;
import org.lemsml.jld.imodel.structure.IMultiInstance;
import org.lemsml.jld.imodel.structure.IStructureBlock;
import org.lemsml.jld.model.core.ListMap;

public class AbstractStructureBlock implements IStructureBlock {
	
	AbstractStructureBlock parent;
	
	protected ListMap<MultiInstance> multiInstanceMap = new ListMap<MultiInstance>();
	
	protected ListMap<ForEach> forEachMap = new ListMap<ForEach>();
	
	protected ListMap<EventConnection> eventConnectionMap = new ListMap<EventConnection>();
	
	
	
	protected AbstractStructureBlock(AbstractStructureBlock p) {
		parent = p;
	}

	
	public MultiInstance addMultiInstance(String id) {
		MultiInstance mi = new MultiInstance(this);
		multiInstanceMap.put(id, mi);
		return mi;
	}

	public List<MultiInstance> getMultiInstances() {
		return multiInstanceMap.getItems();
	}
	
	public ForEach addForEach(String id) {
		ForEach mi = new ForEach(this);
		forEachMap.put(id, mi);
		return mi;
	}

	public List<ForEach> getForEachs() {
		return forEachMap.getItems();
	}
	
	public EventConnection addEventConnection(String id) {
		EventConnection mi = new EventConnection(this);
		eventConnectionMap.put(id, mi);
		return mi;
	}

	public List<EventConnection> getEventConnections() {
		return eventConnectionMap.getItems();
	}


	@Override
	public List<? extends IMultiInstance> getIMultiInstances() {
		return getMultiInstances();
	}


	@Override
	public List<? extends IEventConnection> getIEventConnections() {
		return getEventConnections();
	}


	@Override
	public List<? extends IForEach> getIForEachs() {
		return getForEachs();
	}
	
	
}
