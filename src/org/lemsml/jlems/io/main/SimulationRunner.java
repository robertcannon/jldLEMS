package org.lemsml.jlems.io.main;

import java.io.File;

import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.run.ConnectionError;
import org.lemsml.jlems.core.run.RuntimeError;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.sim.ParseException;
import org.lemsml.jlems.core.sim.Sim;
import org.lemsml.jlems.core.type.BuildException;
import org.lemsml.jlems.core.xml.XMLException;
import org.lemsml.jlems.io.reader.FileInclusionReader;

public class SimulationRunner {
	
	String modelName;
	String typePath;
	
	public SimulationRunner(String mn, String tp) {
		modelName = mn;
		typePath = tp;
	}
	
	
	public void readBuildRun() throws ContentError, ParseError, ParseException, BuildException, XMLException, ConnectionError, RuntimeError {
		
	 
		File simFile = new File(modelName);
		
	    if (!simFile.exists()) {
	    	E.error("No such file: " + simFile.getAbsolutePath());
	    	System.exit(1);
	    }

	    FileInclusionReader fir = new FileInclusionReader(simFile);
	    if (typePath != null) {
	    	fir.addSearchPaths(typePath);
	    }
	    Sim sim = new Sim(fir.read());
        
	    sim.readModel();
	    sim.build();
        
	    boolean doRun = true;
        
	    if (doRun) {
	    	sim.run();
	    	E.info("Finished reading, building, running & displaying LEMS model");
	    }    
		 
	}

	
}