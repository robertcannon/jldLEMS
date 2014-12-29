package org.lemsml.io.jld;

import java.io.File;
import java.io.IOException;

import org.lemsml.api.APIException;
import org.lemsml.io.jld.reader.FileInclusionReader;
import org.lemsml.io.jld.reader.FileUtil;
import org.lemsml.io.jld.reader.JLDModelReader;
import org.lemsml.io.jld.reader.GeneratedModelReader;
import org.lemsml.io.jld.writer.GeneratedModelWriter;
import org.lemsml.io.jld.xml.XMLElement;
import org.lemsml.io.jld.xml.XMLElementReader;
import org.lemsml.model.Lems;
import org.lemsml.model.core.ModelException;

public class ReadWriteExample {

	
	public static void main(String[] argv) throws ModelException, APIException, IOException {
		ReadWriteExample re = new ReadWriteExample();
		re.readModel();
	}
	
	
	
	private void readModel() throws ModelException, APIException, IOException {
		File exd = new File("examples");
		File xmlFile = new File(exd, "bounce.xml");
		
		
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
			System.out.println("Rewritten xml OK: " + s);
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
