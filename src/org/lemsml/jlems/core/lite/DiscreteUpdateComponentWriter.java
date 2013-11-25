package org.lemsml.jlems.core.lite;

import org.lemsml.jlems.core.lite.model.DiscreteUpdateComponent;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.xml.XMLElement;

public class DiscreteUpdateComponentWriter {
	
	DiscreteUpdateComponent dum;
	
	boolean includeRP = false;
	
	
	public DiscreteUpdateComponentWriter(DiscreteUpdateComponent m) {
		dum = m;
	}
	
	
	
	public XMLElement toXML() {
		E.missing();
		return null;
	}
	
}
