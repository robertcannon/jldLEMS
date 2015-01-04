package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.exception.ExpressionError;
 

public abstract class AbstractFloatOperatorNode extends AbstractOperatorNode {

	protected DoubleParseTreeNode leftEvaluable;
	protected DoubleParseTreeNode rightEvaluable;
	
	public AbstractFloatOperatorNode(String s) {
		super(s);
	}

	 
	
	protected void checkLeftRight() throws ExpressionError {
		if (leftEvaluable == null) {
			if (left instanceof DoubleParseTreeNode) {
				leftEvaluable = (DoubleParseTreeNode)left;
			} else {
				throw new ExpressionError("Wrong node type in float operator: " + left);
			}
		}
		
		if (rightEvaluable == null) {
			if (right instanceof DoubleParseTreeNode) {
				rightEvaluable = (DoubleParseTreeNode)right;
			} else {
				throw new ExpressionError("Wrong node type in float operator: " + right);
			}
		}
	}
	
	

    @Override
	public Dim getDimensionality(HashMap<String, Dim> dimHM) throws ExpressionError {
		checkLeftRight();
		
		Dim dl = leftEvaluable.getDimensionality(dimHM);
		Dim dr = rightEvaluable.getDimensionality(dimHM);
		Dim ret = null;
		if (dl != null && dr != null) {
			ret = dimop(dl, dr);
		} else {
			throw new ExpressionError("Null dimension in operator: " + dl + " " + dr + " operator: " + symbol);
		}
		return ret;
	}
	
	
    @Override
	public Dim evaluateDimensional(HashMap<String, Dim> dimHM) throws ExpressionError {
		return getDimensionality(dimHM);
	}
		
	public abstract Dim dimop(Dim dl, Dim dr) throws ExpressionError;
	
	
    @Override
	public void substituteVariables(HashMap<String, String> varHM) throws ExpressionError {
		checkLeftRight();
		leftEvaluable.substituteVariables(varHM);
		rightEvaluable.substituteVariables(varHM);
	}
	
	
    @Override
	public String toExpression() throws ExpressionError {
		checkLeftRight();
		return "(" + leftEvaluable.toExpression() + " " + symbol + " " + rightEvaluable.toExpression() + ")";
	}
    
    
    @Override
    public final void doVisit(ExpressionVisitor ev) throws ExpressionError {
        checkLeftRight();
        doLocalVisit(ev);
        leftEvaluable.doVisit(ev);
        rightEvaluable.doVisit(ev);
    }



	public abstract void doLocalVisit(ExpressionVisitor ev) throws ExpressionError;
	 

}