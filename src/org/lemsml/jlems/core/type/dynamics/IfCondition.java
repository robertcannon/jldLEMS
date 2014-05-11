package org.lemsml.jlems.core.type.dynamics;

import java.util.HashMap;

import org.lemsml.jlems.core.expression.ExpressionParser;
import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.expression.ParseTree;
import org.lemsml.jlems.core.expression.Valued;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.LemsCollection;

public class IfCondition extends PointResponse {

	public String test;
	
	ParseTree parseTree;
	 

	public IfCondition makeCopy() {
		IfCondition ret = new IfCondition();
		
		ret.test = test;
		
		super.copyInto(ret);
		
		return ret;
	}


	public void resolve(Dynamics bhv, LemsCollection<StateVariable> stateVariables, HashMap<String, Valued> valHM, ExpressionParser expressionParser) throws ContentError, ParseError {
		supResolve(bhv, stateVariables, valHM, expressionParser);
		
		parseTree = expressionParser.parseCondition(test);
	  	 
	}


	public ParseTree getParseTree() {
		return parseTree;
	}
	
	
	
	
}
