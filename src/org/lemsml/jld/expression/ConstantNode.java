package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.eval.AbstractDVal;
import org.lemsml.jld.eval.DCon;
import org.lemsml.jld.exception.ExpressionError; 
 

public class ConstantNode extends Node implements DoubleParseTreeNode {

    String sval = null;

    double dval;

    public ConstantNode(String s) {
        super();

        sval = s;
        dval = Double.parseDouble(s);
    }

    @Override
    public String toString() {
        return "{Constant: " + sval + "}";
    }

    @Override
    public String toExpression() {

        return dval + "";
    }

    public double evalD(HashMap<String, Double> valHS) {
        return dval;
    }

    @Override
    public AbstractDVal makeEvaluable(HashMap<String, Double> fixedHM) {
        return new DCon(dval);
    }

    public double getDoubleValue() {
        return dval;
    }

    @Override
    public Dim getDimensionality(HashMap<String, Dim> dimHM) throws ExpressionError {
        Dim ed = new Dim();
        if (dval == 0) {
            ed.setZero();
        }
        ed.setDoubleValue(dval);
        return ed;
    }

    @Override
    public Dim evaluateDimensional(HashMap<String, Dim> dhm) throws ExpressionError {
        throw new ExpressionError("Can't use constants with  dimensions");
    }

    @Override
    public void substituteVariables(HashMap<String, String> varHM) throws ExpressionError {
        if (varHM.containsKey(sval)) {
            sval = varHM.get(sval);
        }

    }

    @Override
    public void doVisit(ExpressionVisitor ev) throws ExpressionError {
        ev.visitConstant(dval);

    }

}
