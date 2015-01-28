package org.lemsml.jld.imodel.dynamics;

import java.util.List;

public interface IOn {

	List<? extends IStateAssignment> getIStateAssignments();

	List<? extends IEventOut> getIEventOuts();

	List<? extends ITransition> getITransitions();
	
}
