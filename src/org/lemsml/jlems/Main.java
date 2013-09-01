package org.lemsml.jlems;
 
import java.io.IOException;
import java.util.HashMap;

import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.run.ConnectionError;
import org.lemsml.jlems.core.run.RuntimeError;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.sim.ParseException;
import org.lemsml.jlems.core.type.BuildException;
import org.lemsml.jlems.core.xml.XMLException;
import org.lemsml.jlems.io.logging.DefaultLogger;
import org.lemsml.jlems.io.main.ArgReader;
import org.lemsml.jlems.io.main.Discretizer;
import org.lemsml.jlems.io.main.SimulationRunner;
 

public final class Main {

	 static String usage = "USAGE: java -jar lems-0.X.X.jar [-cp folderpaths] model-file\n";
	

	 private Main() {
		 
	 }
	 
	 
	 public static void showUsage() {
		 E.info(usage);
	 }
	 
	 
	 public static void selfContainedMain(String[] argv) {
		 try {
			 main(argv);
		 } catch (Exception ex) {
			 E.report("failed: " + argv, ex);
		 }
	 }
	 
	
    public static void main(String[] argv) throws ConnectionError, ContentError, 
    	RuntimeError, ParseError, ParseException, BuildException, XMLException, IOException {        
      
    	DefaultLogger.initialize();
    	
    	if (argv.length == 0) {
            showUsage();
            System.exit(1);
        }
        
        HashMap<String, String> argMap = ArgReader.parseArguments(argv);
        
        String typePath = null;
        String modelName = null;
        String targetID = null;
        String outFile = null;
        
        if (argMap.containsKey("-cp")) {
        	typePath = argMap.get("-cp");
        	argMap.remove("-cp");
        }
        if (argMap.containsKey("0")) {
        	modelName = argMap.get("0");
        	argMap.remove("0");
        }
            
        if (modelName == null) {
        	showUsage();
        	System.exit(1);
        }
    
        if (argMap.containsKey("-o")) {
        	outFile = argMap.get("-o");
        }
        
        if (argMap.containsKey("-t")) {
        	targetID = argMap.get("-t");
        }
        
        if (argMap.containsKey("-d")) {
        	// generate the discrete update version of the model
        	String numericsFile = null;
        	if (argMap.containsKey("-n")) {
        		numericsFile = argMap.get("-n");
        	}
        	Discretizer dz = new Discretizer(modelName, typePath);
        	if (numericsFile != null) {
        		dz.setNumericsFile(numericsFile);
        	}
        	if (targetID != null) {
        		dz.setTargetID(targetID);
        	}
        	if (outFile != null) {
        		dz.setOutFile(outFile);
        	}
        	
        	dz.generateDiscreteModel();
        	
        	
        	
        } else if (argMap.containsKey("-f")) {
        	// generate the flat version of the model
        	
        	
        } else {        
        	// run the simulation
        	SimulationRunner sr = new SimulationRunner(modelName, typePath);
        	sr.readBuildRun();
        }
     
 
    
    }
    
     
  
}
