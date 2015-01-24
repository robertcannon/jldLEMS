package org.lemsml.jld.io.writer;

import org.lemsml.jld.io.xml.XMLElement;
import org.lemsml.jld.model.Component;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.ParameterValue;

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
 		for (Component cpt : lems.getComponents()) {
			XMLElement xel = writeComponent(cpt);
			tgt.addXMLElement(xel);
		}
	}

	private XMLElement writeComponent(Component cpt) {
		XMLElement ret = null;
		String el = cpt.getElement();
		if (el != null) {
			ret = new XMLElement(el);			
		} else {
			ret = new XMLElement("Component");
			
		}
		String t = cpt.getType();
		if (t != null) {
			if (el != null && t.equals(el)) {
				// no need for type attribute
			} else {
				ret.addAttribute("type", cpt.getType());				
			}
		}
		
		String id = cpt.getId();
		if (id != null) {
			ret.addAttribute("id", id);
		}
		
		if (cpt.getExtends() != null) {
			ret.addAttribute("extends", cpt.getExtends());
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
