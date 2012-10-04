package org.lemsml.jlems.type.dynamics;

import org.lemsml.jlems.util.ContentError;

public class Transition {

	public String regime;

	Regime r_regime;
	
	public void resolve(Dynamics bhv) throws ContentError {
		r_regime = bhv.getRegime(regime);
	}
	
	public String getRegime() {
		return regime;
	}
	
}