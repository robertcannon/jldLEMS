package org.lemsml.api;

import org.lemsml.model.Lems;
import org.lemsml.model.ModelException;

public interface ModelWriter {

	
	public String serializeModelToXML(Lems lems) throws APIException, ModelException;
	
	
	
}
