package org.lemsml.api;

import java.io.File;

import org.lemsml.model.Lems;
import org.lemsml.model.ModelException;

public interface ModelReader {

	
	public Lems readModelFromXMLFile(File xmlFile) throws APIException, ModelException;
	
	
	
}
