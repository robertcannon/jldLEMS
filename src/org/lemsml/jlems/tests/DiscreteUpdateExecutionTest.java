package org.lemsml.jlems.tests;

import java.io.File;
import java.io.IOException;

import org.lemsml.jlems.core.codger.CodeGenerationException;
import org.lemsml.jlems.core.codger.CompilationException;
import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.flatten.ComponentFlattener;
import org.lemsml.jlems.core.lite.DiscreteUpdateGenerator;
import org.lemsml.jlems.core.lite.model.DiscreteUpdateComponent;
import org.lemsml.jlems.core.lite.run.DiscreteUpdateStateType;
import org.lemsml.jlems.core.logging.E;
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
import org.lemsml.jlems.core.xml.XMLException;
import org.lemsml.jlems.io.logging.DefaultLogger;
import org.lemsml.jlems.io.reader.FileInclusionReader;
import org.lemsml.jlems.viz.datadisplay.SwingDataViewerFactory;


public class DiscreteUpdateExecutionTest {

	
	public static void main(String[] argv) {
		DefaultLogger.initialize();
		SwingDataViewerFactory.initialize();
		
		DiscreteUpdateExecutionTest dct = new DiscreteUpdateExecutionTest();
		try {
			File f1 = new File("examples/example1.xml");
			dct.generateAndRun(f1);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public void generateAndRun(File fmod) throws CodeGenerationException, CompilationException, 
	ContentError, ConnectionError, ParseError, RuntimeError, ParseException, BuildException, XMLException {
	 		String pkgName = "org.lemsml.dynamic";
		
		String tgtid = "na";
	 
    	FileInclusionReader fir = new FileInclusionReader(fmod);
    	Sim sim = new Sim(fir.read());

        sim.readModel();
        
        sim.setMaxExecutionTime(1000);
 
        Lems lems = sim.getLems();
        Component cpt = lems.getComponent(tgtid);

        ComponentFlattener cf = new ComponentFlattener(lems, cpt);

        ComponentType ct = cf.getFlatType();
        Component cp = cf.getFlatComponent();
        
		lems.addComponentType(ct);
		lems.addComponent(cp);
	
		lems.resolve(ct);
		lems.resolve(cp);
	  
		
		StateType st = cp.getStateType();
		E.missing();
		DiscreteUpdateGenerator dug = new DiscreteUpdateGenerator(st, null);
		DiscreteUpdateComponent dum = dug.buildDiscreteUpdateComponent();
        
        DiscreteUpdateStateType dui = new DiscreteUpdateStateType(dum);
        dui.resolve();
		
        E.info("Substitting DU type " + dui);
        sim.addSubstitutionType(tgtid, dui);
//        sim.addSubstitutionType(ntk);
        
     
         
        sim.build();
        
       
        long t1 = System.currentTimeMillis();
        sim.runTree();
        
        long t2 = System.currentTimeMillis();
         E.info("Execution took " + (t2 - t1));
        
    
	}
	
 
	
	 
		 
	  
	 
	

    public boolean executeExample(String filename) throws ContentError, ConnectionError, RuntimeError, ParseError, IOException, ParseException, BuildException, XMLException {
    	File fdir = new File("examples");
    	File f = new File(fdir, filename);
    	FileInclusionReader fir = new FileInclusionReader(f);
    	Sim sim = new Sim(fir.read());

        sim.readModel();
        
        sim.setMaxExecutionTime(1000);
        
        sim.build();
        sim.run();
        
        E.info("OK - executed " + filename);
        return true;
    }
    
    

   
	  
    
    
    
  
	
	 
	
	
}
