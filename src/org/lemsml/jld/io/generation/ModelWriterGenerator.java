package org.lemsml.jld.io.generation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.lemsml.jld.io.E;
import org.lemsml.jld.io.reader.FileUtil;
import org.lemsml.jld.model.core.ComponentMap;
import org.lemsml.jld.model.core.ListMap;
import org.lemsml.jld.model.core.Single;
 
public class ModelWriterGenerator {

	 
	

	private String generateJava() {
		LemsClasses lclasses = LemsClasses.getInstance();
		
		ArrayList<LemsClass> alc = lclasses.getClasses();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("package org.lemsml.jld.io.writer;\n\n");
		sb.append("import org.lemsml.jld.io.xml.*;\n\n");

		sb.append("import org.lemsml.jld.api.*;\n");
		sb.append("import org.lemsml.jld.model.*;\n");
		sb.append("import org.lemsml.jld.model.core.*;\n");
		sb.append("import org.lemsml.jld.model.type.*;\n");
		sb.append("import org.lemsml.jld.model.dynamics.*;\n");
		sb.append("import org.lemsml.jld.model.structure.*;\n");
		sb.append("import org.lemsml.jld.model.simulation.*;\n\n");
		 
	
		sb.append("// NB this is generated code. Don't edit it. If there is a problem, fix the superclass,\n");
		sb.append("// the generator - org.lemsml.jld.generation.ModelFactoryGenerator, or the class being instantiated.\n\n");
		sb.append("public class GeneratedModelWriter extends AbstractModelWriter {\n\n\n");
	
			 	
		for (LemsClass lc : alc) {
			Class c = lc.jclass;
			appendWriter(c, sb);
		}
	  
		
		sb.append("}\n");
		
		return sb.toString();
		
	}
	
	
	
	
	
	
	private void appendWriter(Class<?> c, StringBuilder sb) {
		
		String cnm = c.getSimpleName();

		sb.append("    public XMLElement write" + cnm +"ToXML(" + cnm + " tgt) throws APIException, ModelException {\n");		
		sb.append("       XMLElement ret = new XMLElement(\"" + cnm + "\");\n");
 
		
		for (Field f : getProtectedFields(c)) {					
			 
			String fnm = f.getName();
 			
			String mnm = fnm;
			
			if (fnm.equals("eXtends")) {
				mnm = "extends";
			}
				
			if (f.getType() == String.class) {
				sb.append("      String " + fnm + " = tgt.get" + capitalize(mnm) + "();\n");
				sb.append("      if (" + fnm + " != null) {\n");
				sb.append("          ret.addAttribute(\"" + mnm + "\", " + fnm + ");\n");
				sb.append("      }\n");
			
			} else if (f.getType() == double.class) {
				sb.append("             addDoubleAttribute(ret, \"" + fnm + "\", tgt.get" + capitalize(fnm) + "());\n");
					
			} else if (f.getType() == int.class) {
				sb.append("             addIntAttribute(ret, \"" + fnm + "\", tgt.get" + capitalize(fnm) + "());\n");
				
			} else if (f.getType() == boolean.class) {
				sb.append("             addBooleanAttribute(ret, \"" + fnm + "\", tgt.get" + capitalize(fnm) + "());\n");
			
			} else if (f.getType() == ListMap.class) {
				String lcnm = getListClassName(f.getName());
				sb.append("       for (" + lcnm + " x : tgt.get" + lcnm + "s" + "()) {\n");
				sb.append("          ret.addXMLElement(write" + lcnm + "ToXML(x));\n");
				sb.append("       }\n");
				
				
				
			}  else if (f.getType() == Single.class) {
				String lcnm = getSingleClassName(f.getName());
				String vnm = lc(lcnm);
				sb.append("      " + lcnm + " " + vnm + " = tgt.get" + lcnm + "();\n");
				sb.append("       if (" + vnm + " != null) {\n");
				sb.append("          ret.addXMLElement(write" + lcnm + "ToXML(" + vnm + "));\n");
				sb.append("       }\n");
				
			} else if (f.getType() == ComponentMap.class) {
				sb.append("      writeComponentsToXML(tgt, ret);\n");
			}
		}
		sb.append("       return ret;\n");
		sb.append("    }\n\n\n");
	}
	
	
	
	private String lc(String s) {
		String ret = s.substring(0, 1).toLowerCase() + s.substring(1, s.length());
		return ret;
	}
	
	
	private String capitalize(String s) {
		String ret = s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
		return ret;
	}
	
	

	protected String getListClassName(String s) {
		String ret = s;
		
		if (ret.endsWith("Map")) {
			ret = ret.substring(0, ret.length() - 3);
		}
		ret = capitalize(ret);
	 
		return ret;
	}
	
	protected String getSingleClassName(String s) {
		String ret = s;
		
		if (ret.endsWith("Single")) {
			ret = ret.substring(0, ret.length() - 6);
		}
		ret = capitalize(ret);
	 
		return ret;
	}
	
	 
	
	
	
	 
	
	private ArrayList<Field> getProtectedFields(Class<?> c) {
		ArrayList<Field> ret = new ArrayList<Field>();
		Class csup = c.getSuperclass();
		if (csup != null) {
			ret.addAll(getProtectedFields(csup));
		}
		for (Field f : c.getDeclaredFields()) {
			if (Modifier.isProtected(f.getModifiers())) {
				if (Modifier.isStatic(f.getModifiers())) {
					
				} else {
					ret.add(f);
				}
			}
		}
		 
		return ret;
	}






	public static void main(String[] argv) throws IOException {
		ModelWriterGenerator lfg = new ModelWriterGenerator();
		
		String txt = lfg.generateJava();
 		
		File f = new File("src/org/lemsml/jld/io/writer/GeneratedModelWriter.java");
	 
		// E.info("About to write " + f.getAbsolutePath() + " local "+ (new File("")).getAbsolutePath());
		
		FileUtil.writeStringToFile(txt, f);
		E.info("Written " + f.getAbsolutePath());
		 
	}

}
