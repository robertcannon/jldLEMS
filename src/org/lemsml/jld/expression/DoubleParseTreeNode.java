package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jlems.core.eval.AbstractDVal;

public interface DoubleParseTreeNode extends ParseTreeNode {
 
	AbstractDVal makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError;

	String toExpression() throws ExpressionError;
	
}
