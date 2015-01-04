package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jlems.core.eval.AbstractDVal;
import org.lemsml.jlems.core.eval.Divide;

public class DivideNode extends AbstractFloatResultNode {

    public static final String SYMBOL = "/";

    public DivideNode() {
        super(SYMBOL);
    }

    @Override
    public DivideNode copy() {
        return new DivideNode();
    }

    @Override
    public int getPrecedence() {
        return 2;
    }

    @Override
    public double op(double x, double y) {
        return (Double.isNaN(x) ? 1 : x) / y;
    }

    @Override
    public AbstractDVal makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError {
        checkLeftRight();
        return new Divide(leftEvaluable.makeEvaluable(fixedHM), rightEvaluable.makeEvaluable(fixedHM));
    }

    @Override
    public Dim dimop(Dim dl, Dim dr) {
        return dl.getDivideBy(dr);
    }

    @Override
    public void doLocalVisit(ExpressionVisitor ev) throws ExpressionError {
         ev.visitDivideNode(leftEvaluable, rightEvaluable);
    }

}
