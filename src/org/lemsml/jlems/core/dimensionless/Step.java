package org.lemsml.jlems.core.dimensionless;

import org.lemsml.jlems.core.type.LemsCollection;

public class Step {

	
	public LemsCollection<Update> updates = new LemsCollection<Update>();
	
	public LemsCollection<Var> vars = new LemsCollection<Var>();
	
	public LemsCollection<Output> outputs = new LemsCollection<Output>();
	
	public LemsCollection<ConditionCheck> conditionChecks = new LemsCollection<ConditionCheck>();
	
	
	public void addUpdateAssignment(FloatAssignment fa) {
		// TODO Auto-generated method stub
		
	}

	public LemsCollection<Update> getUpdates() {
		return updates;
	}

	public LemsCollection<Var> getVars() {
		return vars;
	}

	public void addOutput(String var, String expr) {
		Output o = new Output(var);
		o.setExpression(expr);
		
	}

	public LemsCollection<Output> getOutputs() {
		return outputs;
	}

	public void addVar(Var fa) {
		vars.add(fa);
	}

}
