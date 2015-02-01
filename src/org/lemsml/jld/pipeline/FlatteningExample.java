package org.lemsml.jld.pipeline;

import java.io.File;
import java.io.IOException;

import org.lemsml.jld.api.APIException;
import org.lemsml.jld.flattener.LemsFlattener;
import org.lemsml.jld.hrun.ConnectionError;
import org.lemsml.jld.hrun.RuntimeError;
import org.lemsml.jld.hrun.Sim;
import org.lemsml.jld.hrun.StateType;
import org.lemsml.jld.hsim.StateTypeBuilder;
import org.lemsml.jld.io.E;
import org.lemsml.jld.io.reader.FileInclusionReader;
import org.lemsml.jld.io.reader.FileUtil;
import org.lemsml.jld.io.reader.GeneratedModelReader;
import org.lemsml.jld.io.writer.GeneratedModelWriter;
import org.lemsml.jld.io.xml.XMLElement;
import org.lemsml.jld.io.xml.XMLElementReader;
import org.lemsml.jld.model.Component;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.core.ModelException;
import org.lemsml.jld.report.Reporter;
import org.lemsml.jld.resolve.LemsResolver;
import org.lemsml.jld.validation.ConsistencyCheck;
import org.lemsml.jld.viz.SwingDataViewerFactory;

public class FlatteningExample {

	
	public static void main(String[] argv) throws ModelException, APIException, IOException, RuntimeError, ConnectionError {
		FlatteningExample re = new FlatteningExample();
		re.processModel();
	}
	
	
	
	private void processModel() throws ModelException, APIException, IOException, RuntimeError, ConnectionError {
		File exd = new File("examples");
		File xmlFile = new File(exd, "example1.xml");
		
		
		// read the file embedding any included files
		FileInclusionReader fir = new FileInclusionReader(xmlFile);
		String xmlText = fir.read();
		
		// parse the xml into an element tree
		XMLElementReader xer = new XMLElementReader(xmlText);
		XMLElement xelIn = xer.getRootElement();
		
		// instantiate objects from jld.model.* for each XML element and return the main Lems element
		GeneratedModelReader mf = new GeneratedModelReader();
		Lems lems = mf.buildLemsFromXMLElement(xelIn);
		
		
		// list the component types that we've read so far
		Reporter reporter = new Reporter(lems);
		reporter.reportTypes();
		
		
		// resolve references to dimensions and other component types; parse expressions in Dynamics blocks
		LemsResolver lr = new LemsResolver(lems);
		lr.resolveTypes();
		
		// check consistency of dimensions in expressions and references to parameters
		ConsistencyCheck cc = new ConsistencyCheck(lems);
		cc.checkExpressions();
		cc.checkPaths();
		// at this stage we should have checked what we can about the types.
		
		
		reporter.reportComponents();
		
		// resolve references in components to types and other components
		lr.resolveComponents();
		
		
		GeneratedModelWriter mw = new GeneratedModelWriter();
		XMLElement xelOut = mw.writeLemsToXML(lems);
		String s = xelOut.serialize();

		
		System.out.println("Orignal lems:\n" + s);
		
		
		LemsFlattener lf = new LemsFlattener();
		
		Lems flatLems = lf.flatten(lems);
		
		XMLElement flatOut = mw.writeLemsToXML(flatLems);
		String sf = flatOut.serialize();
		System.out.println("Flat lems:\n" + sf);
		
		
		// Now each component should have all the references it needs. If there are no errors so far, we can be 
		// confident that the paths and expressions are all valid.
		// N.B. this is not so at the time of writing, but as errors occur in subsequent code, we should extend the 
		// resolvers and checks to identify them earlier rather than put error checking in after this stage.
		// After this stage the process can branch into a number of different directions so we don't want to replicate
		// further checks in each of those.
		
		/*
		Component target = lems.getTargetComponent();
		
		E.info("Target id is " + target.getId());
		 
		StateTypeBuilder sb = new StateTypeBuilder(lems);
		
		StateType rootType = sb.makeStateType(target, 0);
 
		Sim sim = new Sim(rootType);
		
		SwingDataViewerFactory.initialize();
		sim.build();
		sim.run();
		*/
		
	}
		
	
	
	
	
	private void rewrite(Lems lems, XMLElement xelIn) throws APIException, ModelException, IOException {
		GeneratedModelWriter mw = new GeneratedModelWriter();
		
		XMLElement xelOut = mw.writeLemsToXML(lems);
	
		String s = xelOut.serialize();

		if (xelIn.subsetOf(xelOut)) {
			System.out.println("Rewritten xml OK for " + lems.getSourceName());
		} else {
			System.out.println("Error on " + lems.getSourceName() + " src and rewritten xml do not match - see out/src.xml and out/out.xml");
			// System.out.println("Source xml: " + xmlText);
			// System.out.println("Rewritten xml: " + s);
		
			File fout = new File("out");
			if (!fout.exists()) {
				fout.mkdirs();
			}
			// FileUtil.writeStringToFile(xmlText, new File(fout, "src.xml"));
			FileUtil.writeStringToFile(s, new File(fout, "out.xml"));
		}	 
	 	
	}
	
}
