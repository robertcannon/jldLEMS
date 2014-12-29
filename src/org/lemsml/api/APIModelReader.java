package org.lemsml.api;

import java.io.File;

import org.lemsml.model.Lems;
import org.lemsml.model.core.ModelException;

public interface APIModelReader {

	
	public Lems readModelFromXMLFile(File xmlFile) throws APIException, ModelException;
	
	
	
}
