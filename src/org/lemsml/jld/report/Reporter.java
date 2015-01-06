package org.lemsml.jld.report;

import org.lemsml.jld.io.E;
import org.lemsml.jld.model.Component;
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


	public void reportComponents() {
		StringBuilder sb = new StringBuilder();
		String indent = "    ";
		for (Component c : lems.getComponents()) {
			appendComponent(sb, c, indent);
		}
		E.info("All Components\n" + sb.toString());
	}


	private void appendComponent(StringBuilder sb, Component c, String indent) {
		sb.append(indent + c.getElement() + ", type=" + c.getType() + ", id=" + c.getId() + "\n");
		for (Component sub : c.getComponents()) {
			appendComponent(sb, sub, indent + "    ");
		}
	}
	
	
}
