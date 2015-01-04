package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jlems.core.eval.AbstractBComp;
import org.lemsml.jlems.core.eval.LTComp;

public class LessThanNode extends AbstractComparisonNode {

    public static final String SYMBOL = ".lt.";

    public LessThanNode() {
        super(SYMBOL);
    }

    @Override
    public LessThanNode copy() {
        return new LessThanNode();
    }

    @Override
    public int getPrecedence() {
        return 10;
    }

    @Override
    public boolean compare(double x, double y) {
        return (x < y);
    }

    @Override
    public AbstractBComp makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError {
        checkLeftRight();
        return new LTComp(leftEvaluable.makeEvaluable(fixedHM), rightEvaluable.makeEvaluable(fixedHM));
    }

    @Override
    public boolean compareInts(long ix, long iy) {
        return (ix < iy);
    }

    @Override
    public void doLocalVisit(ExpressionVisitor ev) throws ExpressionError {
           ev.visitLessThanNode(leftEvaluable, rightEvaluable);
    }

}
