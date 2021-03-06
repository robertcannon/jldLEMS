package org.lemsml.jld.resolve;

import org.lemsml.jld.expression.ExpressionParser;
import org.lemsml.jld.expression.ParseError;
import org.lemsml.jld.expression.ParseTree;
import org.lemsml.jld.io.E;
import org.lemsml.jld.model.Component;
import org.lemsml.jld.model.Constant;
import org.lemsml.jld.model.Dimension;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.Unit;
import org.lemsml.jld.model.core.TargetTypeElement;
import org.lemsml.jld.model.dynamics.AbstractDynamicsBlock;
import org.lemsml.jld.model.dynamics.DerivedVariable;
import org.lemsml.jld.model.dynamics.Dynamics;
import org.lemsml.jld.model.dynamics.OnCondition;
import org.lemsml.jld.model.dynamics.OnEvent;
import org.lemsml.jld.model.dynamics.OnStart;
import org.lemsml.jld.model.dynamics.StateAssignment;
import org.lemsml.jld.model.dynamics.TimeDerivative;
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
		
		resolveTypeExtension();

		resolveTypeDimensions();
	
		resolveTypeReferences();
		
		parseExpressions();
	}
	
	
	
	public void resolveComponents() {
 		for (Component cpt : lems.getComponents()) {
			resolveSingleComponent(cpt);
		}
	}
	
	public void resolveSingleComponent(Component cpt) {
		ComponentResolver cr = new ComponentResolver(lems);
		cr.resolveComponent(cpt);
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


	private void resolveChildReference(TargetTypeElement child) {
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
		for (TimeDerivative td : d.getTimeDerivatives()) {
			parseTimeDerivativeExpression(td, ep);
		}
		
		for (OnCondition oc : d.getOnConditions()) {
			E.info("------------Parsing an OnCondition, test = " + oc.getTest());
			 try {
				 ParseTree pt = ep.parseCondition(oc.getTest());
				 oc.setAST(pt);
			 } catch (Exception ex) {
				 E.error("Failed to parse " + oc.getTest());
			 }
			 parseDynamicsBlockExpressions(oc, ep);
		}
		
		for (OnStart os : d.getOnStarts()) {
			parseDynamicsBlockExpressions(os, ep);
		}
		
		for (OnEvent oe : d.getOnEvents()) {
			parseDynamicsBlockExpressions(oe, ep);
		}
	}

	
	private void parseDynamicsBlockExpressions(AbstractDynamicsBlock adb, ExpressionParser ep) {
		for (StateAssignment sa : adb.getStateAssignments()) {
			try {
				ParseTree pt = ep.parseExpression(sa.getValue());
				sa.setAST(pt);
			} catch (Exception ex) {
				E.error("Cant parse " + sa.getValue() + " " + ex);
			}
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
	
	private void parseTimeDerivativeExpression(TimeDerivative td, ExpressionParser ep) {
		 if (td.getValue() != null) {
			 try {
				 ParseTree pt = ep.parseExpression(td.getValue());

				 td.setAST(pt);
				 
			 } catch (ParseError pe) {
				 E.error("Failed to parse " + td.getValue());
				 lems.setError();
			 }
		 }
	}
	
}
