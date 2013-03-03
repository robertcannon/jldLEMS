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
		
		ArrayList<Statement> statements = new ArrayList<Statement>();
		
		statements.addAll(prints.getContents());
		statements.addAll(equilibrates.getContents());
		statements.addAll(forEachComponents.getContents());
		
		for (Statement statement : statements) {
			ret.addStep(statement.makeExecutableStatement(lems));
		}
		
		return ret;
	}
	
	
}
