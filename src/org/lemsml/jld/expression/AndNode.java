package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.eval.AbstractBVal;
import org.lemsml.jld.eval.And;
import org.lemsml.jld.exception.ExpressionError;
 

public class AndNode extends AbstractBooleanOperatorNode {

    public static final String SYMBOL = ".and.";

    public AndNode() {
        super(SYMBOL);
    }

    @Override
    public AndNode copy() {
        return new AndNode();
    }

    @Override
    public int getPrecedence() {
        return 20;// TODO: check..
    }

    @Override
    public boolean bool(boolean x, boolean y) {
        return x && y;
    }

    @Override
    public AbstractBVal makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError {
        checkLeftRight();
        return new And(leftEvaluable.makeEvaluable(fixedHM), rightEvaluable.makeEvaluable(fixedHM));
    }

    @Override
    public void checkDimensions(HashMap<String, Dim> dimHM) throws ExpressionError {
        getDimensionality(dimHM);

    }

    @Override
    public void doLocalVisit(ExpressionVisitor ev) throws ExpressionError {
       
        ev.visitAndNode(leftEvaluable, rightEvaluable);

    }
}
