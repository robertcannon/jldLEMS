package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jlems.core.run.RuntimeError;
 

public abstract class AbstractComparisonNode extends AbstractFloatOperatorNode implements BooleanParseTreeNode {


	public AbstractComparisonNode(String s) {
		super(s);
	}
 
	
	public abstract boolean compare(double x, double y) throws RuntimeError;
	
	 
    @Override
	public Dim dimop(Dim dl, Dim dr) throws ExpressionError {
		Dim ret = null;
		if (dl.matches(dr) || dl.isAny() || dr.isAny()) {
			ret = new Dim();
		} else {
			throw new ExpressionError("Mismatched dimensions in comparison, left: (" + dl + "), right: (" + dr+")");
		}
		return ret;
	}


    @Override
	public void checkDimensions(HashMap<String, Dim> dimHM) throws ExpressionError {
		getDimensionality(dimHM); 
	}

	public abstract boolean compareInts(long ileft, long iright);


    @Override
	public Dim evaluateDimensional(HashMap<String, Dim> dhm) throws ExpressionError {
		throw new ExpressionError("Can't apply boolean operations to comparisons");
	}
	

}
