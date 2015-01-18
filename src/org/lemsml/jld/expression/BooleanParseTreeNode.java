package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.eval.AbstractBVal;
import org.lemsml.jld.exception.ExpressionError; 

public interface BooleanParseTreeNode extends ParseTreeNode {
	
 
	AbstractBVal makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError;

	void checkDimensions(HashMap<String, Dim> dimHM) throws ExpressionError;

    @Override
	String toExpression() throws ExpressionError;
	     
}
