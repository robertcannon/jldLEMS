package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.exception.ExpressionError;
 
public abstract class AbstractBooleanOperatorNode extends AbstractOperatorNode implements BooleanParseTreeNode {

	BooleanParseTreeNode leftEvaluable;
	BooleanParseTreeNode rightEvaluable;
	
	public AbstractBooleanOperatorNode(String s) {
		super(s);
	}
 
	public abstract boolean bool(boolean x, boolean y);
 
 
	
	protected void checkLeftRight() throws ExpressionError {
		if (leftEvaluable == null) {
			if (left instanceof BooleanParseTreeNode) {
				leftEvaluable = (BooleanParseTreeNode)left;
			} else {
				throw new ExpressionError("Wrong node type in float operator: " + left);
			}
		}
		if (rightEvaluable == null) {
			if (right instanceof BooleanParseTreeNode) {
				rightEvaluable = (BooleanParseTreeNode)right;	
			} else {
				throw new ExpressionError("Wrong node type in float operator: " + right);
			}
		}
	}
	

	public Dim getDimensionality(HashMap<String, Dim> dimHM) throws ExpressionError {
		checkLeftRight();
		Dim dl = leftEvaluable.getDimensionality(dimHM);
		Dim dr = rightEvaluable.getDimensionality(dimHM);
		Dim ret = null;
		if (dl != null && dr != null && dl.isDimensionless() && dr.isDimensionless()) {
			ret = dl;
		} else {
			throw new ExpressionError("Null dimension in operator: " + dl + " " + dr + " operator: " + symbol);
		}
		return ret;
	}

	 
	 
 

	public Dim evaluateDimensional(HashMap<String, Dim> dhm) throws ExpressionError {
		throw new ExpressionError("Can't apply boolean operations to dimensions");
	}
	
	
	@Override
	public void substituteVariables(HashMap<String, String> varHM) throws ExpressionError {
		checkLeftRight();
		leftEvaluable.substituteVariables(varHM);
		rightEvaluable.substituteVariables(varHM);
	}
 
	
	public String toExpression() throws ExpressionError {
		checkLeftRight();
		return "(" + leftEvaluable.toExpression() + " " + symbol + " " + rightEvaluable.toExpression() + ")";
	}
	
	
	 @Override
	    public void doVisit(ExpressionVisitor ev) throws ExpressionError {
		 checkLeftRight();
		 doLocalVisit(ev);
		 leftEvaluable.doVisit(ev);
		 rightEvaluable.doVisit(ev);
	   

	    }

	public abstract void doLocalVisit(ExpressionVisitor ev) throws ExpressionError;
	 
}
 