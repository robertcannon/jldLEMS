package org.lemsml.jld.expression;

import org.lemsml.jld.exception.ExpressionError;



// check wither an expression can be evaluated from a given set of variable names. Ie, check if it refers to any variables
// that aren't in the list
public class DummyExpressionVisitor implements ExpressionVisitor {

	 

	public DummyExpressionVisitor() {
 	}
	
	 
	 
	
	@Override
	public void visitVariable(String svar) {
	 
	}

	@Override
	public void visitOrNode(OrNode orNode) {
	 
	}

	@Override
	public void visitFunctionNode(String fname, DoubleParseTreeNode argEvaluable)
			throws ExpressionError {
	 
	}

	@Override
	public void visitConstant(double dval) {
		 
	}

	@Override
	public void visitAndNode(BooleanParseTreeNode leftEvaluable,
			BooleanParseTreeNode rightEvaluable) {
	 
	}

	@Override
	public void visitTimesNode(DoubleParseTreeNode leftEvaluable,
			DoubleParseTreeNode rightEvaluable) throws ExpressionError {
		 
	}

	@Override
	public void visitPowerNode(DoubleParseTreeNode leftEvaluable,
			DoubleParseTreeNode rightEvaluable) throws ExpressionError {
		 
	}

	@Override
	public void visitMinusNode(DoubleParseTreeNode leftEvaluable,
			DoubleParseTreeNode rightEvaluable) throws ExpressionError {
		 
	}

	@Override
	public void visitUnaryMinusNode(DoubleParseTreeNode rightEvaluable)
			throws ExpressionError {
		 
	}

	@Override
	public void visitDivideNode(DoubleParseTreeNode leftEvaluable,
			DoubleParseTreeNode rightEvaluable) throws ExpressionError {
		 
	}

	@Override
	public void visitNotEqualsNode(DoubleParseTreeNode leftEvaluable,
			DoubleParseTreeNode rightEvaluable) {
		 
	}

	@Override
	public void visitModuloNode(DoubleParseTreeNode leftEvaluable,
			DoubleParseTreeNode rightEvaluable) throws ExpressionError {
	 
	}

	@Override
	public void visitLessThanOrEqualsNode(DoubleParseTreeNode leftEvaluable,
			DoubleParseTreeNode rightEvaluable) {
		 
	}

	@Override
	public void visitLessThanNode(DoubleParseTreeNode leftEvaluable,
			DoubleParseTreeNode rightEvaluable) {
		 
	}

	@Override
	public void visitPlusNode(DoubleParseTreeNode leftEvaluable,
			DoubleParseTreeNode rightEvaluable) throws ExpressionError {
		 
	}

	@Override
	public void visitGreaterThanOrEqualsNode(DoubleParseTreeNode leftEvaluable,
			DoubleParseTreeNode rightEvaluable) {
		 
	}

	@Override
	public void visitGreaterThanNode(DoubleParseTreeNode leftEvaluable,
			DoubleParseTreeNode rightEvaluable) {
		 
	}

	@Override
	public void visitEqualsNode(DoubleParseTreeNode leftEvaluable,
			DoubleParseTreeNode rightEvaluable) {
	 
	}


 
}
