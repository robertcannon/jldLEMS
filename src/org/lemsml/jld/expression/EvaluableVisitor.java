package org.lemsml.jld.expression;

import java.util.HashSet;

import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jld.io.E;
import org.lemsml.jld.model.core.AbstractAST;



// check wither an expression can be evaluated from a given set of variable names. Ie, check if it refers to any variables
// that aren't in the list
public class EvaluableVisitor extends DummyExpressionVisitor {

	HashSet<String> vars = null;
	
	boolean ok = false;
	

	public EvaluableVisitor(HashSet<String> vars) {
		this.vars = vars;
	}
	
	public boolean canEvaluateFrom(AbstractAST ast) {
		ok = true;
		try {
			ast.visitAll(this);
		} catch (ExpressionError ex) {
			E.warning("Exception while checking if can evaluate: " + ex);
			ok = false;
		}
		return ok;
	}

	 
	 
	
	@Override
	public void visitVariable(String svar) {
		if (!vars.contains(svar)) {
			// E.warning("Can evaluate failed on " + svar);
			ok = false;
		}
	}

	 

 
}
