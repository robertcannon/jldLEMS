package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.eval.AbstractDVal;
import org.lemsml.jld.eval.Times;
import org.lemsml.jld.exception.ExpressionError;


public class TimesNode extends AbstractFloatResultNode {

    public static final String SYMBOL = "*";

    public TimesNode() {
        super(SYMBOL);
    }

    @Override
    public TimesNode copy() {
        return new TimesNode();
    }

    @Override
    public int getPrecedence() {
        return 3;
    }

    @Override
    public double op(double x, double y) {
        return x * y;
    }

    @Override
    public AbstractDVal makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError {
        checkLeftRight();
        return new Times(leftEvaluable.makeEvaluable(fixedHM), rightEvaluable.makeEvaluable(fixedHM));
    }

    @Override
    public Dim dimop(Dim dl, Dim dr) {
        Dim ret = dl.getTimes(dr);
        return ret;
    }

    @Override
    public void doLocalVisit(ExpressionVisitor ev) throws ExpressionError {
         ev.visitTimesNode(leftEvaluable, rightEvaluable);
    }

}
