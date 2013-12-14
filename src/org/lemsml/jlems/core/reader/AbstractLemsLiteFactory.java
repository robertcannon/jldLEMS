package org.lemsml.jlems.core.reader;

import java.util.Locale;

import org.lemsml.jlems.core.lite.model.ComponentArray;
import org.lemsml.jlems.core.lite.model.DataSources;
import org.lemsml.jlems.core.lite.model.DiscreteUpdateComponent;
import org.lemsml.jlems.core.lite.model.EventConnections;
import org.lemsml.jlems.core.lite.model.LemsLite;
import org.lemsml.jlems.core.lite.model.Simulation;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.About;
import org.lemsml.jlems.core.xml.XMLAttribute;
import org.lemsml.jlems.core.xml.XMLElement;

public abstract class AbstractLemsLiteFactory {

	
	public LemsLite buildLemsFromXMLElement(XMLElement root) throws ContentError {
	 	LemsLite ret=  null;
		if (root.isTag("LEMSLite")) {
			ret = readLemsLite(root);
		} else {
			throw new ContentError("Cant read lems lite from " + root);
		}
		return ret;
	}
	
	public abstract Object instantiateFromXMLElement(XMLElement root) throws ContentError;
	
	
	private LemsLite readLemsLite(XMLElement root) throws ContentError {
		LemsLite ret = new LemsLite();
		for (XMLElement xel : root.getElements()) {
			
			if (xel.isTag("DiscreteUpdateComponent")) {
				ret.discreteUpdateComponents.add((DiscreteUpdateComponent)instantiateFromXMLElement(xel));

			} else if (xel.isTag("DataSources")) {
				ret.dataSourcess.add((DataSources)instantiateFromXMLElement(xel));

			} else if (xel.isTag("ComponentArray")) {
				ret.componentArrays.add((ComponentArray)instantiateFromXMLElement(xel));

			} else if (xel.isTag("EventConnections")) {
				ret.eventConnectionss.add((EventConnections)instantiateFromXMLElement(xel));
		
			} else if (xel.isTag("Simulation")) {
				ret.simulations.add((Simulation)instantiateFromXMLElement(xel));
				
			} else {
				throw new ContentError("Unrecognized tag " + xel);
 				 
			}
		}
		return ret;
	}
	 
	
	  
	public DiscreteUpdateComponent readDiscreteUpdateComponent(XMLElement xel) throws ContentError {
		Object obj = instantiateFromXMLElement(xel);
		DiscreteUpdateComponent ret = (DiscreteUpdateComponent)obj;
		return ret;
	}
	 

	
	protected String internalFieldName(String s) {
		String ret = s;
		if (ret.equals("extends")) {
			// "extends" occurs in the definitions, but it is not a valid field name
			ret = "eXtends";
		}
		return ret;
	}
	
	
	
	protected String parseString(String s) {
		return s;
	}
	
	 
	protected double parseDouble(String s) {
		double ret = 0;
		if (s.length() > 0) {
		try {
			ret= Double.parseDouble(s);
		} catch (Exception ex) {
			E.error("Can't parse double from " + s);
		}
		}
		return ret;
	}
	
	protected int parseInt(String s) {
		int ret = 0;
		if (s.length() > 0) {
		try {
			ret= Integer.parseInt(s);
		} catch (Exception ex) {
			E.error("Can't parse int from " + s);
		}
		}
		return ret;
	}
	
	protected boolean parseBoolean(String s) {
		boolean ret = false;
		if (s.length() > 0) {
			String sl = s.toLowerCase(Locale.ENGLISH);
			if (sl.equals("0") || sl.equals("false")) {
				ret = false;
			} else if (sl.equals("1") || s.equals("true")) {
				ret = true;
				
			} else {
				E.error("Can't parse boolean from " + s);
			}
		}
		return ret;
	}
	
	
	
}
