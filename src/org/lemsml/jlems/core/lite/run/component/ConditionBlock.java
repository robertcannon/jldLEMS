package org.lemsml.jlems.core.lite.run.component;

import java.util.ArrayList;
import java.util.HashMap;

import org.lemsml.jlems.core.eval.BooleanEvaluator;
import org.lemsml.jlems.core.run.DoublePointer;
import org.lemsml.jlems.core.run.RuntimeError;

public class ConditionBlock {

	BooleanEvaluator conditionEvaluator;
	ActionBlock subBlock;
	
	
	public ConditionBlock(BooleanEvaluator bev, ActionBlock ab) {
		conditionEvaluator = bev;
		subBlock = ab;
	}


	public boolean conditionHolds(HashMap<String, DoublePointer> variables) throws RuntimeError {
		 boolean ret = conditionEvaluator.evalptr(variables);
		 return ret;
	}
	
	public ActionBlock getActionBlock() {
		return subBlock;
	}
}
