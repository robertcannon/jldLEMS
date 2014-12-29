package org.lemsml.io.jld.writer;

import org.lemsml.io.jld.E;
import org.lemsml.io.jld.xml.XMLElement;
import org.lemsml.model.Component;
import org.lemsml.model.Lems;
import org.lemsml.model.ParameterValue;

public class AbstractModelWriter {

	
	public void addIntAttribute(XMLElement xel, String name, int val) {
		xel.addAttribute(name, "" + val);
	}
	
	public void addBooleanAttribute(XMLElement xel, String name, boolean val) {
		xel.addAttribute(name, "" + val);
	}
	
	public void addDoubleAttribute(XMLElement xel, String name, double val) {
		// TODO - precision
		xel.addAttribute(name, "" + val);
	}
	
	
	public void writeComponentsToXML(Lems lems, XMLElement tgt) {
		E.info("Writing top leve lcpts...");
		for (Component cpt : lems.getComponents()) {
			XMLElement xel = writeComponent(cpt);
			tgt.addXMLElement(xel);
		}
	}

	private XMLElement writeComponent(Component cpt) {
		XMLElement ret = new XMLElement(cpt.getType());
		
		E.info("MAde xel for cpt " + cpt.getType());
		String id = cpt.getId();
		if (id != null) {
			ret.addAttribute("id", id);
		}
		
		for (ParameterValue pv : cpt.getParameterValues()) {
			ret.addAttribute(pv.getName(), pv.getValue());
		}
		for (Component child : cpt.getComponents()) {
			XMLElement xelch = writeComponent(child);
			ret.addXMLElement(xelch);
		}
		return ret;
	}
	
}
