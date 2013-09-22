package org.lemsml.jlems.tests;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.lemsml.jlems.core.discrete.DiscreteUpdateGenerator;
import org.lemsml.jlems.core.discrete.DiscreteUpdateModel;
import org.lemsml.jlems.core.discrete.NumericsRoot;
import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.flatten.ComponentFlattener;
import org.lemsml.jlems.core.logging.E;
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
import org.lemsml.jlems.core.xml.XMLElement;
import org.lemsml.jlems.core.xml.XMLException;
import org.lemsml.jlems.io.logging.DefaultLogger;
import org.lemsml.jlems.io.reader.FileInclusionReader;
import org.lemsml.jlems.io.util.JUtil;
 


public class DiscreteUpdateTest {

	
	 
    public static void main(String[] args) throws ContentError, ParseError, ConnectionError, RuntimeError, IOException, ParseException, BuildException, XMLException {
    	DefaultLogger.initialize();
       
    	DiscreteUpdateTest dut = new DiscreteUpdateTest();
    	dut.runExampleIaF(); 
    	dut.runExampleHH(); 
    }
    
    @Test
    public void runExample1() throws ContentError, ConnectionError, ParseError, IOException, RuntimeError, ParseException, BuildException, XMLException {
    	File f1 = new File("examples/example1.xml");
 		String s = generateDiscreteUpdateModel(f1, "na");
 		E.info("Generated XML: \n" + s);
    }
    
    @Test
    public void runExampleIaF() throws ContentError, ConnectionError, ParseError, IOException, RuntimeError, ParseException, BuildException, XMLException {
    	File f1 = new File("examples/exampleIAF.xml");
 		String s = generateDiscreteUpdateModel(f1, "iaf3cpt");
 		E.info("Generated XML: \n" + s);
    }
    
    @Test
    public void runExampleHH() throws ContentError, ConnectionError, ParseError, IOException, RuntimeError, ParseException, BuildException, XMLException {
    	File f1 = new File("examples/example1.xml");
 		String s = generateDiscreteUpdateModel(f1, "hhcell_1");
 		E.info("Generated XML: \n" + s);
    }
    
    
    public String generateDiscreteUpdateModel(File f, String tgtid) throws ContentError,
    		ConnectionError, ParseError, IOException, RuntimeError, ParseException, 
    		BuildException, XMLException {
    	E.info("Loading LEMS file from: " + f.getAbsolutePath());

        FileInclusionReader fir = new FileInclusionReader(f);
        Sim sim = new Sim(fir.read());

        sim.readModel();
        sim.build();

        Lems lems = sim.getLems();
        Component cpt = lems.getComponent(tgtid);

        ComponentFlattener cf = new ComponentFlattener(lems, cpt);

        ComponentType ct = cf.getFlatType();
        Component cp = cf.getFlatComponent();
        
        // String typeOut = XMLSerializer.serialize(ct);
        // String cptOut = XMLSerializer.serialize(cp);
      
        // E.info("Flat type: \n" + typeOut);
        // E.info("Flat cpt: \n" + cptOut);
        
		lems.addComponentType(ct);
		lems.addComponent(cp);
	
		lems.resolve(ct);
		lems.resolve(cp);
	 
		
		StateType st = cp.getStateType();
		
		st.removeRedundantExpressions();
		st.sortExpressions();
		
		String numcontents = JUtil.getRelativeResource(new NumericsRoot(), "defaultNumerics.xml");
	 		
		Sim simnum = new Sim(numcontents);

		simnum.readModel();

		Lems lemsnum = simnum.getLems();

		LemsCollection<IntegrationScheme> schemes = lemsnum.getIntegrationSchemes();

		IntegrationScheme euler = schemes.getByName("forwardEuler");

		DiscreteUpdateGenerator dug = new DiscreteUpdateGenerator(st, euler);
		
		DiscreteUpdateModel dum = dug.buildDiscreteUpdateModel();
		
		XMLElement xel = dum.toXML(); 
		
		String ret = xel.serialize();
		
		return ret;
    }
}
