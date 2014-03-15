package org.lemsml.jlems.core.type.procedure;

import java.util.ArrayList;

import org.lemsml.jlems.core.run.ExecutableProcedure;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.Lems;
import org.lemsml.jlems.core.type.LemsCollection;

public class Procedure {

	
	public LemsCollection<Print> prints = new LemsCollection<Print>();
	public LemsCollection<Equilibrate> equilibrates = new LemsCollection<Equilibrate>();
	public LemsCollection<ForEachComponent> forEachComponents = new LemsCollection<ForEachComponent>();
	
	
	public ExecutableProcedure makeExecutableProcedure(Lems lems) throws ContentError {
		ExecutableProcedure ret = new ExecutableProcedure();
		
		ArrayList<AbstractStatement> abstractStatements = new ArrayList<AbstractStatement>();
		
		abstractStatements.addAll(prints.getContents());
		abstractStatements.addAll(equilibrates.getContents());
		abstractStatements.addAll(forEachComponents.getContents());
		
		for (AbstractStatement abstractStatement : abstractStatements) {
			ret.addStep(abstractStatement.makeExecutableStatement(lems));
		}
		
		return ret;
	}
	
	
}
