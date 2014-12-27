package org.lemsml.io.jld.xml;

import org.lemsml.api.APIException;
import org.lemsml.io.jld.E;
import org.lemsml.model.Component;
import org.lemsml.model.Lems;
import org.lemsml.model.ModelException;
  

public abstract class AbstractModelFactory {

	
	int nid = 0;
	
	
	public Lems buildLemsFromXMLElement(XMLElement root) throws APIException, ModelException {
	 	Lems ret = null;
		if (root.isTag("Lems")) {
			
			ret = new Lems();
			populateLemsFromXMLElement(ret, root);
			
		} else {
			throw new APIException("Cant read lems from " + root);
		}
		return ret;
	}
	
	public abstract void populateLemsFromXMLElement(Lems lems, XMLElement xel) throws APIException, ModelException;
	
	 
	  

	protected void readComponentFromXMLElement(Lems lems, XMLElement xel) throws ModelException {
		
		String typeName = "";
		if (xel.getTag().equals("Component")) {
			// should have a type field
		} else {
			typeName = xel.getTag();
		}
		
		String id = "";
		for (XMLAttribute xa : xel.getAttributes()) {
			String xn = xa.getName();
			if (xn.equals("id")) {
				id = xa.getValue();
			} else if (xn.equals("type")) {
				typeName = xa.getValue();
			}
		}
		Component ret = lems.addComponent(id);
		
		ret.setTypeName(typeName);
		
		for (XMLAttribute xa : xel.getAttributes()) {
			String xn = xa.getName();
			if (xn.equals("extends")) {
			//	ret.setExtends(xa.getValue());
			} else {
			//	ret.addAttribute(xa.makeCopy());
			}
		}
		
		for (XMLElement cel : xel.getXMLElements()) {
			String ct = cel.getTag();
			 
			if (ct.equals("About")) {
				//About ab = (About)instantiateFromXMLElement(cel);
				//ret.abouts.add(ab);
			} else if (ct.equals("Meta")) {
				//Meta m = new Meta();
				//m.setSource(cel);
				//ret.metas.add(m);
				
			} else if (ct.equals("Insertion")) {
				//Insertion ins = (Insertion)instantiateFromXMLElement(cel);
				//ret.insertions.add(ins);
			
			} else {
				// ret.addComponent(readComponentFromXMLElement(cel));
			}
		}
	 
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
			
			// GWT doesn't have the locale version - why did we need this?
			// String sl = s.toLowerCase(Locale.ENGLISH);
			// (it was just to avoid a PMD warning - should disable in PMD)
			
			String sl = s.toLowerCase();
			
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
	

	
	public String getNameAttribute(XMLElement xel) {
		String ret = null;
		for (XMLAttribute at : xel.getAttributes()) {
			if (at.getName().equals("name")) {
				ret = at.getValue();
				break;
			}
			// TODO check - fall back on id, but prefer name
			if (at.getName().equals("id")) {
				ret = at.getValue();
			}
		}
		if (ret == null) {
			nid += 1;
			ret = "_" + nid;
		}
		return ret;
	}
	
}
