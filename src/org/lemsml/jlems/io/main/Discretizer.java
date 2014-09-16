package org.lemsml.jlems.io.main;

import java.io.File;
import java.io.IOException;

import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.flatten.ComponentFlattener;
import org.lemsml.jlems.core.lite.model.DiscreteUpdateComponent;
import org.lemsml.jlems.core.lite.model.LemsLite;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.numerics.DiscreteUpdateGenerator;
import org.lemsml.jlems.core.numerics.IntegrationScheme;
import org.lemsml.jlems.core.numerics.NumericsRoot;
import org.lemsml.jlems.core.run.ConnectionError;
import org.lemsml.jlems.core.run.StateType;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.sim.ParseException;
import org.lemsml.jlems.core.sim.Sim;
import org.lemsml.jlems.core.type.BuildException;
import org.lemsml.jlems.core.type.Component;
import org.lemsml.jlems.core.type.ComponentType;
import org.lemsml.jlems.core.type.Lems;
import org.lemsml.jlems.core.type.LemsCollection;
import org.lemsml.jlems.core.xml.XMLException;
import org.lemsml.jlems.io.reader.FileInclusionReader;
import org.lemsml.jlems.io.util.FileUtil;
import org.lemsml.jlems.io.util.JUtil;
import org.lemsml.jlems.io.xmlio.XMLSerializer;
import org.lemsml.jlems.validation.LemsLiteValidator;

public class Discretizer {

	String modelName;
	String typePath;

	String numericsPath;
	String targetID;
	String outPath;

	public Discretizer(String mn, String tp) {
		modelName = mn;
		typePath = tp;
	}

	public void setNumericsFile(String np) {
		numericsPath = np;
	}

	public void setTargetID(String sid) {
		targetID = sid;
	}

	public void setOutFile(String op) {
		outPath = op;
	}

	public String generateDiscreteModel() throws IOException, ContentError, ParseError, ParseException, BuildException, XMLException, ConnectionError {
	 
			File simFile = new File(modelName);

			if (!simFile.exists()) {
				E.error("No such file: " + simFile.getAbsolutePath());
				System.exit(1);
			}

			FileInclusionReader fir = new FileInclusionReader(simFile);
			if (typePath != null) {
				fir.addSearchPaths(typePath);
			}
			Sim sim = new Sim(fir.read());

			sim.readModel();
			sim.build();

			Lems lems = sim.getLems();

			Component cpt = lems.getComponent(targetID);

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

			StateType st = cp.getFixedStateType();
			
		
			

			E.info("St summary: " + st.getSummary());
			
			
			// now get the numerics specification

			
			String numcontents = "";
			if (numericsPath != null) {
				File fnum = new File(numericsPath);
				FileInclusionReader firnum = new FileInclusionReader(fnum);
				numcontents = firnum.read();
			} else {
				numcontents = JUtil.getRelativeResource(new NumericsRoot(), "defaultNumerics.xml");
			}
				
			Sim simnum = new Sim(numcontents);

			simnum.readModel();

			Lems lemsnum = simnum.getLems();

			LemsCollection<IntegrationScheme> schemes = lemsnum.getIntegrationSchemes();

			IntegrationScheme euler = schemes.getByName("forwardEuler");

			DiscreteUpdateGenerator dug = new DiscreteUpdateGenerator(st, euler);

			DiscreteUpdateComponent dum = dug.buildDiscreteUpdateComponent();

			LemsLite ll = new LemsLite();
			ll.addDiscreteUpdateComponent(dum);
			
			
			String ret = XMLSerializer.serialize(ll);

			if (outPath == null) {
				E.info("discrete model:\n" + ret);
				
				
			} else {
				File fout = new File(outPath);
				FileUtil.writeStringToFile(ret, fout);
				E.info("Written discrete model to " + fout.getAbsolutePath());
			}

			
			LemsLiteValidator llv = new LemsLiteValidator();
			boolean bok = llv.checkXMLText(ret);
			if (bok) {
				E.info("Output validated OK against lems lite schema");
			} else {
				E.info("WARNING: output is not valid acording to lems lite schema");
			}
			
			return ret;
	}

}
