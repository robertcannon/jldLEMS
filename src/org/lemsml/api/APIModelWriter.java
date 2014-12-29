package org.lemsml.api;

import org.lemsml.model.Lems;
import org.lemsml.model.core.ModelException;

public interface APIModelWriter {

	
	public String serializeModelToXML(Lems lems) throws APIException, ModelException;
	
	
	
}
