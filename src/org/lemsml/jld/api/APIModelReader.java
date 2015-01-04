package org.lemsml.jld.api;

import java.io.File;

import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.core.ModelException;

public interface APIModelReader {

	
	public Lems readModelFromXMLFile(File xmlFile) throws APIException, ModelException;
	
	
	
}
