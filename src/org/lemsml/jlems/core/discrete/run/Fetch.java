package org.lemsml.jlems.core.discrete.run;

import java.util.HashMap;

import org.lemsml.jlems.core.eval.DoubleEvaluator;
import org.lemsml.jlems.core.run.DoublePointer;
import org.lemsml.jlems.core.run.RuntimeError;
import org.lemsml.jlems.core.run.StateRunnable;

public class Fetch {

	
	String varName;
	 	
	
	public Fetch(String vnm) {
		varName = vnm;
	}


	public void exec(HashMap<String, DoublePointer> variables, StateRunnable parent) throws RuntimeError {
		double d = parent.getVariable(varName);
		variables.get(varName).set(d);
	}
	
	
	

}
