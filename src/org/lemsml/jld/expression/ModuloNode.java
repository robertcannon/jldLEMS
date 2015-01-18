package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.eval.AbstractDVal;
import org.lemsml.jld.eval.Mod;
import org.lemsml.jld.exception.ExpressionError; 

public class ModuloNode extends AbstractFloatResultNode {


	public ModuloNode() {
		super("mod");
	}


	
	public ModuloNode copy() {
		return new ModuloNode();
	}
	
	public int getPrecedence() {
		return 5;
	}
	 
	public double op(double x, double y) {
		// TODO - v. lazy - need int op nodes
		return (Math.round(x) % Math.round(y));
	}

	
	public AbstractDVal makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError {
		checkLeftRight();
		return new Mod(leftEvaluable.makeEvaluable(fixedHM), rightEvaluable.makeEvaluable(fixedHM));
	}

	 
	public Dim dimop(Dim dl, Dim dr) throws ExpressionError {
		Dim ret = null;
		if (dl.matches(dr)) {
			ret = dl;
		} else {
			throw new ExpressionError("Dimensions do not match in plus: " + dl + " " + dr);
		}
		return ret;
	}

	public Dim evaluateDimensional(HashMap<String, Dim> dhm) throws ExpressionError {
		throw new ExpressionError("Can't apply modulo operations to dimensions");
	}
	


	@Override
	public void doLocalVisit(ExpressionVisitor ev) throws ExpressionError {
		ev.visitModuloNode(leftEvaluable, rightEvaluable);
	}
		
 
	
}
