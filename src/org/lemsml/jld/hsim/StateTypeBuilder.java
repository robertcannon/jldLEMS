package org.lemsml.jld.hsim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.lemsml.jld.eval.DoubleEvaluator;
import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jld.expression.ParseError;
import org.lemsml.jld.hrun.RunConfig;
import org.lemsml.jld.hrun.RuntimeError;
import org.lemsml.jld.hrun.StateType;
import org.lemsml.jld.imodel.IComponent;
import org.lemsml.jld.imodel.IComponentType;
import org.lemsml.jld.imodel.IConstant;
import org.lemsml.jld.imodel.ILems;
import org.lemsml.jld.imodel.dynamics.IDerivedVariable; 
import org.lemsml.jld.imodel.dynamics.IDynamics;
import org.lemsml.jld.imodel.dynamics.IStateVariable;
import org.lemsml.jld.imodel.dynamics.ITimeDerivative;
import org.lemsml.jld.imodel.simulation.IRun;
import org.lemsml.jld.imodel.simulation.ISimulation;
import org.lemsml.jld.io.E;
import org.lemsml.jld.model.core.AbstractAST;
 
 
 

public class StateTypeBuilder {

	
	private ILems lems;
	
	
	
	private HashMap<IComponent, StateType> stateTypeMap = new HashMap<IComponent, StateType>();
	
	
	public StateTypeBuilder(ILems lems) {
		this.lems = lems;
	}


	private StateType getOrMakeStateType(IComponent target) {
		StateType ret = null;
		if (stateTypeMap.containsKey(target)) {
			ret = stateTypeMap.get(target);
		} else {
			ret = makeStateType(target);
			stateTypeMap.put(target, ret);
		}
		return ret;
	}
	
	
	public StateType makeStateType(IComponent target) {
	 
		HashMap<String, Double> fixedHM = new HashMap<String, Double>();

		for (IConstant c : lems.getIConstants()) {
			fixedHM.put(c.getName(), c.getValue());
		}
		
		
		IComponentType type = target.getIComponentType();
		
		ArrayList<IComponentType> typeChain = getTypeChain(type);
		
		StateType ret = new StateType(target.getId(), type.getName());
		
		
		 
		 
		for (IComponentType c : typeChain) {

			for (String qn : c.getParameterNames()) {
				
//				IParameterValue pv = target.getParameterValue(qn);
				double qv = target.getSIParameterValue(qn);
 				ret.addFixed(qn, qv);
			}
			
			for (String rn : c.getRequirementNames()) {
				ret.addIndependentVariable(rn, c.getDimension(rn));
			}
		 
			for (String en : c.getExposureNames()) {
				ret.addExposedVariable(en, c.getDimension(en));
			}
			
			for (String pnm : c.getPropertyNames()) {
				ret.addExposureMapping(pnm, pnm);
			}
			
			for (String tnm : c.getTextNames()) {
			 
				String tv = target.getTextParameterValue(tnm);
				if (tv != null) {
					ret.addTextParam(tnm, tv);
				}
			}

		}
		
		IDynamics dynamics = type.getIDynamics();
		if (dynamics != null) {
			applyDynamics(dynamics, ret, fixedHM);
		}
		

		/* TODO
		for (Structure b : dynamics.getStructures()) {
			ret.addBuilder(b.makeBuilder(cpt));
		}
	 */
		
	
		
		for (String s : target.getChildNames()) {
			IComponent ch = target.getIChild(s);
			StateType st = getOrMakeStateType(ch);	
			ret.addChildStateType(s, st);
		}
 	 
			// if not directly defined?
			// ret.addRefStateType(s, chb);
		 
		for (String s : target.getChildrenNames()) {
			List<? extends IComponent> cpts = target.getIChildren(s);
			for (IComponent c : cpts) {
				StateType cst = getOrMakeStateType(c);
				ret.addListStateType(s, cst);
			}
		}
		
		
		 
	 
		/* 
		
		for (Attachments ats : getAttachmentss()) {
 			ret.addAttachmentSet(ats.getName(), ats.getIComponentType().getName());
		}

		for (Collection c : getCollections()) {
			ret.addInstanceSet(c.getName());
		}

		for (PairCollection c : getPairCollections()) {
			ret.addInstancePairSet(c.getName());
		}
		
		*/
		
		
		
		ISimulation sim = type.getISimulation();
		if (sim != null) {
			applySimulation(sim, target, ret);
		}
	 
		return ret;
	
	}
	
	
	
	
	private ArrayList<IComponentType> getTypeChain(IComponentType ct) {
		ArrayList<IComponentType> ret = new ArrayList<IComponentType>();
		IComponentType wk = ct;
		while (wk != null) {
			ret.add(wk);
			wk = wk.getSupertype();
		}
		Collections.reverse(ret);
		return ret;
	}
	
	
	
	
	private void applySimulation(ISimulation sim, IComponent target, StateType ret) {
		for (IRun run : sim.getIRuns()) {
			
			
			String incField = run.getIncrement();
			double dt = target.getSIParameterValue(incField);
	 		
			String totField = run.getTotal();
			double trun = target.getSIParameterValue(totField);
		 
			String fieldName = run.getComponent();
			

			IComponent runTarget = target.getIChild(fieldName);
			E.info("** processing a run, need '" + fieldName + "' within " + target + " got: " + runTarget);
			
			StateType targetST = stateTypeMap.get(runTarget);
			
			RunConfig rc = new RunConfig(targetST, dt, trun);
			ret.addRunConfig(rc);
			E.info("Added a run config to " + ret.hashCode() + " " + ret);
		}
			
	}

	
	

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
		 for (IStateVariable sv : dynamics.getStateVariables()) {
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
		 
		 
		 for (IDerivedVariable dv : dynamics.getDerivedVariables()) {
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
		 
		 
		 for (ITimeDerivative sd : dynamics.getTimeDerivatives()) {
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
		 
		 /*
		 
		 for (OnStart os : onStarts) {
			 ActionBlock ea = os.makeEventAction(fixedHM);
			 ret.addInitialization(ea);
		 }
		 
		 for (OnEvent oe : onEvents) {
			 EventAction er = new EventAction(oe.getPortName());	 
			 ActionBlock ea = oe.makeEventAction(fixedHM);
			 er.setAction(ea);
			 if (ea == null) {
				 throw new ContentError("Null action block from OnEvent " + oe);
			 }
			 ret.addEventResponse(er);
		 }
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
		 
		 
		 for (OnCondition oc : onConditions) {
		
			 ParseTree pt = oc.getParseTree();
			 BooleanEvaluator bb = pt.makeBooleanFixedEvaluator(fixedHM);
			
			 ConditionAction cr = new ConditionAction(bb);
			 ActionBlock ea = oc.makeEventAction(fixedHM);
			 cr.setAction(ea);
			 ret.addConditionResponse(cr);
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

	
	private HashMap<String, Double> copyFixed(HashMap<String, Double> fixedHM) {
		 HashMap<String, Double> ret = new HashMap<String, Double>();
		 for (String s : fixedHM.keySet()) {
			 ret.put(s, fixedHM.get(s));
		 }
		 return ret;
	}
	

	
	
	
	
	
	
}
