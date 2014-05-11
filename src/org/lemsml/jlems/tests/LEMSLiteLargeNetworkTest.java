package org.lemsml.jlems.tests;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.lite.model.LemsLite;
import org.lemsml.jlems.core.lite.simulation.LemsLiteSimulation;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.reader.LemsLiteFactory;
import org.lemsml.jlems.core.run.ConnectionError;
import org.lemsml.jlems.core.run.RuntimeError;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.sim.ParseException;
import org.lemsml.jlems.core.type.BuildException;
import org.lemsml.jlems.core.xml.XMLElement;
import org.lemsml.jlems.core.xml.XMLElementReader;
import org.lemsml.jlems.core.xml.XMLException;
import org.lemsml.jlems.io.data.FileDataSource;
import org.lemsml.jlems.io.logging.DefaultLogger;
import org.lemsml.jlems.io.util.FileUtil;
import org.lemsml.jlems.viz.datadisplay.SwingDataViewerFactory;

 


public class LEMSLiteLargeNetworkTest {

	
	 
    public static void main(String[] args) throws ContentError, ParseError, ConnectionError, RuntimeError, IOException, ParseException, BuildException, XMLException {
    
    	SwingDataViewerFactory.initialize();
		DefaultLogger.initialize();
    
    	LEMSLiteLargeNetworkTest dut = new LEMSLiteLargeNetworkTest();
    //	dut.runExampleIaF(); 
    	dut.runExampleHandwriting();  	
    }
     
     
    
    @Test
    public void runExampleHandwriting() throws ContentError, ConnectionError, ParseError, IOException, RuntimeError, ParseException, BuildException, XMLException {
    	File f1 = new File("examples/mh_handwriting.xml");
    	
    	runDiscreteUpdateComponent(f1);
    	
    }
    
    public void runExampleHandwritingSmall() throws ContentError, ConnectionError, ParseError, IOException, RuntimeError, ParseException, BuildException, XMLException {
    	File f1 = new File("examples/handwriting_small.xml");
    	
    	runDiscreteUpdateComponent(f1);
    	
    }
    
    
    
    private void runDiscreteUpdateComponent(File f1) throws ContentError, IOException, ParseError, ConnectionError, RuntimeError {
    
    	String stxt = FileUtil.readStringFromFile(f1);
     
    	XMLElementReader exmlr = new XMLElementReader(stxt + "    ");

		XMLElement xel = exmlr.getRootElement();
  		
		LemsLiteFactory lf = new LemsLiteFactory();
		LemsLite lemsLite = lf.buildLemsFromXMLElement(xel);
		
		E.info("lemsLite model read: " + lemsLite.getSummary());
	
		// XMLSerializer xs = new XMLSerializer();
		// String sx = xs.serialize(lemsLite);
	 
		File fdir = f1.getParentFile();
	
		LemsLiteSimulation lls = new LemsLiteSimulation(lemsLite);
		
		FileDataSource fds = new FileDataSource(fdir);
		
		lls.run(fds);
		
    }
    
	
    
   
}
