package org.lemsml.jld.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jld.expression.Dim;
import org.lemsml.jld.expression.EvaluableVisitor;
import org.lemsml.jld.io.E;
import org.lemsml.jld.model.Constant;
import org.lemsml.jld.model.Dimension;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.core.AbstractAST;
import org.lemsml.jld.model.dynamics.DerivedVariable;
import org.lemsml.jld.model.dynamics.Dynamics;
import org.lemsml.jld.model.dynamics.StateVariable;
import org.lemsml.jld.model.type.ComponentType;
import org.lemsml.jld.model.type.Parameter;
import org.lemsml.jld.model.type.Requirement;

public class DimensionsCheck {

	private Lems lems;
	
	public DimensionsCheck(Lems lems) {
		this.lems = lems;
	}
	
	
	public void checkExpressions() {
		for (ComponentType ct : lems.getComponentTypes()) {
			checkComponentTypeExpressions(ct);
		}
	}


	private void checkComponentTypeExpressions(ComponentType ct) {
		HashMap<String, Dim> dimHM = new HashMap<String, Dim>();
		
		Dynamics d = ct.getDynamics();
		if (d != null) {
				
			dimHM.put("t", new Dim(lems.TIME_DIMENSION));
		 
			
	 		for (Constant c : lems.getConstants()) {
				dimHM.put(c.getSymbol(), new Dim(c.getDimensionObject()));
			}
		
	 		addTypeProperties(ct, dimHM);
			
			
			for (DerivedVariable dv : d.getDerivedVariables()) {
				Dimension dobj = dv.getDimensionObject();
				if (dobj != null) {
					dimHM.put(dv.getName(), new Dim(dobj));
				}
			}
			for (StateVariable sv : d.getStateVariables()) {
				Dimension dobj = sv.getDimensionObject();
				if (dobj != null) {
					dimHM.put(sv.getName(), new Dim(dobj));
				}
			}
			
			checkDynamicsExpressions(d, dimHM);
		}
	}

	
	
	

	private void addTypeProperties(ComponentType ct, HashMap<String, Dim> dimHM) {
		for (Parameter p : ct.getParameters()) {
			dimHM.put(p.getName(), new Dim(p.getDimensionObject()));
		}

		for (Requirement r : ct.getRequirements()) {
			dimHM.put(r.getName(), new Dim(r.getDimensionObject()));
		}
		ComponentType cts = ct.getSupertype();
		if (cts != null) {
			addTypeProperties(cts, dimHM);
		}
	}


	private void checkDynamicsExpressions(Dynamics d, HashMap<String, Dim> dimHM) {
		
		ArrayList<DerivedVariable> sortedDVs = sortVariables(d, dimHM);
		
		 for (DerivedVariable dv : sortedDVs) {
			 AbstractAST ast = dv.getAST();
			 if (ast != null) {
				 try {
					 Dim dim = ast.getDimensionality(dimHM);
					 Dimension dvd = dv.getDimensionObject();
					 if (dvd == null) {
						 // E.info("No dimension set on " + dv + " (can't check consistency)");
						 dimHM.put(dv.getName(), dim);
						 
					 } else {
						 Dim tgt = new Dim(dvd);
						 if (dim.matches(tgt)) {
							 E.info("Dimension match OK for " + dv.getValue());
						 } else {
							 E.error("Dimension mismatch for " + dv.getValue() + " LHS: " + tgt + ", RHS: " + dim);
						 }
					 }
				 } catch (ExpressionError e) {
					 E.error("Invalid expression: " + dv.getValue() + " " + e);
					 showDimensions(dimHM);
				 }
			 }
			
		 }
	}

	
	
	

	private ArrayList<DerivedVariable> sortVariables(Dynamics d,
			HashMap<String, Dim> dimHM) {
		HashSet<String> vars = new HashSet<String>();
		vars.addAll(dimHM.keySet());
	 
		
		ArrayList<DerivedVariable> ret = new ArrayList<DerivedVariable>();
		HashSet<DerivedVariable> wkset = new HashSet<DerivedVariable>();
		for (DerivedVariable dv : d.getDerivedVariables()) {
			if (dv.getAST() != null) {
				wkset.add(dv);
			}
		}
		boolean added = true;
		while (added && wkset.size() > 0) {
			added = false;
			HashSet<DerivedVariable> togo = new HashSet<DerivedVariable>();
			for (DerivedVariable dv : wkset) {
				EvaluableVisitor ev = new EvaluableVisitor(vars);
				if (ev.canEvaluateFrom(dv.getAST())) {
					added = true;
					vars.add(dv.getName());
					togo.add(dv);
					ret.add(dv);
				}
			}
			wkset.removeAll(togo);
			
		}
		if (wkset.size() > 0) {
			E.error("Couldn't determine an order for evaluating variables in " + d + " remaining: " + wkset);
			E.info("Added vars " + dimHM.keySet());
		}
 
		return ret;
	}

	
	

	private void showDimensions(HashMap<String, Dim> dimHM) {
		StringBuilder sb = new StringBuilder();
		for (String key : dimHM.keySet()) {
			sb.append("   " + key + ": " + dimHM.get(key) + "\n");
		}
		E.info("Known dimensions: \n" + sb.toString());
	}
	
}
