package org.lemsml.jlems.core.type.procedure;

import org.lemsml.jlems.core.run.ExecutableForEach;
import org.lemsml.jlems.core.run.ExecutableStatement;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.Lems;
import org.lemsml.jlems.core.type.LemsCollection;

public class ForEachComponent extends AbstractStatement {

	public String select;
	
	public String as;
	
	
	public LemsCollection<AbstractStatement> abstractStatements = new LemsCollection<AbstractStatement>();


	@Override
	public ExecutableStatement makeExecutableStatement(Lems lems) throws ContentError {
		ExecutableForEach ret = new ExecutableForEach(select, as);
		
		for (AbstractStatement s : abstractStatements) {
			ret.add(s.makeExecutableStatement(lems));
		}
		
		return ret;
	}
	
}
