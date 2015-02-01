package org.lemsml.jld.flattener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.lemsml.jld.api.APIException;
import org.lemsml.jld.exception.LEMSException;
import org.lemsml.jld.expression.ExpressionParser;
import org.lemsml.jld.expression.ParseTree;
import org.lemsml.jld.io.E;
import org.lemsml.jld.model.Component;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.ParameterValue;
import org.lemsml.jld.model.dynamics.DerivedVariable;
import org.lemsml.jld.model.dynamics.Dynamics;
import org.lemsml.jld.model.dynamics.OnCondition;
import org.lemsml.jld.model.dynamics.OnEvent;
import org.lemsml.jld.model.dynamics.OnStart;
import org.lemsml.jld.model.dynamics.StateAssignment;
import org.lemsml.jld.model.dynamics.StateVariable;
import org.lemsml.jld.model.dynamics.TimeDerivative;
import org.lemsml.jld.model.type.Child;
import org.lemsml.jld.model.type.ComponentType;
import org.lemsml.jld.model.type.Exposure;
import org.lemsml.jld.model.type.Parameter;
import org.lemsml.jld.model.type.ReceivePort;
import org.lemsml.jld.model.type.Requirement;
import org.lemsml.jld.model.type.SendPort;
import org.lemsml.jld.model.type.Text;


public class ComponentFlattener {
 	
	Component flatComponent = null;
	ComponentType flatType = null;

	
	ExpressionParser expressionParser = null;
	

	public ComponentFlattener() {
 		expressionParser = new ExpressionParser();
	}
	
	

	public void insertFlattened(Component srcComponent, Lems flatLems) {
		ComponentType srcCt = srcComponent.getComponentType();
	 
	
		String flatTypeName = srcCt.getName() + "_flat";
	
		flatType = flatLems.addComponentType(flatTypeName);
	 
		String flatId = srcComponent.getId(); //  + "_flat";
		
		flatComponent = flatLems.addComponent(flatId);
		flatComponent.setComponentType(flatType);
		 
		applyFlattened(srcComponent, "", true);
	}
	
 
	
	
	
	
	private void applyFlattened(Component cpt, String prefix, boolean withExposures) {

		HashMap<String, String> varHM = new HashMap<String, String>();

		ComponentType typ = cpt.getComponentType();

	 	ArrayList<ComponentType> act = getTypeChain(typ);
	 	
	 	for (ComponentType ct : act) {
	 		applyType(ct, prefix, varHM, withExposures);
	 	}
		 
	 	mapParameterValues(cpt, prefix);
	 	
		Dynamics dyn = typ.getDynamics();
	
		if (dyn != null) {
			applyDynamics(typ, cpt, dyn, prefix, varHM, withExposures);
		}
	
		importChildren(cpt, prefix);
	
	}
	

	
	public void removeStateRequirements() {
		// during flattening, a requirement may be present (from a subcomponent)
		// that is a state variable in the same component itself. These need
		// removing
		Dynamics d = flatType.getDynamics();
		
		HashSet<String> rnms = new HashSet<String>();
		rnms.addAll(flatType.getRequirementNames());
		
		if (d != null) {
			for (StateVariable sv : d.getStateVariables()) {
				if (rnms.contains(sv)) {
					flatType.removeRequirement(sv.getName());
				}
			}
		}
	}
	
	
	
	private void mapParameterValues(Component cpt, String prefix) {
 		
		for (ParameterValue pv : cpt.getParameterValues()) {
			String n = pv.getName();
			String fname = flatName(n, prefix);
			String val = pv.getValue();
			flatComponent.setParameterValue(fname, val);
		}
		
	}

	
	
	private void importChildren(Component cpt, String prefix) {
		for (Component child : cpt.getComponents()) {
			String cid = child.getId();
			if (cid == null) {
				cid = child.getElement();
			}
			if (cid == null) {
				cid = child.getType();
			}
		 	
			if (cid == null) {
				E.error("No identifier for child: " + child);
			
			} else { 
				String childPrefix = flatName(cid, prefix);
				// false here as we don't want the exposures from the children
				applyFlattened(child, childPrefix, false);
			}
		}
	}
	
	
	
	
	
		
	private void applyDynamics(ComponentType typ, Component cpt, Dynamics dyn, 
			String prefix, HashMap<String, String> varHM, boolean withExposures) {	
		
		Dynamics flatDynamics = null;
		try {
			flatDynamics = flatType.createDynamics();
		} catch (APIException ex) {
			E.error("api exception during flattening: " + ex);
			return;
		}
		for (StateVariable sv : dyn.getStateVariables()) {
			String fname = flatName(sv.getName(), prefix, varHM);
			StateVariable fsv = flatDynamics.addStateVariable(fname);
			fsv.setDimension(sv.getDimension());

			if (sv.getExposure() != null) {
				String enm = flatName(sv.getExposure(), prefix);
				Exposure expo = flatType.addExposure(fname);
				expo.setDimension(sv.getDimension());
				
			}
		}

		for (OnEvent oe : dyn.getOnEvents()) {
			makeOnEventCopy(flatDynamics, oe);
		}

		
		for (OnCondition oc : dyn.getOnConditions()) {
			makeOnConditionCopy(flatDynamics, oc);
		}

		
		
		
		for (DerivedVariable dv : dyn.getDerivedVariables()) {

			String fname = flatName(dv.getName(), prefix, varHM);
		 
			String val = dv.getValue();
			String sel = dv.getSelect();

			DerivedVariable fdv = null;
			
			if (val != null) {
				val = substituteVariables(val, varHM);
				fdv = flatDynamics.addDerivedVariable(fname);
				fdv.setDimension(dv.getDimension());
				fdv.setValue(val);
				
			} else if (sel != null) {
				String red = dv.getReduce();
				String selval = sel;
				if (red != null) {
					String op = " ? ";
					String dflt = "";
					boolean ok = false;
					if (red.equals("add")) {
						op = " + ";
						dflt = "0";
						ok = true;
					} else if (red.equals("multiply")) {
						op = " * ";
						dflt = "1";
						ok = true;
					} else {
						E.error("Unrecognized selection opteration " + red);
					}
				
					if (ok) {
					int iwc = sel.indexOf("[*]");
					String rt = sel.substring(0, iwc);
					String var = sel.substring(iwc + 4, sel.length());
					
					ArrayList<String> items = new ArrayList<String>();
					items.add(dflt);
					for (Component c : cpt.getChildren(rt)) {
						items.add(flatName(c.getId() + "_" + var, prefix));
					}
					selval = join(items, op);
					}
				}
				
		 
				for (Child child : typ.getChilds()) {
					String sp = child.getName();
					String fp = flatName(sp, prefix);
					selval = selval.replace(sp + "/", fp + "_");
				}

				/*
				for (ComponentReference compRef : typ.getComponentReferences()) {
					String sp = compRef.getName();
					String refid = cpt.getRefComponents().get(compRef.getName()).getID();
					String fp = flatName(refid, prefix);
					selval = selval.replace(sp + "/", fp + "_");
				}
			 */
				
				fdv = flatDynamics.addDerivedVariable(fname);
				fdv.setDimension(dv.getDimension());
				fdv.setSelect(selval);
			}
			 

			if (withExposures && fdv != null) {
				String expo = dv.getExposure();
				if (expo != null) {
				String enm = flatName(expo, prefix);
				dv.setExposure(enm);
				}
			}
		}

		for (TimeDerivative td : dyn.getTimeDerivatives()) {

			String val = substituteVariables(td.getValue(), varHM);
			
			String varnm = flatName(td.getVariable(), prefix);
			TimeDerivative ftd = flatDynamics.addTimeDerivative(varnm);
			ftd.setValue(val);
		}
		

		for (OnStart os : dyn.getOnStarts()) {
			for (StateAssignment sa : os.getStateAssignments()) {

				String vnm = flatName(sa.getVariable(), prefix);
				String val = substituteVariables(sa.getValue(), varHM);
				
				
				try {
					OnStart fos = flatDynamics.addOnStart("");
					StateAssignment fsa = fos.addStateAssignment(vnm);
					fsa.setValue(val);
				} catch (APIException ex) {
					E.error("API exception during flattening: " + ex);
				}
			}
		}	
	}
	
	
	
	
	
	private String join(ArrayList<String> items, String op) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String s : items) {
			if (first) {
				first = false;
			} else {
				sb.append(op);
				sb.append(s);
			}
		}
		return sb.toString();
	}



	private String makeOnConditionCopy(Dynamics fd, OnCondition oc) {
		OnCondition foc = fd.addOnCondition("");
		E.missing();
		return null;
	}



	private String makeOnEventCopy(Dynamics fd, OnEvent oe) {
		OnEvent foe = fd.addOnEvent("");
		E.missing();
		return null;
	}



	private void applyType(ComponentType typ, String prefix,
			HashMap<String, String> varMap, boolean withExposures) {

		for (Text t : typ.getTexts()) {
			String newText = flatName(t.getName(), prefix);
			flatType.addText(newText);
		}

		for (Parameter p : typ.getParameters()) {
			String fname = flatName(p.getName(), prefix, varMap);
			flatType.addParameter(fname, p.getDimension());
		}

		for (Exposure ex : typ.getExposures()) {
			String fname = flatName(ex.getName(), prefix);
			if (withExposures) {
				Exposure expo = flatType.addExposure(fname, ex.getDimension());
			} else {
				E.info("Leaving out exposure " + fname + " from flattened version");
			}
		}

		for (Requirement req : typ.getRequirements()) {
			String nm = req.getName();
			if (flatType.getRequirement(nm) == null) {
				Requirement r = flatType.addRequirement(nm);
				r.setDimension(req.getDimension());
			}
		}

		for (ReceivePort ep : typ.getReceivePorts()) {
			ReceivePort rp = flatType.addReceivePort(ep.getName());
		}

		for (SendPort ep : typ.getSendPorts()) {
			SendPort sp = flatType.addSendPort(ep.getName());
		}

	}
	
	
	


	private ArrayList<ComponentType> getTypeChain(ComponentType ct) {
		ArrayList<ComponentType> ret = new ArrayList<ComponentType>();
		ComponentType wk = ct;
		while (wk != null) {
			ret.add(wk);
			wk = wk.getSupertype();
		}
		// Collections.reverse(ret);
		return ret;
	}
	
	
	
	
	
	
	

	private String flatName(String nm, String pfx, HashMap<String, String> varmap) {
		String ret = flatName(nm, pfx);
		if (nm.equals(ret)) {
			// no need to add it
		} else {
			varmap.put(nm, ret);
		}
		return ret;
	}

	private String flatName(String nm, String pfx) {
		String ret = nm;
		if (pfx.length() > 0) {
			ret = pfx + "_" + nm;
		}
 		return ret;
	}
	
	
	private String substituteVariables(String expr, HashMap<String, String> varHM) {
		String ret = null;
		try {
			ParseTree ptree = expressionParser.parse(expr);
			ptree.substituteVariables(varHM);	
			ret = ptree.toExpression();
			
		} catch (LEMSException le) {
			E.error("Can't parse expression: " + expr);
		}
		return ret;
	}

		 
	  

}
