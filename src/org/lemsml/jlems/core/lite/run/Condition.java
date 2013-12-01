package org.lemsml.jlems.core.lite.run;

import java.util.HashMap;

import org.lemsml.jlems.core.eval.BooleanEvaluator;
import org.lemsml.jlems.core.eval.DoubleEvaluator;
import org.lemsml.jlems.core.run.DoublePointer;
import org.lemsml.jlems.core.run.RuntimeError;

public class Condition {

	 
	BooleanEvaluator evaluator;
	
	ActionBlock actionBlock;
	
	public Condition(BooleanEvaluator de) {
		evaluator = de;
	}


	public boolean exec(HashMap<String, DoublePointer> variables) throws RuntimeError {
		boolean b = evaluator.evalptr(variables);
		return b;
	}


	public void setActionBlock(ActionBlock ab) {
		actionBlock = ab;
	}
	
	public ActionBlock getActionBlock() {
		return actionBlock;
	}
	

}
