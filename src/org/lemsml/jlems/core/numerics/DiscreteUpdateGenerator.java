package org.lemsml.jlems.core.numerics;

import java.util.HashMap;

 
import org.lemsml.jlems.core.eval.BooleanEvaluator;
import org.lemsml.jlems.core.eval.DVar;
import org.lemsml.jlems.core.eval.Plus;
import org.lemsml.jlems.core.eval.Times;
import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.lite.convert.DUStateTypeBuilder;
import org.lemsml.jlems.core.lite.model.DiscreteUpdateComponent;
import org.lemsml.jlems.core.lite.model.OnAbstract;
import org.lemsml.jlems.core.lite.model.OnEvent;
import org.lemsml.jlems.core.lite.model.Output;
import org.lemsml.jlems.core.lite.model.Update;
import org.lemsml.jlems.core.lite.model.Var;
import org.lemsml.jlems.core.lite.run.component.DiscreteUpdateStateType;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.run.ActionBlock;
import org.lemsml.jlems.core.run.ConditionAction;
import org.lemsml.jlems.core.run.EventAction;
import org.lemsml.jlems.core.run.ExpressionDerivedVariable;
import org.lemsml.jlems.core.run.MultiStateType;
import org.lemsml.jlems.core.run.PathDerivedVariable;
import org.lemsml.jlems.core.run.StateType;
import org.lemsml.jlems.core.run.VariableAssignment;
import org.lemsml.jlems.core.run.VariableROC;
import org.lemsml.jlems.core.sim.ContentError;
 

public class DiscreteUpdateGenerator {

	StateType stateType;
	IntegrationScheme scheme;
	
	public DiscreteUpdateGenerator(StateType st, IntegrationScheme s) {
		stateType = st;
		scheme = s;
	}

	
	public DiscreteUpdateStateType buildDiscreteUpdateStateType() throws ParseError, ContentError {

		DiscreteUpdateComponent duc = buildDiscreteUpdateComponent();

		DUStateTypeBuilder cdustg = new DUStateTypeBuilder(duc);
		DiscreteUpdateStateType ret = cdustg.makeDiscretUpdateStateType();
		return ret;
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
		
			Var fa = new Var(edv.getVariableName(), edv.getExpressionString());
			fa.setReversePolishExpression(edv.getReversePolishExpressionString());
		
			ret.addVar(fa);
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
		
		HashMap<String, String> expHM = stateType.getExposureMap();
		for (String s : expHM.keySet()) {
			Output out = new Output(s);
			out.setExpression(expHM.get(s));
			ret.addOutput(out);
		}
 		
		return ret;
	}
		
	
	private void addEventAction(DiscreteUpdateComponent ret, EventAction ea) {
	 
		OnEvent oe = ret.addOnEvent(ea.getPortName());
		
		ActionBlock ab = ea.getAction();
	 	
		for (VariableAssignment ve : ab.getAssignments()) {
			Var fa = new Var(ve.getVarName(), ve.getValexp().getExpressionString());
			oe.addVar(fa);
		}
	}
	
	
	private void addConditionAction(DiscreteUpdateComponent ret, ConditionAction ca) {
		 
		BooleanEvaluator be = ca.getCondition();
		OnAbstract os = ret.addOnCondition(be.getExpressionString());
		ActionBlock ab = ca.getAction();
		
 		
		for (VariableAssignment ve : ab.getAssignments()) {
 			Var fa = new Var(ve.getVarName(), ve.getValexp().getExpressionString());
			os.addVar(fa);
		}
		
		for (String s : ab.getOutEvents()) {
			os.addSend(s);
		}
	}
	
	
	private void addEulerUpdate(DiscreteUpdateComponent ret) {
		for (VariableROC vroc : stateType.getRates()) {
			String vnm = vroc.getVariableName();
			String rnm = makeRateVar(vnm);
			
			Var fa = new Var(rnm, vroc.getTextExpression());
			fa.setReversePolishExpression(vroc.getReversePolishExpressionString());
			ret.addVar(fa);
 		}
		
		  
		
		for (VariableROC vroc : stateType.getRates()) {
			String vnm = vroc.getVariable();
			
			
			String r = makeRateVar(vnm);
			// Forward euler
			Times t = new Times(new DVar(r), new DVar("dt"));
			Plus p = new Plus(new DVar(vnm), t);
			String expr = p.toExpression();			 
		
			Update fa = new Update(vnm, expr);
			fa.setReversePolishExpression(vnm + " " + r + " dt * +");
			ret.addUpdate(fa);
		
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
			 
				Var fa = new Var(rnm, vroc.getTextExpression());
				fa.setReversePolishExpression(vroc.getReversePolishExpressionString());
				ret.addVar(fa);
				
			}
			
			  
			
			for (VariableROC vroc : stateType.getRates()) {
				String vnm = vroc.getVariable();
				
				
				String r = makeRateVar(vnm);
				// Forward euler
				Times t = new Times(new DVar(r), new DVar("dt"));
				Plus p = new Plus(new DVar(vnm), t);
				String expr = p.toExpression();			 
				Update fa = new Update(vnm, expr);
				fa.setReversePolishExpression(vnm + " " + r + " dt * +");
				ret.addUpdate(fa);
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
