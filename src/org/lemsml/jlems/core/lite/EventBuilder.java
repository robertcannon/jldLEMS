package org.lemsml.jlems.core.lite;

import java.util.ArrayList;

import org.lemsml.jlems.core.lite.run.Assignment;

public class EventBuilder {

	
	ArrayList<Assignment> assignments = new ArrayList<Assignment>();
	
	public void addAssignment(Assignment as) {
		assignments.add(as);
	}

	public ArrayList<Assignment> getAssignments() {
		 return assignments;
	}

}
