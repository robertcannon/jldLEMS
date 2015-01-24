package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.eval.AbstractBComp;
import org.lemsml.jld.eval.LEQComp;
import org.lemsml.jld.exception.ExpressionError;

public class LessThanOrEqualsNode extends AbstractComparisonNode {

    public static final String SYMBOL = ".lte.";

    public LessThanOrEqualsNode() {
        super(SYMBOL);
    }

    @Override
    public LessThanOrEqualsNode copy() {
        return new LessThanOrEqualsNode();
    }

    @Override
    public int getPrecedence() {
        return 10;
    }

    @Override
    public boolean compare(double x, double y) {
        return (x <= y);
    }

    @Override
    public AbstractBComp makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError {
        checkLeftRight();
        return new LEQComp(leftEvaluable.makeEvaluable(fixedHM), rightEvaluable.makeEvaluable(fixedHM));
    }

    @Override
    public boolean compareInts(long ix, long iy) {
        return (ix <= iy);
    }

    @Override
    public void doLocalVisit(ExpressionVisitor ev) throws ExpressionError {
         ev.visitLessThanOrEqualsNode(leftEvaluable, rightEvaluable);
    }

}
