package org.lemsml.jlems.core.numerics;

import java.util.ArrayList;

import org.lemsml.jlems.core.type.LemsCollection;

public class IntegrationScheme {

	public String name;
	
	public LemsCollection<WorkState> workStates = new LemsCollection<WorkState>();

	public LemsCollection<IntegrationStep> integrationSteps = new LemsCollection<IntegrationStep>();

	
	
	public ArrayList<WorkState> getWorkStates() {
		 return workStates.getContents();
	}
	
	
	public ArrayList<IntegrationStep> getIntegrationSteps() {
		return integrationSteps.getContents();
	}
	
}
