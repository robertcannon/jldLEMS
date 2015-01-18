package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.eval.AbstractDVal;
import org.lemsml.jld.exception.ExpressionError;
 

public interface DoubleParseTreeNode extends ParseTreeNode {
 
	AbstractDVal makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError;

	String toExpression() throws ExpressionError;
	
}
