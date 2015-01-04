package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.exception.ExpressionError;
 
public interface ParseTreeNode {
  
	Dim getDimensionality(HashMap<String, Dim> dimHM) throws ExpressionError;
 
	Dim evaluateDimensional(HashMap<String, Dim> adml) throws ExpressionError;

	void substituteVariables(HashMap<String, String> varHM) throws ExpressionError;

	String toExpression() throws ExpressionError;

	void doVisit(ExpressionVisitor ev) throws ExpressionError;
}
