package org.lemsml.jld.io.reader;

import org.lemsml.jld.api.APIException;
import org.lemsml.jld.io.E;
import org.lemsml.jld.io.xml.XMLAttribute;
import org.lemsml.jld.io.xml.XMLElement;
import org.lemsml.jld.model.Component;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.core.ModelException;
  

public abstract class AbstractModelReader {

	
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
 		
		String id = getComponentId(xel);
		Component ret = lems.addComponent(id);
 		
		ret.setType(getComponentTypeName(xel));
	
		 
		populateComponentFromXMLElement(ret, xel);
	}
	
	
	private String getComponentTypeName(XMLElement xel) {
		String ret = null;
		if (xel.hasAttribute("type")) {
			ret = xel.getAttribute("type");
		} else {
			if (xel.getTag().equals("Component")) {
				 ret = "";
			} else {
				ret = xel.getTag();
			}
		}
		return ret;
 	}
	
	
	private String getComponentId(XMLElement xel) {
		String ret = null;
		if (xel.getTag().equals("Component")) {
			// no use as an id
		} else {
			ret = xel.getTag(); // could be the name by which the parent knows this child component
		} 
		if (xel.hasAttribute("id")) {
			ret = xel.getAttribute("id");
		}
		if (ret == null) {
			ret = nextId();
		}
		return ret;
	}
	
	
	
	private String nextId() {
		nid += 1;
		String ret = "_" + nid;
		return ret;
	}
	
	public void populateComponentFromXMLElement(Component tgt, XMLElement xel) {

		tgt.setElement(xel.getTag());
		
		for (XMLAttribute xa : xel.getAttributes()) {
			String xn = xa.getName();
			if (xn.equals("id")) {
				// already done
				
			} else if (xn.equals("type")) {
				tgt.setType(xa.getValue());
			
			} else if (xn.equals("extends")) {
				tgt.setExtends(xa.getValue());
			
			} else {
				tgt.setParameterValue(xn, xa.getValue());
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
				String id = getComponentId(cel);
				Component wk = tgt.addComponent(id);
				populateComponentFromXMLElement(wk, cel);  
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
			String n = at.getName();
			if (n.equals("name")) {
				ret = at.getValue();
				break;
			}
			// TODO check - fall back on id, but prefer name
			if (n.equals("id")) {
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
