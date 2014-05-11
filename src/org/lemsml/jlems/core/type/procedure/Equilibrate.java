package org.lemsml.jlems.core.type.procedure;

import org.lemsml.jlems.core.run.EquilibrateStatement;
import org.lemsml.jlems.core.run.ExecutableStatement;
import org.lemsml.jlems.core.type.Lems;

public class Equilibrate extends AbstractStatement {

	@Override
	public ExecutableStatement makeExecutableStatement(Lems lems) {
		EquilibrateStatement es = new EquilibrateStatement();
		return es;
	}

}
