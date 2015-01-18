package org.lemsml.jld.eval;

import java.util.HashMap;
import java.util.HashSet;

import org.lemsml.jld.hrun.RuntimeError;
 

public interface DoubleEvaluator {

	
	double evalD(HashMap<String, Double> valHM);

	double evalptr(HashMap<String, DoublePointer> varHM) throws RuntimeError;

	double evalptr(HashMap<String, DoublePointer> varHM, HashMap<String, DoublePointer> v2hm);

	DoubleEvaluator makePrefixedCopy(String pfx, HashSet<String> indHS);

	void substituteVariableWith(String vnm, String pth);

	boolean variablesIn(HashSet<String> known);

	String getExpressionString();

	String getReversePolishExpressionString();

	boolean isTrivial();

	String getSimpleValueName();
	
	
}
