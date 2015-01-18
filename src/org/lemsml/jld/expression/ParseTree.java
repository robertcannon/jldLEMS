package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.eval.BBase;
import org.lemsml.jld.eval.BooleanEvaluator;
import org.lemsml.jld.eval.DBase;
import org.lemsml.jld.eval.DoubleEvaluator;
import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jld.io.E;
import org.lemsml.jld.model.core.AbstractAST;
 

public class ParseTree extends AbstractAST {
 
	ParseTreeNode root;
	
	public ParseTree(ParseTreeNode r) { 
		root = r;
	}
	
	public String toString() {
		return root.toString();
	}
	

	public boolean isFloat() {
		boolean ret = false;
		if (root instanceof DoubleParseTreeNode) {
			ret = true;
		}
		return ret;
	}
	
	
	public boolean isBoolean() {
		boolean ret = false;
		if (root instanceof BooleanParseTreeNode) {
			ret = true;
		}
		return ret;
	}

	public BooleanEvaluator makeBooleanEvaluator() throws ExpressionError {
		BBase ret = null;
		if (root instanceof BooleanParseTreeNode) {
			ret= new BBase(((BooleanParseTreeNode) root).makeEvaluable(null));
		}
		return ret;
	}

 
	
	public BooleanEvaluator makeBooleanFixedEvaluator(HashMap<String, Double> fixedHM) throws ExpressionError {
		BBase ret = null;
		if (root instanceof BooleanParseTreeNode) {
			ret = new BBase(((BooleanParseTreeNode) root).makeEvaluable(fixedHM));
		} else {
			throw new ExpressionError("Seeking a boolean evaluator, but not a boolean node? " + root);
		}
		return ret;
	}
	
	

	public DoubleEvaluator makeFloatEvaluator() throws ExpressionError {
		DBase ret = null;
		if (root instanceof DoubleParseTreeNode) {
			ret = new DBase(((DoubleParseTreeNode) root).makeEvaluable(null));
		}
		return ret;
	}
	
	
	public DoubleEvaluator makeFloatFixedEvaluator(HashMap<String, Double> fixedHM) throws ExpressionError {
		
		if (fixedHM.containsKey("leakConductance")) {
			E.trace();
		}
		
		DBase ret = null;
		if (root instanceof DoubleParseTreeNode) {
			ret = new DBase(((DoubleParseTreeNode) root).makeEvaluable(fixedHM));
		}
		return ret;
	}
	
 
	
	
	public Dim evaluateDimensional(HashMap<String, Dim> adml) throws ExpressionError {
		return root.evaluateDimensional(adml);
	}

	public Dim getDimensionality(HashMap<String, Dim> dimHM) throws ExpressionError {
		 return root.getDimensionality(dimHM);
	}

	public void checkDimensions(HashMap<String, Dim> dimHM) throws ExpressionError {
 		if (root instanceof BooleanParseTreeNode) {
			((BooleanParseTreeNode)root).checkDimensions(dimHM);
		} else {
			E.error("Can't check dims of expression");
		}
	}

	public void substituteVariables(HashMap<String, String> varHM) throws ExpressionError {
		root.substituteVariables(varHM);
	}

	public String toExpression() throws ExpressionError {
		return root.toExpression();
	}

	public void visitAll(ExpressionVisitor ev) throws ExpressionError {
		root.doVisit(ev);
	}


}
