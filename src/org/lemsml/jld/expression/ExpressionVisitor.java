package org.lemsml.jld.expression;

import org.lemsml.jld.exception.ExpressionError;

public interface ExpressionVisitor {

	 
	void visitVariable(String svar);

	void visitOrNode(OrNode orNode);

	void visitFunctionNode(String fname, DoubleParseTreeNode argEvaluable) throws ExpressionError;

	void visitConstant(double dval);

	void visitAndNode(BooleanParseTreeNode leftEvaluable, BooleanParseTreeNode rightEvaluable);

	void visitTimesNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) throws ExpressionError;

	void visitPowerNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) throws ExpressionError;

	void visitMinusNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) throws ExpressionError;

	void visitUnaryMinusNode(DoubleParseTreeNode rightEvaluable) throws ExpressionError;

	void visitDivideNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) throws ExpressionError;

	void visitNotEqualsNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable);

	void visitModuloNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) throws ExpressionError;

	void visitLessThanOrEqualsNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable);

	void visitLessThanNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable);

	void visitPlusNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) throws ExpressionError;

	void visitGreaterThanOrEqualsNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable);

	void visitGreaterThanNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable);

	void visitEqualsNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable);
	
	
}
