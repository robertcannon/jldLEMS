package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jlems.core.eval.AbstractBVal;

public interface BooleanParseTreeNode extends ParseTreeNode {
	
 
	AbstractBVal makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError;

	void checkDimensions(HashMap<String, Dim> dimHM) throws ExpressionError;

    @Override
	String toExpression() throws ExpressionError;
	     
}
