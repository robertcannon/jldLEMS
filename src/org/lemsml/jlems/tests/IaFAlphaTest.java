/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lemsml.jlems.tests;

import java.io.IOException;

import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.run.ConnectionError;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.sim.ParseException;
import org.lemsml.jlems.core.type.BuildException;
import org.lemsml.jlems.core.xml.XMLException;
import org.lemsml.jlems.io.logging.DefaultLogger;
import org.lemsml.jlems.io.main.Discretizer;
import org.lemsml.jlems.validation.LemsLiteValidator;
 
 
public class IaFAlphaTest {

  
 
    

    public static void main(String[] args) throws IOException, ContentError, ParseError, ParseException, BuildException, XMLException, ConnectionError {
    	DefaultLogger.initialize();
    	
    	Discretizer dtz = new Discretizer("examples/exampleIAFCurrAlpha.xml", "examples");
    	dtz.setTargetID("iaf1");
    	
    	String xcpt = dtz.generateDiscreteModel();
    	
    	String llxml = "<LemsLite>\n" + xcpt + "\n</LemsLite>";
    	
    	LemsLiteValidator llv = new LemsLiteValidator();
    	llv.checkXMLText(llxml);

    }

}