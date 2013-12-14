package org.lemsml.jlems.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.lemsml.jlems.core.display.DataViewer;
import org.lemsml.jlems.core.display.DataViewerFactory;
import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.flatten.ComponentFlattener;
import org.lemsml.jlems.core.lite.model.DiscreteUpdateComponent;
  
import org.lemsml.jlems.core.lite.model.LemsLite;
import org.lemsml.jlems.core.lite.run.component.DiscreteUpdateStateType;
import org.lemsml.jlems.core.lite.simulation.LemsLiteSimulation;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.numerics.DiscreteUpdateGenerator;
import org.lemsml.jlems.core.numerics.IntegrationScheme;
import org.lemsml.jlems.core.numerics.NumericsRoot;
import org.lemsml.jlems.core.reader.LemsLiteFactory;
import org.lemsml.jlems.core.run.ConnectionError;
import org.lemsml.jlems.core.run.RuntimeError;
import org.lemsml.jlems.core.run.RuntimeRecorder;
import org.lemsml.jlems.core.run.StateInstance;
import org.lemsml.jlems.core.run.StateRunnable;
import org.lemsml.jlems.core.run.StateType;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.sim.ParseException;
import org.lemsml.jlems.core.sim.RunnableAccessor;
import org.lemsml.jlems.core.sim.Sim;
import org.lemsml.jlems.core.type.BuildException;
import org.lemsml.jlems.core.type.Component;
import org.lemsml.jlems.core.type.ComponentType;
import org.lemsml.jlems.core.type.Lems;
import org.lemsml.jlems.core.type.LemsCollection;
import org.lemsml.jlems.core.xml.XMLElement;
import org.lemsml.jlems.core.xml.XMLElementReader;
import org.lemsml.jlems.core.xml.XMLException;
import org.lemsml.jlems.io.logging.DefaultLogger;
import org.lemsml.jlems.io.reader.FileInclusionReader;
import org.lemsml.jlems.io.util.FileUtil;
import org.lemsml.jlems.io.util.JUtil;
import org.lemsml.jlems.io.xmlio.XMLSerializer;
import org.lemsml.jlems.viz.datadisplay.SwingDataViewerFactory;
 


public class LEMSLiteSmallNetworkTest {

	
	 
    public static void main(String[] args) throws ContentError, ParseError, ConnectionError, RuntimeError, IOException, ParseException, BuildException, XMLException {
    
    	SwingDataViewerFactory.initialize();
		DefaultLogger.initialize();
    
    	LEMSLiteSmallNetworkTest dut = new LEMSLiteSmallNetworkTest();
    //	dut.runExampleIaF(); 
    //	dut.runExampleHandwriting(); 
    	
    	dut.runExampleHandwritingSmall();
    	
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
	
		XMLSerializer xs = new XMLSerializer();
		String sx = xs.serialize(lemsLite);
	 
		File fdir = f1.getParentFile();
	
		LemsLiteSimulation lls = new LemsLiteSimulation(lemsLite);

		lls.run(fdir);
		
    }
    
	
    
   
}
