package org.lemsml.jlems.tests;
  
import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.Result;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.sim.ParseException;
import org.lemsml.jlems.core.type.BuildException;
import org.lemsml.jlems.core.xml.TextElementReader;
import org.lemsml.jlems.core.xml.XMLElement;
import org.lemsml.jlems.core.xml.XMLException;
import org.lemsml.jlems.io.logging.DefaultLogger;
import org.lemsml.jlems.io.util.FileUtil;
 
 
public class TextReaderTest {
 

	@Test
	public void testReadFromString() throws ParseException, BuildException,
			ContentError, XMLException, IOException {
  
		File fex = new File("build/compact-text-examples/example1.txt");
		String testString = FileUtil.readStringFromFile(fex);
		
		E.info("reading test string " + testString.length());
		
		TextElementReader textReader = new TextElementReader(testString); 
		XMLElement xe = textReader.getRootElement();
	  
		E.info(xe.serialize());
	}
	

	public static void main(String[] args) {
		DefaultLogger.initialize();
		
		TextReaderTest ct = new TextReaderTest(); 
		
		Result r = org.junit.runner.JUnitCore.runClasses(ct.getClass());		
		MainTest.checkResults(r);

	}

}