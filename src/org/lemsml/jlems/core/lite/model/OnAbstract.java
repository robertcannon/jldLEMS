package org.lemsml.jlems.core.lite.model;

import org.lemsml.jlems.core.type.LemsCollection;

public class OnAbstract {

	public LemsCollection<Var> vars = new LemsCollection<Var>();
	public LemsCollection<Update> updates = new LemsCollection<Update>();
	public LemsCollection<Emit> emits = new LemsCollection<Emit>();

	public void addSend(String s) {
		emits.add(new Emit(s));
	}

	public void addEmit(String p) {        
		Emit e = new Emit(p);
		emits.add(e);
	}

	public LemsCollection<Var> getVars() {
		 return vars;
	}

	public LemsCollection<Update> getUpdates() {
		return updates;
	}

	public LemsCollection<Emit> getEmits() {
		return emits;
	}

	public void addVar(Var fa) {
		vars.add(fa);
	}

	public void addUpdate(Update fa) {
		updates.add(fa);
	}
	
	
}
