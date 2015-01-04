package org.lemsml.jld.resolver;

import java.util.HashMap;

import org.lemsml.jld.expression.ExpressionParser;
import org.lemsml.jld.expression.ParseError;
import org.lemsml.jld.expression.ParseTree;
import org.lemsml.jld.io.E;
import org.lemsml.jld.model.Component;
import org.lemsml.jld.model.Constant;
import org.lemsml.jld.model.Dimension;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.Unit;
import org.lemsml.jld.model.core.AbstractTypeElement;
import org.lemsml.jld.model.core.ParseException;
import org.lemsml.jld.model.dynamics.DerivedVariable;
import org.lemsml.jld.model.dynamics.Dynamics;
import org.lemsml.jld.model.type.Child;
import org.lemsml.jld.model.type.Children;
import org.lemsml.jld.model.type.ComponentType;

public class LemsResolver {

	private Lems lems;
 	
	public LemsResolver(Lems lems) {
		this.lems = lems;
	}
	
	
	
	public void resolveTypes() {
	 
		 
		resolveUnitDimensions();
		
		resolveConstantDimensions();
		
		resolveTypeDimensions();
		
		resolveTypeExtension();
	
		resolveTypeReferences();
		
		parseExpressions();
	}
	
	
	
	public void resolveComponents() {
		for (Component cpt : lems.getComponents()) {
			resolveSingleComponent(cpt);
		}
	}
	
	public void resolveSingleComponent(Component cpt) {
		ComponentResolver cr = new ComponentResolver(lems, cpt);
		cr.resolve();
	}
	
	
	
	
	private void resolveTypeExtension() {	
		// first check for any extensions
		for (ComponentType ct : lems.getComponentTypes()) {
			String ext = ct.getExtends();
			if (ext != null) {
				ComponentType sct = lems.getComponentType(ext);
				if (sct == null) {
					E.error("Cannot find supertype of " + ct + " (looking for " + ext + ")");
				} else {
					ct.setSupertype(sct);
				}
			}
		}
	}
	
	
	
	
	private void resolveTypeReferences() {	
		// first check for any extensions
		for (ComponentType ct : lems.getComponentTypes()) {
			resolveSingleTypeReferences(ct);
		}
	}
	
	
	 
	
	private void resolveSingleTypeReferences(ComponentType ct) {
		for (Child child : ct.getChilds()) {
			resolveChildReference(child);
		}
		for (Children children : ct.getChildrens()) {
			resolveChildReference(children);
		}	
	}


	private void resolveChildReference(AbstractTypeElement child) {
		String typ = child.getType();
		if (typ == null) {
			E.error("Child " + child + " does not specify its ComponentType.");
		} else {
			ComponentType ct = lems.getComponentType(typ);
			if (ct == null) {
				E.error("No such type (" + typ + ") needed by child " + child + " of " + child.getType());
			} else {
				child.setTargetType(ct);
			}
		}
	}

	 


	private void resolveTypeDimensions() {	
		for (ComponentType ct : lems.getComponentTypes()) {
			 resolveSingleTypeDimensions(ct);
		}
	}

	private void resolveSingleTypeDimensions(ComponentType ct) {
		TypeResolver tr = new TypeResolver(lems);
		tr.resolveType(ct);
	}
	
	 
	
	
	private void resolveUnitDimensions() {
		for (Unit u : lems.getUnits()) {
			resolveUnit(u);
		}
	}
	
	private void resolveUnit(Unit u) {
			String s = u.getDimension();
			Dimension d = lems.getDimension(s);
			if (d == null) {
				E.error("Unit " + u.getName() + " refers to unknown dimension: " + u.getDimension());
			} else {			 
				u.setDimension(d);
			}
	}
	
	private void resolveConstantDimensions() {
		for (Constant c : lems.getConstants()) {
			resolveConstant(c);
		}
	}
	
	private void resolveConstant(Constant c) {
		String s = c.getDimension();
		Dimension d = lems.getDimension(s);
		if (d == null) {
			E.error("Constant " + c.getName() + " refers to unknown dimension: " + c.getDimension());
		} else {			 
			c.setDimension(d);
		}
	}
	
	
	private void parseExpressions() {
		ExpressionParser ep = new ExpressionParser();
		for (ComponentType ct : lems.getComponentTypes()) {
			Dynamics d = ct.getDynamics();
			if (d != null) {
				parseDynamicsExpressions(d, ep);
			}
		}
	}



	private void parseDynamicsExpressions(Dynamics d, ExpressionParser ep) {
		for (DerivedVariable dv : d.getDerivedVariables()) {
			parseDerivedVariableExpressions(dv, ep);
		}
	}



	private void parseDerivedVariableExpressions(DerivedVariable dv,
			ExpressionParser ep) {
		 if (dv.getValue() != null) {
			 try {
				 ParseTree pt = ep.parseExpression(dv.getValue());

				 dv.setAST(pt);
				 
			 } catch (ParseError pe) {
				 E.error("Failed to parse " + dv.getValue());
				 lems.setError();
			 }
		 }
	}
	
}
