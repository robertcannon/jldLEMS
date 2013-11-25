package org.lemsml.jlems.core.lite;

import java.util.HashMap;

import org.lemsml.jlems.core.dimensionless.FloatAssignment;
import org.lemsml.jlems.core.dimensionless.OnCondition;
import org.lemsml.jlems.core.dimensionless.OnEvent;
 
import org.lemsml.jlems.core.eval.BooleanEvaluator;
import org.lemsml.jlems.core.eval.DVar;
import org.lemsml.jlems.core.eval.Plus;
import org.lemsml.jlems.core.eval.Times;
import org.lemsml.jlems.core.lite.model.DiscreteUpdateComponent;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.numerics.Gradient;
import org.lemsml.jlems.core.numerics.GradientStateIncrement;
import org.lemsml.jlems.core.numerics.IntegrationScheme;
import org.lemsml.jlems.core.numerics.IntegrationStep;
import org.lemsml.jlems.core.numerics.WorkState;
import org.lemsml.jlems.core.run.ActionBlock;
import org.lemsml.jlems.core.run.ConditionAction;
import org.lemsml.jlems.core.run.EventAction;
import org.lemsml.jlems.core.run.ExpressionDerivedVariable;
import org.lemsml.jlems.core.run.MultiStateType;
import org.lemsml.jlems.core.run.PathDerivedVariable;
import org.lemsml.jlems.core.run.StateType;
import org.lemsml.jlems.core.run.VariableAssignment;
import org.lemsml.jlems.core.run.VariableROC;
 

public class DiscreteUpdateGenerator {

	StateType stateType;
	IntegrationScheme scheme;
	
	public DiscreteUpdateGenerator(StateType st, IntegrationScheme s) {
		stateType = st;
		scheme = s;
	}

	
	public DiscreteUpdateComponent buildDiscreteUpdateComponent() {
		DiscreteUpdateComponent ret = new DiscreteUpdateComponent(stateType.getID());
		
		 
		for (String s : stateType.getStateVariables()) {
			ret.addStateVariable(s);
		}
		
	
		
		
		for (PathDerivedVariable pdv : stateType.getPathderiveds()) {
			E.error("Discrete update generator can't handle path derivd variables - should all be " +
					"flattened first: " + pdv);
	 		}
 
		
		/*
		for (VariableROC vroc : stateType.getRates()) {
			String rnm = makeRateVar(vroc.getVariableName());
			ret.addIfNewStateVariable(rnm);
		}
		*/
		
		HashMap<String, StateType> chm = stateType.getChildHM();
		for (String s : chm.keySet()) {
			E.error("Unhandled child in state type " + s);
		}
		
		HashMap<String, MultiStateType> mchm = stateType.getMultiHM();
		for (String s : mchm.keySet()) {
			E.error("Unhandled child in state type " + s);
		}
		
		  
	 	
		HashMap<String, String> ehm = stateType.getExposureMap();
		for (String s : ehm.keySet()) {
			ret.addFloatExposure(s, ehm.get(s));
		}
	 
		for (String s : stateType.getAllIndeps()) {
			ret.addIndependentVariagble(s);
		}
		
		 
		// TODO
//		cb.sortExpressions();
		for (ExpressionDerivedVariable edv : stateType.getExderiveds()) {
		
			FloatAssignment fa = new FloatAssignment(edv.getVariableName(), edv.getExpressionString());
			fa.setReversePolishExpression(edv.getReversePolishExpressionString());
			ret.addFloatAssignment(fa);
		}
		
		
		if (scheme != null) {
			addSchemeUpdate(ret);
		
		} else {
			addEulerUpdate(ret);
		}
		
		
		for (EventAction ea : stateType.getEventActions()) {
			addEventAction(ret, ea);
		}
		
		for (ConditionAction ca : stateType.getConditionActions()) {
			addConditionAction(ret, ca);
		}
		
		return ret;
	}
		
	
	private void addEventAction(DiscreteUpdateComponent ret, EventAction ea) {
	 
		OnEvent oe = ret.addOnEvent(ea.getPortName());
		
		ActionBlock ab = ea.getAction();
	 	
		for (VariableAssignment ve : ab.getAssignments()) {
			FloatAssignment fa = new FloatAssignment(ve.getVarName(), ve.getValexp().getExpressionString());
			oe.addFloatAssignment(fa);
		}
	}
	
	
	private void addConditionAction(DiscreteUpdateComponent ret, ConditionAction ca) {
		 
		BooleanEvaluator be = ca.getCondition();
		OnCondition os = ret.addOnCondition(be.getExpressionString());
		ActionBlock ab = ca.getAction();
		
 		
		for (VariableAssignment ve : ab.getAssignments()) {
 			FloatAssignment fa = new FloatAssignment(ve.getVarName(), ve.getValexp().getExpressionString());
			os.addFloatAssignment(fa);
		}
		
		for (String s : ab.getOutEvents()) {
			os.addSend(s);
		}
	}
	
	
	private void addEulerUpdate(DiscreteUpdateComponent ret) {
		for (VariableROC vroc : stateType.getRates()) {
			String vnm = vroc.getVariableName();
			String rnm = makeRateVar(vnm);
			
			FloatAssignment fa = new FloatAssignment(rnm, vroc.getTextExpression());
			fa.setReversePolishExpression(vroc.getReversePolishExpressionString());
			ret.addFloatAssignment(fa);
 		}
		
		  
		
		for (VariableROC vroc : stateType.getRates()) {
			String vnm = vroc.getVariable();
			
			
			String r = makeRateVar(vnm);
			// Forward euler
			Times t = new Times(new DVar(r), new DVar("dt"));
			Plus p = new Plus(new DVar(vnm), t);
			String expr = p.toExpression();			 
		
			FloatAssignment fa = new FloatAssignment(vnm, expr);
			fa.setReversePolishExpression(vnm + " " + r + " dt * +");
			ret.addUpdateFloatAssignment(fa);
		
		}
	}
	
	
	private void addSchemeUpdate(DiscreteUpdateComponent ret) {
		for (WorkState ws : scheme.getWorkStates()) {
			E.missing("Scheme update: " + ws);
		}
		
		for (IntegrationStep istep : scheme.getIntegrationSteps()) {
			for (GradientStateIncrement gsi : istep.getGradientStateIncrements()) {
				
				addGSI(gsi, ret);
				
			}
		}
	}
	
	
	private void addGSI(GradientStateIncrement gsi, DiscreteUpdateComponent ret) {
		if (gsi.gradient != null) {
			
			Gradient grad = gsi.gradient;
			if (grad.at.equals("stepStart")) {
		
			// TODO - get suffix for vars according to at, work state should have been eval'd.
				
				
			for (VariableROC vroc : stateType.getRates()) {
				String vnm = vroc.getVariableName();
				String rnm = makeRateVar(vnm);
			 
				FloatAssignment fa = new FloatAssignment(rnm, vroc.getTextExpression());
				fa.setReversePolishExpression(vroc.getReversePolishExpressionString());
				ret.addFloatAssignment(fa);
				
			}
			
			  
			
			for (VariableROC vroc : stateType.getRates()) {
				String vnm = vroc.getVariable();
				
				
				String r = makeRateVar(vnm);
				// Forward euler
				Times t = new Times(new DVar(r), new DVar("dt"));
				Plus p = new Plus(new DVar(vnm), t);
				String expr = p.toExpression();			 
				FloatAssignment fa = new FloatAssignment(vnm, expr);
				fa.setReversePolishExpression(vnm + " " + r + " dt * +");
				ret.addUpdateFloatAssignment(fa);
			}
			
			} else {
				E.missing();
			}
			
		} else if (gsi.meanGradient != null) {
			E.missing();
			
		} else {
			E.error("Can't handle gsi: " + gsi);
		}
		
	}
	
	
	
	private String makeRateVar(String vnm) {
		String ret = "d_" + vnm + "_dt";
		return ret;
	}
	
	
	
	
	
}
