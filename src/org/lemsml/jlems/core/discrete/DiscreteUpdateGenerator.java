package org.lemsml.jlems.core.discrete;

import java.util.ArrayList;
import java.util.HashMap;

import org.lemsml.jlems.core.codger.metaclass.Constructor;
import org.lemsml.jlems.core.codger.metaclass.MetaClass;
import org.lemsml.jlems.core.codger.metaclass.MetaInterface;
import org.lemsml.jlems.core.codger.metaclass.Method;
import org.lemsml.jlems.core.codger.metaclass.MethodCall;
import org.lemsml.jlems.core.codger.metaclass.Product;
import org.lemsml.jlems.core.codger.metaclass.VarType;
import org.lemsml.jlems.core.dimensionless.FloatAssignment;
import org.lemsml.jlems.core.eval.DVar;
import org.lemsml.jlems.core.eval.Plus;
import org.lemsml.jlems.core.eval.Times;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.numerics.Gradient;
import org.lemsml.jlems.core.numerics.GradientStateIncrement;
import org.lemsml.jlems.core.numerics.IntegrationScheme;
import org.lemsml.jlems.core.numerics.IntegrationStep;
import org.lemsml.jlems.core.numerics.WorkState;
import org.lemsml.jlems.core.run.ExpressionDerivedVariable;
import org.lemsml.jlems.core.run.MultiStateType;
import org.lemsml.jlems.core.run.PathDerivedVariable;
import org.lemsml.jlems.core.run.StateType;
import org.lemsml.jlems.core.run.VariableROC;
import org.lemsml.jlems.core.type.Component;
 

public class DiscreteUpdateGenerator {

	StateType stateType;
	IntegrationScheme scheme;
	
	public DiscreteUpdateGenerator(StateType st, IntegrationScheme s) {
		stateType = st;
		scheme = s;
	}

	
	public DiscreteUpdateModel buildDiscreteUpdateModel() {
		DiscreteUpdateModel ret = new DiscreteUpdateModel(stateType.getID());
		
		
		
		for (String s : stateType.getStateVariables()) {
			ret.addStateVariable(s);
		}
		
		for (PathDerivedVariable pdv : stateType.getPathderiveds()) {
			E.error("Discrete update generator can't handle path derivd variables - should all be " +
					"flattened first: " + pdv);
	 		}
 
		
		
		for (VariableROC vroc : stateType.getRates()) {
			String rnm = makeRateVar(vroc.getVariableName());
			// ret.addIfNewStateVariable(rnm);
		}
		
		
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
		
		
		return ret;
	}
		
	
	
	
	
	
	private void addEulerUpdate(DiscreteUpdateModel ret) {
		for (VariableROC vroc : stateType.getRates()) {
			String vnm = vroc.getVariableName();
			String rnm = makeRateVar(vnm);
		 
			ret.addFloatAssignment(rnm, vroc.getTextExpression());
		}
		
		  
		
		for (VariableROC vroc : stateType.getRates()) {
			String vnm = vroc.getVariable();
			
			
			String r = makeRateVar(vnm);
			// Forward euler
			Times t = new Times(new DVar(r), new DVar("dt"));
			Plus p = new Plus(new DVar(vnm), t);
			String expr = p.toExpression();			 
			ret.addUpdateFloatAssignment(vnm, expr);
		}
	}
	
	
	private void addSchemeUpdate(DiscreteUpdateModel ret) {
		for (WorkState ws : scheme.getWorkStates()) {
			E.missing();
		}
		
		for (IntegrationStep istep : scheme.getIntegrationSteps()) {
			for (GradientStateIncrement gsi : istep.getGradientStateIncrements()) {
				
				addGSI(gsi, ret);
				
			}
		}
	}
	
	
	private void addGSI(GradientStateIncrement gsi, DiscreteUpdateModel ret) {
		if (gsi.gradient != null) {
			
			Gradient grad = gsi.gradient;
			if (grad.at == "stepStart") {
		
			// TODO - get suffix for vars according to at, work state should have been eval'd.
				
				
			for (VariableROC vroc : stateType.getRates()) {
				String vnm = vroc.getVariableName();
				String rnm = makeRateVar(vnm);
			 
				ret.addFloatAssignment(rnm, vroc.getTextExpression());
			}
			
			  
			
			for (VariableROC vroc : stateType.getRates()) {
				String vnm = vroc.getVariable();
				
				
				String r = makeRateVar(vnm);
				// Forward euler
				Times t = new Times(new DVar(r), new DVar("dt"));
				Plus p = new Plus(new DVar(vnm), t);
				String expr = p.toExpression();			 
				ret.addUpdateFloatAssignment(vnm, expr);
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
