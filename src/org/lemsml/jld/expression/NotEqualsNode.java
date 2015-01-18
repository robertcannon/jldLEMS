package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.eval.AbstractBComp;
import org.lemsml.jld.eval.NEQComp;
import org.lemsml.jld.exception.ExpressionError; 
import org.lemsml.jld.hrun.RuntimeError;

public class NotEqualsNode extends AbstractComparisonNode {

    public static final String SYMBOL = ".neq.";

    public NotEqualsNode() {
        super(SYMBOL);
    }

    @Override
    public NotEqualsNode copy() {
        return new NotEqualsNode();
    }

    @Override
    public int getPrecedence() {
        return 10;
    }

    @Override
    public boolean compare(double x, double y) throws RuntimeError {
        throw new RuntimeError("Called not equals comparison of doubles");
    }

    @Override
    public AbstractBComp makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError {
        checkLeftRight();
        return new NEQComp(leftEvaluable.makeEvaluable(fixedHM), rightEvaluable.makeEvaluable(fixedHM));
    }

    @Override
    public boolean compareInts(long ix, long iy) {
        return (ix != iy);
    }

    @Override
    public void doLocalVisit(ExpressionVisitor ev) throws ExpressionError {
         ev.visitNotEqualsNode(leftEvaluable, rightEvaluable);
    }

}
