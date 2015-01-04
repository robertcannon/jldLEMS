package org.lemsml.jld.pipeline;

import java.io.File;
import java.io.IOException;

import org.lemsml.jld.api.APIException;
import org.lemsml.jld.io.reader.FileInclusionReader;
import org.lemsml.jld.io.reader.FileUtil;
import org.lemsml.jld.io.reader.GeneratedModelReader;
import org.lemsml.jld.io.reader.JLDModelReader;
import org.lemsml.jld.io.writer.GeneratedModelWriter;
import org.lemsml.jld.io.xml.XMLElement;
import org.lemsml.jld.io.xml.XMLElementReader;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.core.ModelException;
import org.lemsml.jld.report.Reporter;
import org.lemsml.jld.resolver.LemsResolver;
import org.lemsml.jld.validation.ConsistencyCheck;

public class PipelineExample {

	
	public static void main(String[] argv) throws ModelException, APIException, IOException {
		PipelineExample re = new PipelineExample();
		re.processModel();
	}
	
	
	
	private void processModel() throws ModelException, APIException, IOException {
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
		// reporter.reportTypes();
		
		
		// resolve references to dimensions and other component types; parse expressions in Dynamics blocks
		LemsResolver lr = new LemsResolver(lems);
		lr.resolveTypes();
		
		// check consistency of dimensions in expressions and references to parameters
		ConsistencyCheck cc = new ConsistencyCheck(lems);
		cc.checkExpressions();
		cc.checkPaths();
		// at this stage we should have checked what we can about the types.
		
		
		
		// resolve references in components to types and other components
		lr.resolveComponents();
		
		
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
