package org.lemsml.jld.eval;

import java.util.HashMap;

import org.lemsml.jld.hrun.RuntimeError;
  
public interface BooleanEvaluator {

	boolean evalB(HashMap<String, Double> valHM);

	BooleanEvaluator makeCopy();

	Boolean evalptr(HashMap<String, DoublePointer> varHM) throws RuntimeError;
	
	String getExpressionString();

	String getLemsExpressionString();
	
}
