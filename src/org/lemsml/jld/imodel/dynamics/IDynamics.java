package org.lemsml.jld.imodel.dynamics;

import java.util.List;

public interface IDynamics {

	List<? extends IStateVariable> getIStateVariables();

	List<? extends IDerivedVariable> getIDerivedVariables();

	List<? extends ITimeDerivative> getITimeDerivatives();

	List<? extends IOnStart> getIOnStarts();

	List<? extends IOnEvent> getIOnEvents();
	
	List<? extends IOnCondition> getIOnConditions();
	
}
