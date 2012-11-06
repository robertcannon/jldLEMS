package org.lemsml.jlems.expression;

import java.util.HashMap;

import org.lemsml.jlems.sim.ContentError;

public abstract class BooleanOperatorNode extends OperatorNode implements BooleanParseTreeNode {

	BooleanParseTreeNode leftEvaluable;
	BooleanParseTreeNode rightEvaluable;
	
	public BooleanOperatorNode(String s) {
		super(s);
	}
 
	
	public abstract boolean bool(boolean x, boolean y);
	
	 

	public Dimensional getDimensionality(HashMap<String, Dimensional> dimHM) throws ContentError {
		Dimensional dl = leftEvaluable.getDimensionality(dimHM);
		Dimensional dr = rightEvaluable.getDimensionality(dimHM);
		Dimensional ret = null;
		if (dl != null && dr != null) {
			ret = dimop(dl, dr);
		} else {
			throw new ContentError("Null dimension in operator: " + dl + " " + dr + " operator: " + symbol);
		}
		return ret;
	}

	public abstract Dimensional dimop(Dimensional dl, Dimensional dr) throws ContentError;
	
	 
 

	public Dimensional evaluateDimensional(HashMap<String, Dimensional> dhm) throws ContentError {
		throw new ContentError("Can't apply boolean operations to dimensions");
	}
	

	
	
}
 