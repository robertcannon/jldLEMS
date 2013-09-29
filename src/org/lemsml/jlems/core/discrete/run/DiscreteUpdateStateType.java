package org.lemsml.jlems.core.discrete.run;

import java.util.ArrayList;
import java.util.HashMap;
 
import org.lemsml.jlems.core.dimensionless.FloatAssignment;
import org.lemsml.jlems.core.dimensionless.IndependentVariable;
import org.lemsml.jlems.core.dimensionless.InputVariable;
import org.lemsml.jlems.core.dimensionless.StateVariable;
import org.lemsml.jlems.core.dimensionless.VariableExposure;
import org.lemsml.jlems.core.discrete.DiscreteUpdateModel;
import org.lemsml.jlems.core.expression.ExpressionParser;
import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.expression.ParseTree;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.run.ConnectionError;
import org.lemsml.jlems.core.run.DoublePointer;
import org.lemsml.jlems.core.run.RuntimeError;
import org.lemsml.jlems.core.run.RuntimeType;
import org.lemsml.jlems.core.run.StateRunnable;
import org.lemsml.jlems.core.sim.ContentError;


public class DiscreteUpdateStateType implements RuntimeType {

	
	DiscreteUpdateModel duModel;
	
	boolean resolved = false;

	ArrayList<Fetch> fetches = new ArrayList<Fetch>();
	
	ArrayList<Assignment> assignments = new ArrayList<Assignment>();
	
	ArrayList<Copy> exposures = new ArrayList<Copy>();
	
	
	public DiscreteUpdateStateType(DiscreteUpdateModel dum) {
		duModel = dum;
	}
	
 
	public String toString() {
		String ret = "DU type, id=" + getID();
		return ret;
	}
	
	
	public String getID() {
		return duModel.getID();
	}

	
	
	public void resolve() throws ParseError, ContentError {
	 
		//ExpressionParser ep = new ExpressionParser();
		
		ExpressionParser expressionParser = new ExpressionParser();
	
		for (InputVariable iv : duModel.getInputVariables()) {
			Fetch f = new Fetch(iv.getName());
			fetches.add(f);
		}
		
		for (FloatAssignment fa : duModel.getFloatAssignments()) {			
			ParseTree pt = expressionParser.parseExpression(fa.getExpression());
			Assignment as = new Assignment(fa.getVariableName(), pt.makeFloatEvaluator());
			assignments.add(as);
			 
		}
		
		for (FloatAssignment ua : duModel.getUpdateAssignments()) {
			ParseTree pt = expressionParser.parseExpression(ua.getExpression());
			Assignment as = new Assignment(ua.getVariableName(), pt.makeFloatEvaluator());
			assignments.add(as);
		}
		
		for (VariableExposure ve : duModel.getVariableExposures()) {
			Copy c = new Copy(ve.getLocalName(), ve.getExposedName());
			exposures.add(c);
		}
		
		resolved = true;
	}
	
	

	@Override
	public StateRunnable newStateRunnable() throws ContentError, ConnectionError, RuntimeError {
		E.info("DU type making runnable... " + getID());
		
		if (!resolved) {
			try {
				resolve();
			} catch (ParseError pe) {
				pe.printStackTrace();
				throw new ContentError("Error parsing discrete update model");
			}
		}
		
		
		HashMap<String, DoublePointer> variables = new HashMap<String, DoublePointer>();
		for (InputVariable iv : duModel.getInputVariables()) {
			variables.put(iv.getName(), new DoublePointer(0.));
		}
		
		for (StateVariable sv : duModel.getStateVariables()) {
			variables.put(sv.getName(), new DoublePointer(0.));
		}
	
		for (FloatAssignment fa : duModel.getFloatAssignments()) {
			variables.put(fa.getVariableName(), new DoublePointer(0.));
		}
		
		for (FloatAssignment ua : duModel.getUpdateAssignments()) {
			variables.put(ua.getVariableName(), new DoublePointer(0.));
		}
		
		variables.put("dt", new DoublePointer(0.));
		
		DiscreteUpdateStateInstance dur = new DiscreteUpdateStateInstance(this, variables);
		
		return dur;
		
	}
	
	
	public void advance(double dt, HashMap<String, DoublePointer> variables, StateRunnable parent) throws RuntimeError {
		variables.get("dt").set(dt);
		
		for (Fetch f : fetches) {
			f.exec(variables, parent);
		}
		
		
		for (Assignment a : assignments) {
			a.exec(variables);
		}
		
		for (Copy c : exposures) {
			c.exec(variables);
		}
		
	}


	public boolean hasStateVariable(String str) {
		boolean ret = false;
		if (duModel.hasStateVariable(str)) {
			ret = true;
		}
		return ret;
	}
	
	
}
