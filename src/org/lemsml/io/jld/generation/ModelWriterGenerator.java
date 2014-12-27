package org.lemsml.io.jld.generation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

import org.lemsml.io.jld.E;
import org.lemsml.io.jld.reader.FileUtil;
import org.lemsml.model.ComponentMap;
import org.lemsml.model.ListMap;
import org.lemsml.model.Single;
 
public class ModelWriterGenerator {

	 
	

	private String generateJava() {
		LemsClasses lclasses = LemsClasses.getInstance();
		
		ArrayList<LemsClass> alc = lclasses.getClasses();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("package org.lemsml.io.jld.writer;\n\n");
		sb.append("import org.lemsml.io.jld.xml*;\n\n");

		sb.append("import org.lemsml.api.*;\n");
		sb.append("import org.lemsml.model.*;\n");
		sb.append("import org.lemsml.model.dynamics.*;\n");
		sb.append("import org.lemsml.model.structure.*;\n");
		sb.append("import org.lemsml.model.simulation.*;\n\n");
		 
	
		sb.append("// NB this is generated code. Don't edit it. If there is a problem, fix the superclass,\n");
		sb.append("// the generator - org.lemsml.jld.generation.ModelFactoryGenerator, or the class being instantiated.\n\n");
		sb.append("public class ModelWriter extends AbstractModelWriter {\n\n\n");
	
			 	
		for (LemsClass lc : alc) {
			Class c = lc.jclass;
			appendWriter(c, sb);
		}
	  
		
		sb.append("}\n");
		
		return sb.toString();
		
	}
	
	
	
	
	
	
	private void appendWriter(Class<?> c, StringBuilder sb) {
		
		String cnm = c.getSimpleName();

		sb.append("    public XMLElement write" + cnm +"toXML(" + cnm + " tgt) throws APIException, ModelException {\n");		
		sb.append("       XMLElement ret = new XMLElement(" + cnm + ");\n");
 
		
		for (Field f : getProtectedFields(c)) {					
			 
			String fnm = f.getName();
			String exfn = null;

				
			if (f.getType() == String.class) {
				sb.append("      String " + fnm + " = tgt.get" + capitalize(fnm) + "();\n");
				sb.append("      if (" + fnm + " != null) {\n");
				sb.append("             ret.addAttribute(" + fnm + ", fnm);\n");
				sb.append("       }\n");
			
			} else if (f.getType() == double.class || f.getType() == int.class || f.getType() == boolean.class) {
				sb.append("             ret.addAttribute(" + fnm + ", get" + capitalize(fnm) + "();\n");
			
			} else if (f.getType() == ListMap.class) {
				String lcnm = getListClassName(f.getName());
				
				
				
			}  else if (f.getType() == Single.class) {
				String lcnm = getSingleClassName(f.getName());
				sb.append("      " + lcnm + " " + lc(lcnm) + " = tgt.get" + lcnm + "();\n";
				sb.append("       if (" + lcnm + " != null) {\n");
				sb.append("          ret.addElement(write")
				sb.append("       }\n");
				
			}
		}
		sb.append("            }\n");
		sb.append("        }\n\n\n");
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
	
	private boolean hasFields(Class<?> c, Class tgt) {
		boolean ret = false;
		for (Field f : c.getDeclaredFields()) {
			if (Modifier.isProtected(f.getModifiers())) {
				if (f.getType() == tgt) {
					ret = true;
					break;
				}
			}
		}
		return ret;
	}
	
	 
	
	
	
	
	private void appendChildInstantiation(Class<?> c, StringBuilder sb) {
		sb.append("        for (XMLElement cel : xel.getXMLElements()) {\n");
		sb.append("            String xn = cel.getTag();\n\n");
	
		sb.append("            if (xn.equals(\"UNUSED\")) {\n");
	
		String cnm = c.getSimpleName();
		
		boolean hasModel = false;
		
		for (Field f : getProtectedFields(c)) {
			if (f.getType() == ListMap.class) {
				
				String ccnm = getListClassName(f.getName());
				
				sb.append("            } else if (xn.equals(\"" + ccnm + "\")) {\n");
				sb.append("                String eltname = getNameAttribute(cel);\n");
				sb.append("                " + ccnm + " obj = tgt.add" + ccnm + "(eltname);\n");
				sb.append("                populate" + ccnm + "FromXMLElement(obj, cel);\n");
				
			} else if (f.getType() == Single.class) {
				String ccnm = getSingleClassName(f.getName());
				
				sb.append("            } else if (xn.equals(\"" + ccnm + "\")) {\n");
				sb.append("                " + ccnm + " obj = tgt.add" + ccnm + "();\n");
				sb.append("                populate" + ccnm + "FromXMLElement(obj, cel);\n");
		
				
			} else if (f.getType() == ComponentMap.class) {
				hasModel = true;
			}
		}	
 
		if (hasModel) {
			sb.append("            } else {\n");
			sb.append("                readComponentFromXMLElement(tgt, cel);\n");
			sb.append("            }\n");
		
		} else {
		
			sb.append("            } else {\n");
			sb.append("                E.warning(\"reading " + cnm + ": unrecognized element \" + cel);\n");
			sb.append("            }\n");
		}
		sb.append("        }\n\n\n");
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
 		
		File f = new File("src/org/lemsml/io/jld/writer/ModelWriter.java");
	 
		// E.info("About to write " + f.getAbsolutePath() + " local "+ (new File("")).getAbsolutePath());
		
		FileUtil.writeStringToFile(txt, f);
		E.info("Written " + f.getAbsolutePath());
		 
	}

}
