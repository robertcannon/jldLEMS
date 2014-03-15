package org.lemsml.jlems.core.lite.convert;

import org.lemsml.jlems.core.expression.ExpressionParser;
import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.expression.ParseTree;
import org.lemsml.jlems.core.lite.model.Constant;
import org.lemsml.jlems.core.lite.model.DiscreteUpdateComponent;
import org.lemsml.jlems.core.lite.model.Emit;
import org.lemsml.jlems.core.lite.model.InputVariable;
import org.lemsml.jlems.core.lite.model.OnAbstract;
import org.lemsml.jlems.core.lite.model.OnCondition;
import org.lemsml.jlems.core.lite.model.OnEvent;
import org.lemsml.jlems.core.lite.model.Output;
import org.lemsml.jlems.core.lite.model.Parameter;
import org.lemsml.jlems.core.lite.model.StateVariable;
import org.lemsml.jlems.core.lite.model.Update;
import org.lemsml.jlems.core.lite.model.Var;
import org.lemsml.jlems.core.lite.run.component.ActionBlock;
import org.lemsml.jlems.core.lite.run.component.Assignment;
import org.lemsml.jlems.core.lite.run.component.Condition;
import org.lemsml.jlems.core.lite.run.component.Copy;
import org.lemsml.jlems.core.lite.run.component.DiscreteUpdateStateType;
import org.lemsml.jlems.core.lite.run.component.EmitAction;
import org.lemsml.jlems.core.lite.run.component.EventResponse;
import org.lemsml.jlems.core.lite.run.component.Fetch;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.sim.ContentError;
 
public class DUStateTypeBuilder {

	DiscreteUpdateComponent src;
	
	
	public DUStateTypeBuilder(DiscreteUpdateComponent dum) {
		src = dum;
	}


	public DiscreteUpdateStateType makeDiscretUpdateStateType() throws ParseError, ContentError {
		DiscreteUpdateStateType ret = new DiscreteUpdateStateType();
		ret.setID(src.getID());
		
		ExpressionParser expressionParser = new ExpressionParser();

		for (InputVariable iv : src.getInputVariables()) {
			Fetch f = new Fetch(iv.getName());
			ret.addFetch(f);
		}
			
		
		for (Var fa : src.getVars()) {			
			ParseTree pt = expressionParser.parseExpression(fa.getExpression());
			Assignment as = new Assignment(fa.getVariableName(), pt.makeFloatEvaluator());
			ret.addAssignment(as);
			 
		}
		
		for (Update ua : src.getUpdateAssignments()) {
			ParseTree pt = expressionParser.parseExpression(ua.getExpression());
			Assignment as = new Assignment(ua.getVariableName(), pt.makeFloatEvaluator());
			ret.addAssignment(as);
		}
		

	
		
		for (Output ve : src.getOutputs()) {
			Copy c = new Copy(ve.getLocalName(), ve.getExposedName());
			
		    E.info("Added exposure " + ve.getLocalName() + " " + ve.getExposedName());
			ret.addExposure(c);
		}
		
		
		for (OnCondition oc : src.getOnConditions()) {
			ParseTree pt = expressionParser.parseCondition(oc.getExpression());
			Condition c = new Condition(pt.makeBooleanEvaluator());
			
			c.setActionBlock(makeActionBlock(oc, expressionParser));
			
			ret.addCondition(c);
		}
		
		for (OnEvent oe : src.getOnEvents()) {
			String pnm = oe.getPortName();
			EventResponse er = new EventResponse(pnm);
			er.setActionBlock(makeActionBlock(oe, expressionParser));
			ret.addEvent(pnm, er);
			 
		}
		
		for (Constant c : src.getConstants()) {
			ret.addConstant(c.getName(), c.getValue());
		}
		
		for (Parameter p : src.getParameters()) {
			ret.addParameter(p.getName());
		 
		}
		
		for (InputVariable iv : src.getInputVariables()) {
			ret.addInputVariable(iv.getName());
		}
		
		for (StateVariable sv : src.getStateVariables()) {
			ret.addStateVariable(sv.getName());
		}
		
		return ret;
	}

	

	private ActionBlock makeActionBlock(OnAbstract oc, ExpressionParser expressionParser) throws ParseError, ContentError {
		ActionBlock ret = new ActionBlock();
		for (Var fa : oc.getVars()) {			
			ParseTree pt = expressionParser.parseExpression(fa.getExpression());
			Assignment as = new Assignment(fa.getVariableName(), pt.makeFloatEvaluator());
			ret.addAssignment(as);
		}
		for (Update ua : oc.getUpdates()) {
			ParseTree pt = expressionParser.parseExpression(ua.getExpression());
			Assignment as = new Assignment(ua.getVariableName(), pt.makeFloatEvaluator());
			ret.addAssignment(as);
		}
		
		for (Emit e : oc.getEmits()) {
			ret.addEmitAction(new EmitAction(e.getPort()));
		}
		
		return ret;
	}
	
	
}
