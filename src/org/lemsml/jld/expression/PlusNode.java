package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jlems.core.eval.AbstractDVal;
import org.lemsml.jlems.core.eval.Plus;

public class PlusNode extends AbstractFloatResultNode {

    public static final String SYMBOL = "+";

	public PlusNode() {
		super(SYMBOL);
	}

	
    @Override
	public PlusNode copy() {
		return new PlusNode();
	}
	
    @Override
	public int getPrecedence() {
		return 5;
	}
	 
    @Override
	public double op(double x, double y) {
		return (Double.isNaN(x) ? 0 : x) + y;
	}

	
    @Override
	public AbstractDVal makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError {
		checkLeftRight();
		
		return new Plus(leftEvaluable.makeEvaluable(fixedHM), rightEvaluable.makeEvaluable(fixedHM));
	}

    @Override
	public Dim dimop(Dim dl, Dim dr) throws ExpressionError {
		Dim ret = null;
		if (dl.matches(dr)) {
			ret = dl;
		} else {
			throw new ExpressionError("Dimensions do not match in plus: " + dl + " " + dr);
		}
		return ret;
	}
	

    @Override
	public Dim evaluateDimensional(HashMap<String, Dim> dhm) throws ExpressionError {
		throw new ExpressionError("Can't apply addition operations to dimensions");
	}
	 


	@Override
	public void doLocalVisit(ExpressionVisitor ev) throws ExpressionError {
	 		ev.visitPlusNode(leftEvaluable, rightEvaluable);
		}
		
 
	
}
