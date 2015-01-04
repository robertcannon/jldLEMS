package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jlems.core.eval.AbstractBVal;
import org.lemsml.jlems.core.eval.Or;

public class OrNode extends AbstractBooleanOperatorNode {

    public static final String SYMBOL = ".or.";

    public OrNode() {
        super(SYMBOL);
    }

    public OrNode copy() {
        return new OrNode();
    }

    public int getPrecedence() {
        return 20;// TODO: check..
    }

    public boolean bool(boolean x, boolean y) {
        return x || y;
    }

    public AbstractBVal makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError {
        checkLeftRight();
        return new Or(leftEvaluable.makeEvaluable(fixedHM), rightEvaluable.makeEvaluable(fixedHM));
    }

    public void checkDimensions(HashMap<String, Dim> dimHM) throws ExpressionError {
        getDimensionality(dimHM);
    }

    @Override
    public void doLocalVisit(ExpressionVisitor ev) {
        ev.visitOrNode(this);
    }

}
