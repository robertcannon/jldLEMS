package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.eval.AbstractBComp;
import org.lemsml.jld.eval.EQComp;
import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jld.hrun.RuntimeError;

public class EqualsNode extends AbstractComparisonNode {

    public static final String SYMBOL = ".eq.";

    public EqualsNode() {
        super(SYMBOL);
    }

    @Override
    public EqualsNode copy() {
        return new EqualsNode();
    }

    @Override
    public int getPrecedence() {
        return 20;
    }

    @Override
    public boolean compare(double x, double y) throws RuntimeError {
        throw new RuntimeError("Called equals comparison of doubles");
    }

    @Override
    public AbstractBComp makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError {
        checkLeftRight();
        return new EQComp(leftEvaluable.makeEvaluable(fixedHM), rightEvaluable.makeEvaluable(fixedHM));
    }

    @Override
    public boolean compareInts(long ix, long iy) {
        return (ix == iy);
    }

    @Override
    public void doLocalVisit(ExpressionVisitor ev) throws ExpressionError {
         ev.visitEqualsNode(leftEvaluable, rightEvaluable);
    }

}
