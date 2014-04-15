package org.lemsml.jlems.core.lite.model;

import org.lemsml.jlems.core.type.LemsCollection;

public class Step {

	
	
	public LemsCollection<Var> vars = new LemsCollection<Var>();

	public LemsCollection<Update> updates = new LemsCollection<Update>();
	
	public LemsCollection<ConditionCheck> conditionChecks = new LemsCollection<ConditionCheck>();

	public LemsCollection<Output> outputs = new LemsCollection<Output>();
	
	
	 
	public Step() {
		ConditionCheck cc = new ConditionCheck();
		conditionChecks.add(cc);
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
	
	public void addUpdate(Update fa) {
		updates.add(fa);
	}

	
	public void addOutput(Output o) {
		outputs.add(o);
	}
}
