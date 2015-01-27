package org.lemsml.jld.hsim;

import java.util.HashMap;
import java.util.HashSet;

import org.lemsml.jld.eval.BooleanEvaluator;
import org.lemsml.jld.eval.DoubleEvaluator;
import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jld.hrun.ActionBlock;
import org.lemsml.jld.hrun.ConditionAction;
import org.lemsml.jld.hrun.EventAction;
import org.lemsml.jld.hrun.RuntimeError;
import org.lemsml.jld.hrun.StateType;
import org.lemsml.jld.imodel.dynamics.IDerivedVariable;
import org.lemsml.jld.imodel.dynamics.IDynamics;
import org.lemsml.jld.imodel.dynamics.IStateVariable;
import org.lemsml.jld.imodel.dynamics.ITimeDerivative;
import org.lemsml.jld.io.E;
import org.lemsml.jld.model.core.AbstractAST;
import org.lemsml.jld.model.dynamics.AbstractDynamicsBlock;
import org.lemsml.jld.model.dynamics.Dynamics;
import org.lemsml.jld.model.dynamics.EventOut;
import org.lemsml.jld.model.dynamics.OnCondition;
import org.lemsml.jld.model.dynamics.OnEvent;
import org.lemsml.jld.model.dynamics.OnStart;
import org.lemsml.jld.model.dynamics.StateAssignment;
import org.lemsml.jld.model.dynamics.Transition;

public class DynamicsBuilder {

	
	
	
	public void applyDynamics(IDynamics dynamics, StateType ret, HashMap<String, Double> fxdHM) {
	 	 
		// ret.setSimultaneous(dynamics.getSimultaneous());
        
        HashMap<String, Double> fixedHM = new HashMap<String, Double>();
        fixedHM.putAll(fxdHM);
        // fixedHM should just contain global constants, we don't want 
        // the parameter values in there as these can still be overridden.
        
        for (String s : fixedHM.keySet()) {
       	 E.info("FIXING Elt in fixedHM " + s + " " + fixedHM.get(s));
        }
        
         
		 
		 HashSet<IStateVariable> varHS = new HashSet<IStateVariable>();
		 for (IStateVariable sv : dynamics.getIStateVariables()) {
			varHS.add(sv); 
			try {
				ret.addStateVariable(sv.getName(), sv.getDimension());
			} catch (RuntimeError e) {
				E.error("Error adding state variable " + e);
			}
			// TODO should have dereferenced Exposure object by now: get that and use it instead
			String expo = sv.getExposure();
			if (expo != null) {
				ret.addExposureMapping(sv.getName(), expo);
			}
		 }
	
		 /*
		 for (ExternalQuantity equan : externalQuantitys) {
			 String qn = equan.getName();
			 double qv = PathEvaluator.getValue(cpt, equan.getPath());
			 ret.addFixed(qn, qv);
			 fixedHM.put(qn, qv); // MUSTDO we don't need both of these: 
			 // either Ext quans shouldn't say they are fixed and we should use ret.addFixed, or it should 
			 // and use fixedHM.put.
		 }
		 */
		 
		 
		 for (IDerivedVariable dv : dynamics.getIDerivedVariables()) {
			 AbstractAST ast = dv.getAST();
			 
			 String select = dv.getSelect();
			 
			 if (ast != null) {
				
				try {
				DoubleEvaluator db = ast.makeFloatFixedEvaluator(fxdHM);
				ret.addExpressionDerived(dv.getName(), db, dv.getDimension());
				} catch (ExpressionError e) {
					E.error("Exception processing expression: " + e);
				}
				
            	 
			 } else if (select != null) {	 
				 boolean isRequired = false; // dv.getRequired();
				 
				 ret.addPathDerived(dv.getName(), select, isRequired, dv.getReduce(), dv.getDimension());
				 
			 } else {
				E.error("Derived variable " + dv + " should contain either a value expression or a path selection.");
			 }
			 String en = dv.getExposure();
			 if (en != null) {
				 ret.addExposureMapping(dv.getName(), en);
			 }
		 }
		 
		 /*
		 for (ConditionalDerivedVariable cdv : conditionalDerivedVariables) {
			  
			 DoubleEvaluator db = cdv.makeFloatFixedEvaluator(fixedHM);
	 		 ret.addExpressionDerived(cdv.getName(), db, cdv.getDimensionString());
            	 
			 
           if (cdv.hasExposure()) {
               ret.addExposureMapping(cdv.getName(), cdv.getExposure().getName());
           }
		 }
		 */
		 
		 
		 for (ITimeDerivative sd : dynamics.getITimeDerivatives()) {
			 IStateVariable sv = sd.getStateVariable();
			 varHS.remove(sv);
			 AbstractAST ast = sd.getAST();
		 
			 try {
				 DoubleEvaluator dev = ast.makeFloatFixedEvaluator(fixedHM);
		 
				 ret.addRate(sv.getName(), dev, sv.getDimension());
			 } catch (ExpressionError e) {
				 E.error("Error with TimeDerivative: " + e);
			 }
		 }
		 
		 
		 
		 // TODO tmp
		 Dynamics rdynamics = (Dynamics)dynamics;
		 
		 for (OnStart os : rdynamics.getOnStarts()) {
			 ActionBlock ea = makeEventAction(os, fixedHM);
			 ret.addInitialization(ea);
		 }
		 
		 for (OnEvent oe : rdynamics.getOnEvents()) {
			 EventAction er = new EventAction(oe.getPortName());	 
			 ActionBlock ea = makeEventAction(oe, fixedHM);
			 er.setAction(ea);
			 ret.addEventResponse(er);
		 }
		 
		 
		 for (OnCondition oc : rdynamics.getOnConditions()) {
		
			 AbstractAST pt = oc.getAST();
			 if (pt == null) {
				 E.error("No AST in OnCondition");
			 } else {
			 try {
				 BooleanEvaluator bb = pt.makeBooleanFixedEvaluator(fixedHM);
			
				 ConditionAction cr = new ConditionAction(bb);
				 ActionBlock ea = makeEventAction(oc, fixedHM);
				 cr.setAction(ea);
				 ret.addConditionResponse(cr);
			 } catch (Exception e) {
				 E.error("cant make onCondition " + e);
			 }
			 }
		 }
		 
		 /*
		 
		 if (regimes.size() > 0) {
			 for (ReceivePort p :  r_type.getReceivePorts()) {
				 if (onEvents.hasName(p.getName())) {
						 // OK, the existing action will also send the event on to the active regime
				 } else {
					 EventAction er = new EventAction(p.getName());
					 ret.addEventResponse(er);
				 }
			 }
		 }
		 
	 
		 
		 for (KineticScheme ks : kineticSchemes) {
			 ArrayList<IComponent> states = cpt.getChildrenAL(ks.getNodesName());
			 ArrayList<IComponent> rates = cpt.getChildrenAL(ks.getEdgesName());
			 
			 KScheme scheme = ks.makeKScheme(states, rates);
			 
			 ret.addKScheme(scheme);
		 }
	 
		 for (ReceivePort p : r_type.getReceivePorts()) {
			 ret.addInputPort(p.getName());
			 // TODO - also need output ports done the same way, in case send action is in a sub-regime
		 }
		  	
		 for (Regime reg : regimes) {
			  IComponentRegime crb = reg.makeIComponentRegime(ret, cpt, copyFixed(fixedHM));
			  ret.addIComponentRegime(crb);
		 }
		 */
		 
		 
		 // TODO - could do something with children of parent type here, but don't have to as they 
		 // come in again via the structure of the IComponent itself.
		 
		 /*
		 for (StateVariable sv : varHS) {
			// E.info("sv without derivative " + sv + " in mcb for " + cpt.getID() + " " + cpt.hashCode());
		 }
		 */
	
		 
		 ret.fix();
	}

	
	 private ActionBlock makeEventAction(AbstractDynamicsBlock pr, HashMap<String, Double> fixedHM) {
		 ActionBlock ret = new ActionBlock();
		 
		
		 for (StateAssignment sa : pr.getStateAssignments()) {
			 AbstractAST ast = sa.getAST();
			 try {
			 DoubleEvaluator dase = ast.makeFloatFixedEvaluator(fixedHM); 
			 ret.addAssignment(sa.getVariable(), dase);
			 } catch (Exception ex) {
				 E.error("Cant make evaluator in block " + ex);
			 }
		} 
		 
		 /*
		 for (IfCondition ic : pr.getIfConditions()) {
			 ParseTree pt = ic.getParseTree();
			 BooleanEvaluator be = pt.makeBooleanFixedEvaluator(fixedHM);
			 ActionBlock ab = ic.makeEventAction(fixedHM);
			 ret.addConditionalActionBlock(be, ab);
		 }
		 */
		 
		 
		 for (EventOut eout : pr.getEventOuts()) {
			 ret.addEventOut(eout.getPort());
		 }
		 
		 for (Transition t : pr.getTransitions()) {
			E.missing();
			 //  ret.addTransition(t.getRegime());
		 }
		 return ret;
	 }
	
	
	
}
