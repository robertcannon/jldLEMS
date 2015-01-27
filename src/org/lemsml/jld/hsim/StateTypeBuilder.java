package org.lemsml.jld.hsim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
 
import org.lemsml.jld.imodel.IComponent;
import org.lemsml.jld.imodel.IComponentType;
import org.lemsml.jld.imodel.IConstant;
import org.lemsml.jld.imodel.ILems;
import org.lemsml.jld.imodel.dynamics.IDynamics;
import org.lemsml.jld.imodel.simulation.ISimulation;
import org.lemsml.jld.imodel.structure.IStructure;

import org.lemsml.jld.hrun.Builder;
import org.lemsml.jld.hrun.StateType;
 

public class StateTypeBuilder {

	
	private ILems lems;
	
	// for generating distinct ids for components that don't have them
	int idCounter = 0;
	
	
	// keeping track of StateTypes as they are built.
	// note that there are dependencies between state types so that building one may trigger another
	// to be built before the first build completes.
	// TODO this is only in the structure and simulation builders - maybe make all StateTypes first
	// with their dynamics, put them in the map, then do the structure builders so we don't get 
	// this deep recursion?
	private HashMap<IComponent, StateType> stateTypeMap = new HashMap<IComponent, StateType>();
	
	
	public StateTypeBuilder(ILems lems) {
		this.lems = lems;
	}
	
	protected StateType getOrMakeStateType(IComponent target) {
		return getOrMakeStateType(target, 0);
	}
	
	
	// Here depth is just for indenting the log messages so we know which element is being built
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
		
	//	E.info(indent(depth) + "Making ST for " + target.getId() + "(" + type.getName() + ")");
		
		// typeChain is all the supertypes ("extends" in the xml)
		ArrayList<IComponentType> typeChain = getTypeChain(type);
		
		StateType ret = new StateType(target.getId(), type.getName());
		 
		
		
		for (IComponentType c : typeChain) {
			for (String qn : c.getParameterNames()) {
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

			 for (String s : c.getReceivePortNames()) {
				 ret.addInputPort(s);
			 }
			 
			 for (String s : c.getSendPortNames()) {
 				 ret.addOutputPort(s);
			 }
			 
			 
		}
		
		IDynamics dynamics = type.getIDynamics();
		if (dynamics != null) {
			DynamicsBuilder db = new DynamicsBuilder();
			db.applyDynamics(dynamics, ret, fixedHM);
			 
		}
	
		
		ISimulation sim = type.getISimulation();
		if (sim != null) {
			SimulationBuilder sb = new SimulationBuilder(this);
			sb.applySimulation(sim, target, ret, depth);
		}  

		
		
		IStructure str = type.getIStructure();
		if (str != null) {
			StructureBuilder sb = new StructureBuilder(this);
			Builder b = sb.makeStructureBuilder(str, target);
			ret.addBuilder(b);
		 
		} else {
			// default behavior is just to instantiate each child
			for (String s : target.getChildNames()) {		
				IComponent ch = target.getIChild(s);
				StateType st = getOrMakeStateType(ch, depth + 1);	
				ret.addChildStateType(s, st);
			}
 	 
			// if not directly defined?
			// ret.addRefStateType(s, chb);
		 
			for (String s : target.getChildrenNames()) {
				List<? extends IComponent> cpts = target.getIChildren(s);
				// E.info(indent(depth) + "STB processing children: " + s + " (" + cpts.size() + ")");
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
	
	
	
	
	
	
	
	
	protected String autoID() {
		idCounter += 1;
		String ret = "_" + idCounter;
		return ret;
	}
	 


	
	private HashMap<String, Double> copyFixed(HashMap<String, Double> fixedHM) {
		 HashMap<String, Double> ret = new HashMap<String, Double>();
		 for (String s : fixedHM.keySet()) {
			 ret.put(s, fixedHM.get(s));
		 }
		 return ret;
	}

	public IComponent getIComponent(String id) {
		return lems.getIComponent(id);
	}
	

	
	
}
