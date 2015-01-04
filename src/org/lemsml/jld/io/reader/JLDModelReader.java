package org.lemsml.jld.io.reader;

import java.io.File;

import org.lemsml.jld.api.APIException;
import org.lemsml.jld.api.APIModelReader;
import org.lemsml.jld.io.xml.XMLElement;
import org.lemsml.jld.io.xml.XMLElementReader;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.core.ModelException;

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
