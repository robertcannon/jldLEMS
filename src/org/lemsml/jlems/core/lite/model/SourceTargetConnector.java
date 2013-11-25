package org.lemsml.jlems.core.lite.model;

import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.LemsCollection;

public class SourceTargetConnector {

	public LemsCollection<FromArrayConnector> fromArrayConnectors = new LemsCollection<FromArrayConnector>();

	public FromArrayConnector getConnector() throws ContentError {
		if (fromArrayConnectors.size() == 0) {
			throw new ContentError("No connector in " + this);
		}
		return fromArrayConnectors.get(0);
	}
	
}
