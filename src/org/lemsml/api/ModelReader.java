package org.lemsml.api;

import java.io.File;

import org.lemsml.model.Lems;

public interface ModelReader {

	
	public Lems readModelFromXMLFile(File xmlFile) throws ContentError;
	
	
	
}
