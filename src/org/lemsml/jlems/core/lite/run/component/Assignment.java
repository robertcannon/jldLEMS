package org.lemsml.jlems.core.lite.run.component;

import java.util.HashMap;

import org.lemsml.jlems.core.eval.DoubleEvaluator;
import org.lemsml.jlems.core.run.DoublePointer;
import org.lemsml.jlems.core.run.RuntimeError;

public class Assignment {

	
	String varName;
	DoubleEvaluator evaluator;
	
	
	public Assignment(String vnm, DoubleEvaluator de) {
		varName = vnm;
		evaluator = de;
	}

	public String getVariableName() {
		return varName;
	}
	

	public void exec(HashMap<String, DoublePointer> variables) throws RuntimeError {
		double d = evaluator.evalptr(variables);
		variables.get(varName).set(d);
	}

	public double evaluate(HashMap<String, DoublePointer> vars) throws RuntimeError {
		double d = evaluator.evalptr(vars);
		return d;
	}
	 

}
