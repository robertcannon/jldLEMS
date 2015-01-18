package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.eval.AbstractBComp;
import org.lemsml.jld.eval.GEQComp;
import org.lemsml.jld.exception.ExpressionError; 

public class GreaterThanOrEqualsNode extends AbstractComparisonNode {

    public static final String SYMBOL = ".geq.";

    public GreaterThanOrEqualsNode() {
        super(SYMBOL);
    }

    @Override
    public GreaterThanOrEqualsNode copy() {
        return new GreaterThanOrEqualsNode();
    }

    @Override
    public int getPrecedence() {
        return 10;
    }

    @Override
    public boolean compare(double x, double y) {
        return (x >= y);
    }

    @Override
    public AbstractBComp makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError {
        checkLeftRight();
        return new GEQComp(leftEvaluable.makeEvaluable(fixedHM), rightEvaluable.makeEvaluable(fixedHM));
    }

    @Override
    public boolean compareInts(long ix, long iy) {
        return (ix >= iy);
    }

    @Override
    public void doLocalVisit(ExpressionVisitor ev) throws ExpressionError {
          ev.visitGreaterThanOrEqualsNode(leftEvaluable, rightEvaluable);
    }

}
