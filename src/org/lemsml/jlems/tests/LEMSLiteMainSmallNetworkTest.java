package org.lemsml.jlems.tests;

import java.io.IOException;

import org.lemsml.jlems.LemsLiteMain;
import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.run.ConnectionError;
import org.lemsml.jlems.core.run.RuntimeError;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.sim.ParseException;
import org.lemsml.jlems.core.type.BuildException;
import org.lemsml.jlems.core.xml.XMLException;
 


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
