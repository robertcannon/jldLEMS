package org.lemsml.jld.expression;

import java.util.HashMap;

import org.lemsml.jld.eval.AbstractDVal;
import org.lemsml.jld.eval.Power;
import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jld.io.E;

public class PowerNode extends AbstractFloatResultNode {

    public static final String SYMBOL = "^";

    public PowerNode() {
        super(SYMBOL);
    }

    @Override
    public PowerNode copy() {
        return new PowerNode();
    }

    @Override
    public int getPrecedence() {
        return 1;
    }

    @Override
    public double op(double x, double y) {
        return Math.pow(x, y);
    }

    @Override
    public AbstractDVal makeEvaluable(HashMap<String, Double> fixedHM) throws ExpressionError {
        checkLeftRight();
        return new Power(leftEvaluable.makeEvaluable(fixedHM), rightEvaluable.makeEvaluable(fixedHM));
    }

    @Override
    public Dim dimop(Dim dl, Dim dr) throws ExpressionError {
        Dim ret = null;
        if (dl.isDimensionless()) {
            ret = new Dim();

        } else if (dr.isDimensionless()) {
            double dpow = dr.getDoubleValue();
            if (Double.isNaN(dpow)) {
                E.warning("Can't check dimensionality in power operation (power not known to be constant) " + dl + " " + dr);
                E.info("class of power node is " + dr.getClass() + " left=" + leftEvaluable + " right=" + rightEvaluable);
            } else {
                ret = dl.power(dpow);
            }

        } else {
            throw new ExpressionError("powers must be dimensionless");
        }
        return ret;
    }

    @Override
    public Dim evaluateDimensional(HashMap<String, Dim> dhm) throws ExpressionError {
        throw new ExpressionError("Can't (yet) apply power operations to dimensions");
    }

    @Override
    public void doLocalVisit(ExpressionVisitor ev) throws ExpressionError {
          ev.visitPowerNode(leftEvaluable, rightEvaluable);
    }

}
