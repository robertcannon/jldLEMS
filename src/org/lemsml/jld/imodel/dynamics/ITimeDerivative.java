package org.lemsml.jld.imodel.dynamics;

import org.lemsml.jld.model.core.AbstractAST;
 
public interface ITimeDerivative {

	IStateVariable getStateVariable();

	AbstractAST getAST();

}
