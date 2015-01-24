package org.lemsml.jld.model.core;

import java.util.HashMap;

import org.lemsml.jld.eval.DoubleEvaluator;
import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jld.expression.Dim;
import org.lemsml.jld.expression.ExpressionVisitor;

public abstract class AbstractAST {

	public abstract Dim getDimensionality(HashMap<String, Dim> dimHM) throws ExpressionError;

	public abstract void visitAll(ExpressionVisitor ev) throws ExpressionError;

	public abstract DoubleEvaluator makeFloatFixedEvaluator(HashMap<String, Double> fixedHM) throws ExpressionError;
	 
	 
 
}
