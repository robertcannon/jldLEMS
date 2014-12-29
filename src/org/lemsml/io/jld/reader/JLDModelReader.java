package org.lemsml.io.jld.reader;

import java.io.File;

import org.lemsml.api.APIException;
import org.lemsml.api.APIModelReader;
import org.lemsml.io.jld.xml.XMLElement;
import org.lemsml.io.jld.xml.XMLElementReader;
import org.lemsml.model.Lems;
import org.lemsml.model.core.ModelException;

public class JLDModelReader implements APIModelReader {

	@Override
	public Lems readModelFromXMLFile(File xmlFile) throws APIException, ModelException {
	 
		FileInclusionReader fir = new FileInclusionReader(xmlFile);
		String xmlText = fir.read();
		
		XMLElementReader xer = new XMLElementReader(xmlText);
		XMLElement root = xer.getRootElement();
		
		GeneratedModelReader mf = new GeneratedModelReader();
		Lems ret = mf.buildLemsFromXMLElement(root);
		
		return ret;
	}

}
