package org.lemsml.jld.hsim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import org.lemsml.jld.eval.DoubleEvaluator;
import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jld.expression.ParseError;
import org.lemsml.jld.hrun.RunConfig;
import org.lemsml.jld.hrun.RuntimeError;
import org.lemsml.jld.hrun.StateType;
import org.lemsml.jld.io.E;
import org.lemsml.jld.model.Component;
import org.lemsml.jld.model.Constant;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.ParameterValue;
import org.lemsml.jld.model.core.AbstractAST;
import org.lemsml.jld.model.dynamics.DerivedVariable;
import org.lemsml.jld.model.dynamics.Dynamics;
import org.lemsml.jld.model.dynamics.StateVariable;
import org.lemsml.jld.model.dynamics.TimeDerivative;
import org.lemsml.jld.model.simulation.Run;
import org.lemsml.jld.model.simulation.Simulation;
import org.lemsml.jld.model.type.ComponentType;
import org.lemsml.jld.model.type.Exposure;
import org.lemsml.jld.model.type.Parameter;
import org.lemsml.jld.model.type.Property;
import org.lemsml.jld.model.type.Requirement;
import org.lemsml.jld.model.type.Text;
 

public class StateTypeBuilder {

	
	private Lems lems;
	
	
	
	private HashMap<Component, StateType> stateTypeMap = new HashMap<Component, StateType>();
	
	
	public StateTypeBuilder(Lems lems) {
		this.lems = lems;
	}


	private StateType getOrMakeStateType(Component target) {
		StateType ret = null;
		if (stateTypeMap.containsKey(target)) {
			ret = stateTypeMap.get(target);
		} else {
			ret = makeStateType(target);
			stateTypeMap.put(target, ret);
		}
		return ret;
	}
	
	
	public StateType makeStateType(Component target) {
	 
		HashMap<String, Double> fixedHM = new HashMap<String, Double>();

		for (Constant c : lems.getConstants()) {
			fixedHM.put(c.getName(), c.getValue());
		}
		
		
		ComponentType type = target.getComponentType();
		
		ArrayList<ComponentType> typeChain = getTypeChain(type);
		
		StateType ret = new StateType(target.getId(), type.getName());
		
		
		 
		 
		for (ComponentType c : typeChain) {
			for (Parameter p : c.getParameters()) {
				String qn = p.getName();
				ParameterValue pv = target.getParameterValue(qn);
				double qv = pv.getDoubleValue();
				
				E.info("Setting fixed value in stat type for " + qn + " " + qv + " " + pv.getValue());
				ret.addFixed(qn, qv);
			}
			
			for (Requirement rv : c.getRequirements()) {
				ret.addIndependentVariable(rv.getName(), rv.getDimension());
			}
		 
			for (Exposure ev : c.getExposures()) {
				ret.addExposedVariable(ev.getName(), ev.getDimension());
			}
			
			
			for (Property p : c.getPropertys()) {
				String pnm = p.getName();
				ret.addExposureMapping(pnm, pnm);
			}

			for (Text text : c.getTexts()) {
				String tnm = text.getName();
				ParameterValue pv = target.getParameterValue(tnm);
				if (pv != null) {
					ret.addTextParam(tnm, pv.getValue());
				}
			}

		}
		
		Dynamics dynamics = type.getDynamics();
		if (dynamics != null) {
			applyDynamics(dynamics, ret, fixedHM);
		}
		

		/* TODO
		for (Structure b : dynamics.getStructures()) {
			ret.addBuilder(b.makeBuilder(cpt));
		}
	 */
		
	
		
		
		HashMap<String, Component> childHM = target.getChildMap();
		for (String s : childHM.keySet()) {
			Component ch = childHM.get(s);
			
			StateType st = getOrMakeStateType(ch);
		 	
			ret.addChildStateType(s, st);
			
			// if not directly defined?
			// ret.addRefStateType(s, chb);
			
		}

		HashMap<String, ArrayList<Component>> childrenHM = target.getChildrenMap();
		for (String s : childrenHM.keySet()) {
			ArrayList<Component> cpts = childrenHM.get(s);
			for (Component c : cpts) {
				StateType cst = getOrMakeStateType(c);
				ret.addListStateType(s, cst);
			}
		}
	 
		/* 
		
		for (Attachments ats : getAttachmentss()) {
 			ret.addAttachmentSet(ats.getName(), ats.getComponentType().getName());
		}

		for (Collection c : getCollections()) {
			ret.addInstanceSet(c.getName());
		}

		for (PairCollection c : getPairCollections()) {
			ret.addInstancePairSet(c.getName());
		}
		
		*/
		
		
		
		Simulation sim = type.getSimulation();
		if (sim != null) {
			applySimulation(sim, target, ret);
		}
	 
		return ret;
	
	}
	
	
	
	
	private ArrayList<ComponentType> getTypeChain(ComponentType ct) {
		ArrayList<ComponentType> ret = new ArrayList<ComponentType>();
		ComponentType wk = ct;
		while (wk != null) {
			ret.add(wk);
			wk = wk.getSupertype();
		}
		Collections.reverse(ret);
		return ret;
	}
	
	
	
	
	private void applySimulation(Simulation sim, Component target, StateType ret) {
		for (Run run : sim.getRuns()) {
			
			
			String incField = run.getIncrement();
			ParameterValue pv = target.getParameterValue(incField);
			E.info("inc param val " + pv + " " + pv.hashCode());
			double dt = pv.getDoubleValue();
			
			String totField = run.getTotal();
			ParameterValue pvt = target.getParameterValue(totField);
			double trun = pvt.getDoubleValue();
			 	

			String fieldName = run.getComponent();
			

			Component runTarget = target.getChild(fieldName);
			E.info("** processing a run, need '" + fieldName + "' within " + target + " got: " + runTarget);
			
			StateType targetST = stateTypeMap.get(runTarget);
			
			RunConfig rc = new RunConfig(targetST, dt, trun);
			ret.addRunConfig(rc);
			E.info("Added a run config to " + ret.hashCode() + " " + ret);
		}
			
	}

	
	

	public void applyDynamics(Dynamics dynamics, StateType ret, HashMap<String, Double> fxdHM) {
 	 	 
		// ret.setSimultaneous(dynamics.getSimultaneous());
        
        HashMap<String, Double> fixedHM = new HashMap<String, Double>();
        fixedHM.putAll(fxdHM);
        // fixedHM should just contain global constants, we don't want 
        // the parameter values in there as these can still be overridden.
        
        for (String s : fixedHM.keySet()) {
       	 E.info("FIXING Elt in fixedHM " + s + " " + fixedHM.get(s));
        }
        
         
		 
		 HashSet<StateVariable> varHS = new HashSet<StateVariable>();
		 for (StateVariable sv : dynamics.getStateVariables()) {
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
		 
		 
		 for (DerivedVariable dv : dynamics.getDerivedVariables()) {
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
			 Exposure e = dv.getExposureObject();
			 if (e != null) {
				 ret.addExposureMapping(dv.getName(), e.getName());
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
		 
		 
		 for (TimeDerivative sd : dynamics.getTimeDerivatives()) {
			 StateVariable sv = sd.getStateVariable();
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
			 ArrayList<Component> states = cpt.getChildrenAL(ks.getNodesName());
			 ArrayList<Component> rates = cpt.getChildrenAL(ks.getEdgesName());
			 
			 KScheme scheme = ks.makeKScheme(states, rates);
			 
			 ret.addKScheme(scheme);
		 }
	 
		 for (ReceivePort p : r_type.getReceivePorts()) {
			 ret.addInputPort(p.getName());
			 // TODO - also need output ports done the same way, in case send action is in a sub-regime
		 }
		  	
		 for (Regime reg : regimes) {
			  ComponentRegime crb = reg.makeComponentRegime(ret, cpt, copyFixed(fixedHM));
			  ret.addComponentRegime(crb);
		 }
		 */
		 
		 
		 // TODO - could do something with children of parent type here, but don't have to as they 
		 // come in again via the structure of the component itself.
		 
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
