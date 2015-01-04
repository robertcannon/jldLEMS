package org.lemsml.jld.report;

import org.lemsml.jld.io.E;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.type.ComponentType;

public class Reporter {

	private Lems lems;
	
	
	public Reporter(Lems lems) {
		this.lems = lems;
	}
	
	
	public void reportTypes() {
		StringBuilder sb = new StringBuilder();
		sb.append("All component types:\n");
		for (ComponentType ct : lems.getComponentTypes()) {
			sb.append("    " + ct.getName() + "\n");
		}
		E.info(sb.toString());
	}
	
	
}
