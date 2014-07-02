package org.lemsml.jlems.core.type.dynamics;

import java.util.HashMap;

import org.lemsml.jlems.core.expression.Dimensional;
import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.expression.ParseTree;
import org.lemsml.jlems.core.expression.ExpressionParser;
import org.lemsml.jlems.core.expression.Valued;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.Dimension;
import org.lemsml.jlems.core.type.Lems;
import org.lemsml.jlems.core.type.LemsCollection;

public class Case extends ExpressionValued implements Valued {

    public String name;
    public String condition;
    ParseTree valueParseTree;
    ParseTree conditionParseTree;
 
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getValue() {
        return Double.NaN;
    }

    public void resolve(Lems lems, LemsCollection<Dimension> dimensions, ExpressionParser expressionParser) throws ParseError {
        if (value != null) {
            valueParseTree = expressionParser.parseExpression(value);
        }
        if (condition != null) {
            conditionParseTree = expressionParser.parseCondition(condition);
        } else {
            // TODO: Check if it's moderately more efficient to have a TrueNode implementing BooleanParseTreeNode
            // instead of this
            conditionParseTree = expressionParser.parseCondition("1 .eq. 1");
        }

    }

    public Dimensional getDimensionality(HashMap<String, Dimensional> dimHM) throws ContentError {
        return valueParseTree.getDimensionality(dimHM);
    }

    public Case makeCopy() {
        Case ret = new Case();
        ret.name = name;
        ret.value = value;
        ret.condition = condition;
        return ret;
    }
}
