package org.lemsml.jld.model.core;

import java.util.HashMap;
import java.util.HashSet;

import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jld.expression.Dim;
import org.lemsml.jld.expression.ExpressionVisitor;

public abstract class AbstractAST {

	public abstract Dim getDimensionality(HashMap<String, Dim> dimHM) throws ExpressionError;

	public abstract void visitAll(ExpressionVisitor ev) throws ExpressionError;
	 
 
}
