package org.lemsml.jld.imodel.dynamics;

import org.lemsml.jld.model.core.AbstractAST;

public interface IDerivedVariable {

	String getDimension();

	String getName();

	String getSelect();

	
	// RCC this shouldn't be here we need some kind of interface for the AST
	AbstractAST getAST();

	String getReduce();

	String getExposure();

}
