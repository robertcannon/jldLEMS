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

public class ReadWriteExample {

	
	public static void main(String[] argv) throws ModelException, APIException, IOException {
		ReadWriteExample re = new ReadWriteExample();
		re.readModel();
	}
	
	
	
	private void readModel() throws ModelException, APIException, IOException {
		File exd = new File("examples");
		File xmlFile = new File(exd, "example1.xml");
		
		
		FileInclusionReader fir = new FileInclusionReader(xmlFile);
		String xmlText = fir.read();
		
		XMLElementReader xer = new XMLElementReader(xmlText);
		XMLElement xelIn = xer.getRootElement();
		
		GeneratedModelReader mf = new GeneratedModelReader();
		Lems lems = mf.buildLemsFromXMLElement(xelIn);
		
		GeneratedModelWriter mw = new GeneratedModelWriter();
		
		XMLElement xelOut = mw.writeLemsToXML(lems);
	
		String s = xelOut.serialize();

		if (xelIn.subsetOf(xelOut)) {
			System.out.println("Rewritten xml OK for " + xmlFile.getName());
		} else {
			System.out.println("Error on " + xmlFile.getName() + " src and rewritten xml do not match - see out/src.xml and out/out.xml");
			// System.out.println("Source xml: " + xmlText);
			// System.out.println("Rewritten xml: " + s);
		
			File fout = new File("out");
			if (!fout.exists()) {
				fout.mkdirs();
			}
			FileUtil.writeStringToFile(xmlText, new File(fout, "src.xml"));
			FileUtil.writeStringToFile(s, new File(fout, "out.xml"));
		}	 
	 	
	}
	
}
