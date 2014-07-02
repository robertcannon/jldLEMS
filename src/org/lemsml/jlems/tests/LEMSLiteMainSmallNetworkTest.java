package org.lemsml.jlems.tests;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.lemsml.jlems.LemsLiteMain;
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
 


public class LEMSLiteMainSmallNetworkTest {

	
	 
    public static void main(String[] args) throws ContentError, ParseError, ConnectionError, RuntimeError, IOException, ParseException, BuildException, XMLException {
     
       	String sf = "examples/handwriting_small.xml";
    	
       	String[] callargs = {"-v", sf};
     
       	LemsLiteMain.main(callargs);
       	
       	
       	String sf2 = "examples/mh_handwriting.xml";
    	
       	String[] callargs2 = {"-v", sf2};
     
       	LemsLiteMain.main(callargs);
    }
    
    
   
}
