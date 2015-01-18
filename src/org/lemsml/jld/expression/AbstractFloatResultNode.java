package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.eval.DBase;
import org.lemsml.jld.exception.ExpressionError;
 
 

public abstract class AbstractFloatResultNode extends AbstractFloatOperatorNode implements DoubleParseTreeNode {


	public AbstractFloatResultNode(String s) {
		super(s);
	}

/*
	public double evalD(HashMap<String, Double> valHS) throws ParseError {
 		 double x = (leftEvaluable != null ? leftEvaluable.evalD(valHS) : Double.NaN);
		 double y = (rightEvaluable != null ? rightEvaluable.evalD(valHS) : Double.NaN);
		 double ret = op(x, y);
		 return ret;
	}
 */
	
	public abstract double op(double x, double y);

 

	public DBase makeEvaluator(HashMap<String, Double> fixedHM) throws ExpressionError {
		return new DBase(makeEvaluable(fixedHM));
	}
	
	 
}
