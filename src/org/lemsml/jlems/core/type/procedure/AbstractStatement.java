package org.lemsml.jlems.core.type.procedure;

import org.lemsml.jlems.core.run.ExecutableStatement;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.Lems;

public abstract class AbstractStatement {

	public abstract ExecutableStatement makeExecutableStatement(Lems lems) throws ContentError;
	 
}
