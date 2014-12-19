package org.lemsml.io.jldreader;

import java.io.File;

import org.lemsml.api.ContentError;
import org.lemsml.api.ModelReader;
import org.lemsml.io.jldreader.xml.XMLElement;
import org.lemsml.io.jldreader.xml.XMLElementReader;
import org.lemsml.model.Lems;

public class JLDModelReader implements ModelReader {

	@Override
	public Lems readModelFromXMLFile(File xmlFile) throws ContentError {
	 
		FileInclusionReader fir = new FileInclusionReader(xmlFile);
		String xmlText = fir.read();
		
		XMLElementReader xer = new XMLElementReader(xmlText);
		XMLElement root = xer.getRootElement();
		
		ModelFactory mf = new ModelFactory();
		Lems ret = mf.buildLemsFromXMLElement(root);
		
		return ret;
	}

}
