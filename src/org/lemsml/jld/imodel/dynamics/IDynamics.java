package org.lemsml.jld.imodel.dynamics;

import java.util.List;

public interface IDynamics {

	List<IStateVariable> getStateVariables();

	List<IDerivedVariable> getDerivedVariables();

	List<ITimeDerivative> getTimeDerivatives();

}
