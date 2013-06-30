/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lemsml.jlems.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.Result;
import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.run.ConnectionError;
import org.lemsml.jlems.core.run.RuntimeError;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.sim.ParseException;
import org.lemsml.jlems.core.sim.Sim;
import org.lemsml.jlems.core.type.BuildException;
import org.lemsml.jlems.core.xml.XMLException;
import org.lemsml.jlems.io.logging.DefaultLogger;
import org.lemsml.jlems.io.reader.FileInclusionReader;

 
public class Example1Test {

 
    @Test
    public void testExample1() throws ContentError, ConnectionError, RuntimeError, ParseError, IOException, ParseException, BuildException, XMLException  {
          boolean ret = executeExample("example1.xml");
          assertTrue("example 1", ret);
    }

   
    public boolean executeExample(String filename) throws ContentError, ConnectionError, RuntimeError, ParseError, IOException, ParseException, BuildException, XMLException {
    	File fdir = new File("examples");
    	File f = new File(fdir, filename);
    	FileInclusionReader fir = new FileInclusionReader(f);
    
    	E.info("Reading from " + f.getAbsolutePath());
    	String stxt = fir.read();
    	
    	
    	Sim sim = new Sim(stxt);

    	
        sim.readModel();
        
        sim.setMaxExecutionTime(1000);
        
        sim.build();
        sim.run();
        
        E.info("OK - executed " + filename);
        return true;
    }
    
    

    public static void main(String[] args) {
    	DefaultLogger.initialize();
    	
    	Example1Test ct = new Example1Test();
        Result r = org.junit.runner.JUnitCore.runClasses(ct.getClass());
        MainTest.checkResults(r);

    }

}