package org.lemsml.jlems.core.numerics;

import java.util.ArrayList;

import org.lemsml.jlems.core.type.LemsCollection;

public class IntegrationStep {

	public LemsCollection<GradientStateIncrement> gradientStateIncrements = 
			new LemsCollection<GradientStateIncrement>();

	public ArrayList<GradientStateIncrement> getGradientStateIncrements() {
		return gradientStateIncrements.getContents();
	}
}
