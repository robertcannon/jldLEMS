package org.lemsml.jlems.core.lite.run.network;

import java.util.ArrayList;

import org.lemsml.jlems.core.lite.run.component.Assignment;

public class EventBuilder {

	
	ArrayList<Assignment> assignments = new ArrayList<Assignment>();
	
	public void addAssignment(Assignment as) {
		assignments.add(as);
	}

	public ArrayList<Assignment> getAssignments() {
		 return assignments;
	}

}
