package org.lemsml.jlems.core.numerics;

import org.lemsml.jlems.core.type.LemsCollection;

public class WorkState {
	
	public String name;
	
	public double timeFactor;
	
	public LemsCollection<GradientStateIncrement> gradientStateIncrements = 
			new LemsCollection<GradientStateIncrement>();
}
