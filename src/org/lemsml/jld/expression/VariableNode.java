package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.eval.AbstractDVal;
import org.lemsml.jld.eval.DCon;
import org.lemsml.jld.eval.DVar;
import org.lemsml.jld.exception.ExpressionError; 
 

public class VariableNode extends Node implements DoubleParseTreeNode {

	String svar;
 	
	
	public VariableNode(String s) {
		super();
		svar = s;
	}
	
     
	public String toString() {
		return "{Variable: " + svar + "}";
	}
	
	public String toExpression() {
		return svar;
	}

	
	public void substituteVariables(HashMap<String, String> varHM) {
		if (varHM.containsKey(svar)) {
			svar = varHM.get(svar);
		}
	}
    
   
	 
	public double evalD(HashMap<String, Double> valHS) throws ParseError {
		double ret = 0;
		if (valHS.containsKey(svar)) {
			ret = valHS.get(svar);
		} else {
			throw new ParseError("can't eval " + svar);
		}
		return ret;
	}

 
   
	
	
	public AbstractDVal makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError {
		AbstractDVal ret = null;
	 
 			if (fixedHM != null && fixedHM.containsKey(svar)) {
				ret = new DCon(fixedHM.get(svar));
	 			} else {
	 				ret = new DVar(svar);
 			}
			
		 
		return ret;
	}

	
	private String renderMap(HashMap<String, Dim> dimHM) {
		String ret = "\n";
		for (String s : dimHM.keySet()) {
			ret += s + ": " + dimHM.get(s) + "\n";
		}
		return ret;
	}
	
	
	public Dim getDimensionality(HashMap<String, Dim> dimHM) throws ExpressionError {
		Dim ret = null;
		if (dimHM.containsKey(svar)) {
			ret = dimHM.get(svar);
		} else {
			throw new ExpressionError("No such variable '" + svar + 
					"' when getting dimensionality for " + this + renderMap(dimHM));
		}
		return ret;
	}
	

	public Dim evaluateDimensional(HashMap<String, Dim> dimHM) throws ExpressionError {
		Dim ret = null;
		if (dimHM.containsKey(svar)) {
			ret = dimHM.get(svar);
		} else {
			throw new ExpressionError("No such variable in map: " + svar);
		}
		return ret;
	}
	
 

	@Override
	public void doVisit(ExpressionVisitor ev) {
		ev.visitVariable(svar);
	}
}
