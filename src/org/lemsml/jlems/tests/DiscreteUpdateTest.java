package org.lemsml.jlems.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.lemsml.jlems.core.display.DataViewer;
import org.lemsml.jlems.core.display.DataViewerFactory;
import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.flatten.ComponentFlattener;
import org.lemsml.jlems.core.lite.convert.DUStateTypeBuilder;
import org.lemsml.jlems.core.lite.model.DiscreteUpdateComponent;
import org.lemsml.jlems.core.lite.run.component.DiscreteUpdateStateType;
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
import org.lemsml.jlems.io.util.JUtil;
import org.lemsml.jlems.io.xmlio.XMLSerializer;
import org.lemsml.jlems.viz.datadisplay.SwingDataViewerFactory;
 


public class DiscreteUpdateTest {

	
	 
    public static void main(String[] args) throws ContentError, ParseError, ConnectionError, RuntimeError, IOException, ParseException, BuildException, XMLException {
    
    	SwingDataViewerFactory.initialize();
		DefaultLogger.initialize();
    	
    	DefaultLogger.initialize();
       
    	DiscreteUpdateTest dut = new DiscreteUpdateTest();
    //	dut.runExampleIaF(); 
    	dut.runExampleHH(); 
    }
    
    @Test
    public void runExample1() throws ContentError, ConnectionError, ParseError, IOException, RuntimeError, ParseException, BuildException, XMLException {
    	File f1 = new File("examples/example1.xml");
 		String s = generateDiscreteUpdateComponent(f1, "na");
 		E.info("Generated XML: \n" + s);
    }
    
    @Test
    public void runExampleIaF() throws ContentError, ConnectionError, ParseError, IOException, RuntimeError, ParseException, BuildException, XMLException {
    	File f1 = new File("examples/exampleIAF.xml");
 		String s = generateDiscreteUpdateComponent(f1, "iaf3cpt");
 		E.info("Generated XML: \n" + s);
    }
    
    @Test
    public void runExampleHH() throws ContentError, ConnectionError, ParseError, IOException, RuntimeError, ParseException, BuildException, XMLException {
    	File f1 = new File("examples/example1.xml");
 		String s = generateDiscreteUpdateComponent(f1, "hhcell_1");
 		E.info("Generated XML: \n" + s);
    }
    
    
    public String generateDiscreteUpdateComponent(File f, String tgtid) throws ContentError,
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
		
		StateInstance storig = st.newInstance();
		st.build(storig);
		
		st.removeRedundantExpressions();
		st.sortExpressions();
		
		String numcontents = JUtil.getRelativeResource(new NumericsRoot(), "defaultNumerics.xml");
	 		
		Sim simnum = new Sim(numcontents);

		simnum.readModel();

		Lems lemsnum = simnum.getLems();

		LemsCollection<IntegrationScheme> schemes = lemsnum.getIntegrationSchemes();

		IntegrationScheme euler = schemes.getByName("forwardEuler");

		DiscreteUpdateGenerator dug = new DiscreteUpdateGenerator(st, euler);
		
		DiscreteUpdateComponent dum = dug.buildDiscreteUpdateComponent();
		
		String ret = XMLSerializer.serialize(dum);
		
	 	
		LemsLiteFactory llf = new LemsLiteFactory();
		 
		XMLElementReader exmlr = new XMLElementReader(ret + "    ");
		XMLElement xel = exmlr.getRootElement();
  		
		DiscreteUpdateComponent dum2 = llf.readDiscreteUpdateComponent(xel);
		
		String ser2 = XMLSerializer.serialize(dum2);
		if (ret.equals(ser2)) {
			E.info("OK: model after reread is same as orignial");
		} else {
			E.error("Reread error - models differ. Original: \n " + ret + "\n\nreread:\n" + ser2);
		} 
		
		
		DUStateTypeBuilder cdustg = new DUStateTypeBuilder(dum);
		DiscreteUpdateStateType dust = cdustg.makeDiscretUpdateStateType();

		StateRunnable sr = dust.newStateRunnable();
		
		DataViewer dv = DataViewerFactory.getFactory().newDataViewer("du-model");
		
	
		RunnableAccessor raorig = new RunnableAccessor(storig);
		RuntimeRecorder rrorig = new RuntimeRecorder("v-orig", "v");
		rrorig.setColor("#00d000");
		rrorig.connectRunnable(raorig, dv);
		
		
		RunnableAccessor ra = new RunnableAccessor(sr);
		
		ArrayList<RuntimeRecorder> arr = new ArrayList<RuntimeRecorder>();
		
		String[] avars = {"popna_na_m_Reverse_r", "popna_na_m_Forward_x", "popna_na_m_Forward_r",
					"popna_na_m_ex", "popna_na_m_q", "popna_na_m_fcond", "v"};
		
		String[] vars = {"popna_na_m_ex", "popna_na_m_q", "popna_na_m_fcond", "v"};
		String[] cols = {"#ff0000", "#00ff00", "#0000ff", "#ffff00", "#00ffff", "#ff00ff", "#ffffff"};
		
		
		
		for (int i = 0; i < vars.length; i++) {
			RuntimeRecorder rs = new RuntimeRecorder(vars[i]);
			
			rs.setColor(cols[i]);
			
			rs.connectRunnable(ra,  dv);
			arr.add(rs);
		}
		
		
		
		double dt = 5e-5;
		double t = 0.;
		for (int i = 0; i < 1000; i++) {
			sr.advance(null,  t, dt);
			
			storig.advance(null, t, dt);
			
			t += dt;
			for (RuntimeRecorder r : arr) {
				r.appendState(t);
			}
			rrorig.appendState(t);
		}
		
		
		return ret;
    }
}
