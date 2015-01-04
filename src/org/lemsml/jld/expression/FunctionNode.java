package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jlems.core.eval.AbstractDVal;
import org.lemsml.jlems.core.eval.DFunc;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.type.Lems;


public class FunctionNode extends AbstractUnaryNode implements DoubleParseTreeNode {

	String fname = null;

	GroupNode args;

	DoubleParseTreeNode argEvaluable;

	public FunctionNode(String sf) {
		super();
		fname = sf;
	}



	@Override
	public String toString() {
		return "Function: " + fname + "(" + right + ")";
	}
	
	
	@Override
	public String toExpression() throws ExpressionError {
		checkArg();
		return fname + "(" + argEvaluable.toExpression() + ")";
	}
	
	
 

	@Override
	public void replaceChild(Node nold, Node nnew) throws ParseError {
		if (right == nold) {
			right = nnew;
		} else {
			throw new ParseError("can't replace - not present " + nold);
		}
	}

 
 

	public double call(double arg) {
		double ret = Double.NaN;
		// "cos", "tan", "exp", "sum", "product", "ln"};
		if (fname.equals("sin")) {
			ret = Math.sin(arg);
			
		} else if (fname.equals("cos")) {
			ret = Math.cos(arg);
		
		} else if (fname.equals("tan")) {
			ret = Math.tan(arg);
		
		} else if (fname.equals("ln") || fname.equals("log")) {
			ret = Math.log(arg);
		
		} else if (fname.equals("exp")) {
			ret = Math.exp(arg);
			
		} else if (fname.equals("abs")) {
			ret = Math.abs(arg);

		} else if (fname.equals("sqrt")) {
			ret = Math.sqrt(arg);

		} else if (fname.equals("random")) {
			ret = arg * Lems.getRandomGenerator().nextDouble();

		} else {
			E.error("unrecognized function: " + fname);
		}
		return ret;
	}
 
	private void checkArg() throws ExpressionError {
		if (argEvaluable == null) {
			if (right instanceof DoubleParseTreeNode) {
				argEvaluable = (DoubleParseTreeNode) right;		
			} else {
				throw new ExpressionError("Wrong node type in function " + right);
			}
		}
	}
	
	
	public AbstractDVal makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError {
		checkArg();
		return new DFunc(fname, argEvaluable.makeEvaluable(fixedHM));
	}

	public Dim getDimensionality(HashMap<String, Dim> dimHM) throws ExpressionError {
		checkArg();
		Dim ret = null;
		Dim diml = argEvaluable.getDimensionality(dimHM);
		if (diml.isDimensionless()) {
			ret = new Dim();
		} else {
			throw new ExpressionError("Function argument for " + fname + " is not dimensionless: " + diml);
		}

		return ret;
	}

	 
	public Dim evaluateDimensional(HashMap<String, Dim> dhm) throws ExpressionError {
		throw new ExpressionError("Can't apply function operations to dimensions");
	}
	
	 
	public void substituteVariables(HashMap<String, String> varHM) throws ExpressionError {
		 checkArg();
		 argEvaluable.substituteVariables(varHM);
	}
	 


	@Override
	public void doVisit(ExpressionVisitor ev) throws ExpressionError {
		checkArg();
		argEvaluable.doVisit(ev);
		ev.visitFunctionNode(fname, argEvaluable);	
	}
	
}
