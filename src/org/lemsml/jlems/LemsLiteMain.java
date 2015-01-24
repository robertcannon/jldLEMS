package org.lemsml.jlems;
 
import java.io.File;
import java.io.IOException;

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
import org.lemsml.jlems.validation.LemsLiteValidator;
import org.lemsml.jlems.viz.datadisplay.SwingDataViewerFactory;
 

public final class LemsLiteMain {

	 static String usage = "USAGE: java -jar lemslite-0.X.X.jar model-file\n";
	

	 private LemsLiteMain() {
		 
	 }
	 
	 
	 public static void showUsage() {
		 E.info(usage);
	 }
	 
	 
	
    public static void main(String[] argv) throws ConnectionError, ContentError, 
    	RuntimeError, ParseError, ParseException, BuildException, XMLException, IOException {        
    	
    	SwingDataViewerFactory.initialize();
		DefaultLogger.initialize();
    	
    	if (argv.length == 0) {
            showUsage();
            System.exit(1);
        }
        
    	File fmod = new File(argv[argv.length - 1]);
        
    	if (argv.length >= 2) {
    		if (argv[0].equals("-v")) {
    			validateModel(fmod);
    			
    		} else {
    			E.error("Unrecognized argument " + argv[0]);
    		}
    	
    	} else {
    		runModel(fmod);
    	}	
    }
    
    
    private static void validateModel(String stxt) {
    	LemsLiteValidator llv = new LemsLiteValidator();
    	boolean ok = llv.checkXMLText(stxt);
    	if (ok) {
    		E.info("OK, valid LemsLits");
    	}
    }
    
    public static void validateModel(File f) throws IOException {
    	String stxt = FileUtil.readStringFromFile(f);
    	validateModel(stxt);
    }
    
    
    public static void runModel(File fmod) throws ContentError, ParseError, ConnectionError, RuntimeError, IOException {
    	
    	String stxt = FileUtil.readStringFromFile(fmod);
        
    	
    	XMLElementReader exmlr = new XMLElementReader(stxt + "    ");

		XMLElement xel = exmlr.getRootElement();
  		
		LemsLiteFactory lf = new LemsLiteFactory();
		LemsLite lemsLite = lf.buildLemsFromXMLElement(xel);
		
		E.info("lemsLite model read: " + lemsLite.getSummary());
	
		// XMLSerializer xs = new XMLSerializer();
		// String sx = xs.serialize(lemsLite);
	 
		File fdir = fmod.getParentFile();
	
		LemsLiteSimulation lls = new LemsLiteSimulation(lemsLite);

		FileDataSource fds = new FileDataSource(fdir);
		
		lls.run(fds);
		
    
 
    
    }
    
     
  
}
