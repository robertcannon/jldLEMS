package org.lemsml.jld.api;

import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.core.ModelException;

public interface APIModelWriter {

	
	public String serializeModelToXML(Lems lems) throws APIException, ModelException;
	
	
	
}
