package org.lemsml.jld.hsim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.lemsml.jld.display.RuntimeOutput;
import org.lemsml.jld.eval.DoubleEvaluator;
import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jld.hrun.Builder;
import org.lemsml.jld.hrun.BuilderElement;
import org.lemsml.jld.hrun.MultiBuilder;
import org.lemsml.jld.hrun.RunConfig;
import org.lemsml.jld.hrun.RuntimeDisplay;
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
import org.lemsml.jld.imodel.simulation.IDataDisplay;
import org.lemsml.jld.imodel.simulation.IDataWriter;
import org.lemsml.jld.imodel.simulation.IRecord;
import org.lemsml.jld.imodel.simulation.IRun;
import org.lemsml.jld.imodel.simulation.ISimulation;
import org.lemsml.jld.imodel.structure.IStructure;
import org.lemsml.jld.io.E;
import org.lemsml.jld.model.core.AbstractAST;
import org.lemsml.jld.model.structure.EventConnection;
import org.lemsml.jld.model.structure.ForEach;
import org.lemsml.jld.model.structure.MultiInstance;
import org.lemsml.jld.model.structure.Structure;
 
 
 

public class StateTypeBuilder {

	
	private ILems lems;
	
	int idCounter = 0;
	
	private HashMap<IComponent, StateType> stateTypeMap = new HashMap<IComponent, StateType>();
	
	
	public StateTypeBuilder(ILems lems) {
		this.lems = lems;
	}
	
	private StateType getOrMakeStateType(IComponent target) {
		return getOrMakeStateType(target, 0);
	}
	
	
	private StateType getOrMakeStateType(IComponent target, int depth) {
		StateType ret = null;
		if (stateTypeMap.containsKey(target)) {
			ret = stateTypeMap.get(target);
		} else {
			ret = makeStateType(target, depth);
			stateTypeMap.put(target, ret);
		}
		return ret;
	}
	
	
	public StateType makeStateType(IComponent target, int depth) {
	 
		HashMap<String, Double> fixedHM = new HashMap<String, Double>();

		for (IConstant c : lems.getIConstants()) {
			fixedHM.put(c.getName(), c.getValue());
		}
		
		
		IComponentType type = target.getIComponentType();
		
		E.info(indent(depth) + "Making ST for " + target.getId() + "(" + type.getName() + ")");
		
		ArrayList<IComponentType> typeChain = getTypeChain(type);
		
		StateType ret = new StateType(target.getId(), type.getName());
		
		
		 
		 
		for (IComponentType c : typeChain) {

			for (String qn : c.getParameterNames()) {
				
//				IParameterValue pv = target.getParameterValue(qn);
				double qv = target.getNumericalParameterValue(qn);
 				ret.addFixed(qn, qv);
			}
			
			for (String rn : c.getRequirementNames()) {
				ret.addIndependentVariable(rn, c.getFieldDimension(rn));
			}
		 
			for (String en : c.getExposureNames()) {
				ret.addExposedVariable(en, c.getFieldDimension(en));
			}
			
			for (String pnm : c.getPropertyNames()) {
				ret.addExposureMapping(pnm, pnm);
			}
			
			for (String tnm : c.getTextNames()) {
			 
				String tv = target.getStringParameterValue(tnm);
				if (tv != null) {
					ret.addTextParam(tnm, tv);
				}
			}

		}
		
		IDynamics dynamics = type.getIDynamics();
		if (dynamics != null) {
			applyDynamics(dynamics, ret, fixedHM);
		}
	
		
		ISimulation sim = type.getISimulation();
		if (sim != null) {
			E.info(indent(depth) + "applying sim");
			applySimulation(sim, target, ret, depth);
		} else {
			E.info(indent(depth) + "no simulation elt");
		}
	 

		
		
		IStructure str = type.getIStructure();

		if (str != null) {
		
			ret.addBuilder(makeStructureBuilder(str, target));
		 
		
		} else {
			// default behavior is just to instantiate each child
		for (String s : target.getChildNames()) {
			
			E.info(indent(depth) + "STB processing child " + s);
			
			IComponent ch = target.getIChild(s);
			StateType st = getOrMakeStateType(ch, depth + 1);	
			ret.addChildStateType(s, st);
		}
 	 
			// if not directly defined?
			// ret.addRefStateType(s, chb);
		 
		for (String s : target.getChildrenNames()) {
			List<? extends IComponent> cpts = target.getIChildren(s);
			E.info(indent(depth) + "STB processing children: " + s + " (" + cpts.size() + ")");
			for (IComponent c : cpts) {
				StateType cst = getOrMakeStateType(c, depth + 1);
				ret.addListStateType(s, cst);
			}
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
		
	
		return ret;
	
	}
	


	private String indent(int n) {
		String spaces = "                                               ";
		int ns = 2 * n;
		if (ns > spaces.length()) {
			ns = spaces.length();
		}
		return spaces.substring(0, ns);
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
	
	
	
	
	private void applySimulation(ISimulation sim, IComponent target, StateType ret, int depth) {
		for (IRun run : sim.getIRuns()) {
			String incField = run.getIncrement();
			double dt = target.getNumericalParameterValue(incField);
	 		
			String totField = run.getTotal();
			double trun = target.getNumericalParameterValue(totField);
		 
			String fieldName = run.getComponent();
			

			IComponent runTarget = target.getIChild(fieldName);
			E.info(indent(depth) + "** processing a run, need '" + fieldName + "' within " + target + " got: " + runTarget);
			
			StateType targetST = getOrMakeStateType(runTarget);
			
			RunConfig rc = new RunConfig(targetST, dt, trun);
			ret.addRunConfig(rc);
			E.info(indent(depth) + "Added a run config to " + ret.hashCode() + " " + ret);
		
		}
			
		 
		E.info(indent(depth) + "Processing data displays " + sim.getIDataDisplays().size());
			
		for (IDataDisplay dd : sim.getIDataDisplays()) {
			final RuntimeDisplay ro = getRuntimeDisplay(dd, target);
			ret.addRuntimeDisplay(ro);
		}
			 
			
			
		for (IDataWriter dw : sim.getIDataWriters()) {
			final RuntimeOutput ro = getRuntimeOutput(dw, target);
			ret.addRuntimeOutput(ro);
		}
		 
			 for (IRecord r : sim.getIRecords()) {
				 final String path = target.getStringParameterValue(r.getQuantity());
				 if (path == null) {
					 E.error("No path specified for recorder (" + r.getQuantity() + ") in " + target);
				 }
				 IComponent cdisp = null;
				 if (r.getDestination() != null) {
					 cdisp = getInheritableLinkTarget(target, r.getDestination());
				 } else {
					 cdisp = target.getParent();
				 }
				 
				 if (cdisp == null) {
					 E.error("No display defined for recorder " + r);		
			
				 } else {
				 if (cdisp.getId() == null) {
					 cdisp.setId(autoID());
				 }
				 
				 double tsc = 1.;
				 double ysc = 1.;
				 String tscl = r.getTimeScale();
				 if (tscl != null) {
					 double ptsc = target.getNumericalParameterValue(tscl);
					 if (ptsc != 0.) {
						 tsc = ptsc;
					 }
				 }
				 String scl = r.getScale();
				if (scl != null) {
					double pscl = target.getNumericalParameterValue(scl);
					if (pscl != 0.) {
						ysc = pscl;
					}
				}
				String col = target.getStringParameterValue(r.getColor());
				 ret.addRecorder(target.getId(), path, tsc, ysc, col, cdisp.getId());
			 }
			 }
	}

	
	 
	
	
	private IComponent getInheritableLinkTarget(IComponent target, String destination) {
			IComponent ret = null;
		
			ret = target.getIChild(destination);
			
			if (ret == null) {
				List<? extends IComponent> ch = target.getIChildren(destination);
				if (ch != null && ch.size() > 0) {
					ret = ch.get(0);
					// TODO - ever relevant, what if more children?
				}
			}
			 
				
			if (ret == null) {
				IComponent p = target.getParent();
				if (p != null) {
					ret = getInheritableLinkTarget(p, destination);
				}
			}
			if (ret == null) {
				E.info("Inheritable - no ref " + destination + " in " + target);
			}  
			return ret;
	}

	
	
	
	private String autoID() {
		idCounter += 1;
		String ret = "_" + idCounter;
		return ret;
	}
	 
		 
	public RuntimeDisplay getRuntimeDisplay(IDataDisplay dd, IComponent cpt) {
		RuntimeDisplay ret = new RuntimeDisplay();
		ret.setID(cpt.getId());
		ret.setTitle(cpt.getStringParameterValue(dd.getTitle()));
		
		String dataRegion = dd.getDataRegion();
		if (dataRegion != null) {
			String[] bits = dataRegion.split(",");
			if (bits.length == 4) {
				double[] box = new double[4];
				for (int i = 0; i < 4; i++) {
					box[i] = cpt.getNumericalParameterValue(bits[i]); 
				}
				ret.setBox(box);
				
 			} else {
 				E.warning("Expect 4 items in region, " + dataRegion);
 			}
		}
		return ret;
		
	}
	
	
	public RuntimeOutput getRuntimeOutput(IDataWriter dw, IComponent cpt) {
		RuntimeOutput ret = new RuntimeOutput();
		ret.setID(cpt.getId());
		String p = cpt.getStringParameterValue(dw.getPath());
		if (p != null) {
			ret.setPath(p);
		}  
		ret.setFileName(cpt.getStringParameterValue(dw.getFileName()));
		return ret;
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
	

	
	private Builder makeStructureBuilder(IStructure astr, IComponent target) {
		Builder b = new Builder();
		
		Structure str = (Structure)astr;
		
		for (MultiInstance mi : str.getMultiInstances()) {
			BuilderElement bde = makeMultiInstanceBuilder(mi, target);
			b.add(bde);
		}
		
		for (ForEach fe : str.getForEachs()) {
			E.missing();
		}
		
		for (EventConnection ec : str.getEventConnections()) {
			E.missing();
		}
		return b;
	}

	private BuilderElement makeMultiInstanceBuilder(MultiInstance mi, IComponent target) {
		MultiBuilder mb = null;
		
		StateType cb = null;
		if (target != null) {
			IComponent c = target.getIChild(mi.getComponent());
			if (c != null) {
				cb = getOrMakeStateType(c);
			}
		}
		if (cb == null) {
			IComponent c = lems.getIComponent(mi.getComponent());
		}
		if (cb == null) {
			E.error("Can't locate component in multibuilder: " + mi.getComponent());
		
		} else {
		 
		int n = (int)Math.round(target.getNumericalParameterValue(mi.getNumber()));
		
		mb = new MultiBuilder(n, cb);
		
		// mb.setIndexVariable(mi.getIndexVariable());
		
		/* TODO
		for (Assign ass : assigns) {
			String cea = null;
			String ea = ass.getExposeAs();
			if (ea != null) {
				cea = cpt.getTextParam(ea);
			}
			// TODO - assigns don't know thier dimenison?
			E.missing("Dont' know dimension in assigns");
			String dim = "unknown";
			
			mb.addAssignment(ass.getProperty(), ass.getDoubleEvaluator(), cea, dim);
		}
		*/
	}
		return mb;	
	
	}
	
	
}
