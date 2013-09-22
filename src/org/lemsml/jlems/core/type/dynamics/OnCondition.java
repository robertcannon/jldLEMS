package org.lemsml.jlems.core.type.dynamics;

import java.util.HashMap;

import org.lemsml.jlems.core.expression.Dimensional;
import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.expression.ParseTree;
import org.lemsml.jlems.core.expression.ExpressionParser;
import org.lemsml.jlems.core.expression.Valued;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.LemsCollection;

public class OnCondition extends PointResponse   {
	
	public String test; 

	ParseTree parseTree;
 
	
	public Trigger trigger;
	 
   
	
	public void resolve(Dynamics bhv, LemsCollection<StateVariable> stateVariables, HashMap<String, Valued> valHM, ExpressionParser expressionParser) throws ContentError, ParseError {
		if (trigger != null) {
			test = trigger.getCondition();
		}
	 
		parseTree = expressionParser.parseCondition(test);
  	 
		supResolve(bhv, stateVariables, valHM, expressionParser);
	}



	
	
	public void checkConditionDimensions(HashMap<String, Dimensional> dimHM) throws ContentError {
		parseTree.checkDimensions(dimHM);
	}

	

	public OnCondition makeCopy() {
		OnCondition ret = new OnCondition();
		ret.test = test;
		if (trigger != null) {
			ret.trigger = trigger.makeCopy();
		}
		super.copyInto(ret);
		return ret;
	}



	public ParseTree getParseTree() {
		 return parseTree;
	}
	
}
