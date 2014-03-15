package org.lemsml.jlems;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.flatten.ComponentFlattener;
import org.lemsml.jlems.core.lite.model.DiscreteUpdateComponent;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.numerics.DiscreteUpdateGenerator;
import org.lemsml.jlems.core.numerics.IntegrationScheme;
import org.lemsml.jlems.core.run.ConnectionError;
import org.lemsml.jlems.core.run.RuntimeError;
import org.lemsml.jlems.core.run.StateType;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.sim.ParseException;
import org.lemsml.jlems.core.sim.Sim;
import org.lemsml.jlems.core.type.BuildException;
import org.lemsml.jlems.core.type.Component;
import org.lemsml.jlems.core.type.ComponentType;
import org.lemsml.jlems.core.type.Lems;
import org.lemsml.jlems.core.type.LemsCollection;
import org.lemsml.jlems.core.xml.XMLException;
import org.lemsml.jlems.io.logging.DefaultLogger;
import org.lemsml.jlems.io.reader.FileInclusionReader;
import org.lemsml.jlems.io.util.FileUtil;
import org.lemsml.jlems.io.xmlio.XMLSerializer;
 


public class LEMSToLite {

	
	 
    public static void main(String[] args) throws ContentError, ParseError, ConnectionError, RuntimeError, IOException, ParseException, BuildException, XMLException {
    	DefaultLogger.initialize();
       
    	if (args.length < 1) {
    		E.info("USAGE: LEMSToLite modelFile [componentID]\n where model file points to the file " +
    				"containing the LEMS definition. Optionally componentID is the id of a compoent " +
    				"to be converted to LEMS-lite. If not specified, all components will be converted.");
    	} else {
    		File fmod = new File(args[0]);
    		if (!fmod.exists()) {
    			E.error("No such file: " + fmod.getAbsolutePath());
    		} else {
    			String cptID = null;
    			if (args.length >= 2) {
    				cptID = args[1];
    					
    				LEMSToLite ltl = new LEMSToLite();
    				
    				ltl.doConversion(fmod, cptID);
    			}
    		}
    	}
    }
    
    
    private void doConversion(File fmod, String cptID) throws ContentError, ConnectionError, ParseError, IOException, RuntimeError, ParseException, BuildException, XMLException {
    	String fnm = fmod.getName();
    	if (fnm.endsWith(".xml")) {
    		fnm = fnm.substring(0, fnm.length() - 4);
    	}
    	File fout = new File(fnm + "-lite.xml");
    	
    	ArrayList<String> args = new ArrayList<String>();
    	args.add(fmod.getAbsolutePath());
    	args.add("-o");
    	args.add(fout.getAbsolutePath());
    	args.add("-t");
    	if (cptID != null) {
    		args.add(cptID);
    	} else {
    		args.add("_ALL");
    	}
    	args.add("-d");
    	String[] sargs = args.toArray(new String[0]);
    	
    	String sa = sargs[0];
    	for (int i = 1; i < sargs.length; i++) {
    		sa += " " + sargs[i];
    	}
    	E.info("Calling lems main with arguments: " + sa);
    	
    	Main.main(sargs);
    	 
    }
    
     
}
