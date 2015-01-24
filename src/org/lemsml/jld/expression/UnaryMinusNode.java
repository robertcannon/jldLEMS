package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.eval.AbstractDVal;
import org.lemsml.jld.eval.Minus;
import org.lemsml.jld.exception.ExpressionError;
 
public class UnaryMinusNode extends AbstractFloatResultNode {

    public UnaryMinusNode() {
        super("-");
        left = new ConstantNode("0");
    }

    @Override
    public UnaryMinusNode copy() {
        return new UnaryMinusNode();
    }

    @Override
    public int getPrecedence() {
        return 0;
    }

    @Override
    public double op(double x, double y) {
        return -y;
    }

    @Override
    public void claim() throws ParseError {
        claimRight();
    }

    @Override
    public AbstractDVal makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError {
        checkLeftRight();
        return new Minus(leftEvaluable.makeEvaluable(fixedHM), rightEvaluable.makeEvaluable(fixedHM));
    }

    @Override
    public Dim dimop(Dim dl, Dim dr) throws ExpressionError {
        Dim ret = null;
        if (dl == null) {
            ret = dr;
        } else if (dl.matches(dr)) {
            ret = dl;
        } else if (dl.isAny()) {
            ret = dr;
        } else if (dr.isAny()) {
            ret = dl;
        } else {
            throw new ExpressionError("Dimension mismatch - can't subtract " + dr + " (rhs) from " + dl + " (lhs)");
        }
        return ret;
    }

    @Override
    public Dim evaluateDimensional(HashMap<String, Dim> dhm) throws ExpressionError {
        throw new ExpressionError("Can't apply function operations to dimensions");
    }

    @Override
    public void doLocalVisit(ExpressionVisitor ev) throws ExpressionError {
         ev.visitUnaryMinusNode(rightEvaluable);
    }

}
