package org.lemsml.jlems.core.lite.run.component;

import java.util.HashMap;

import org.lemsml.jlems.core.run.DoublePointer;
import org.lemsml.jlems.core.run.RuntimeError;

public class Copy {

	
	String srcName;
	String varName;
	
	
	public Copy(String snm, String vnm) {
		srcName = snm;
		varName = vnm;
	}


	public void exec(HashMap<String, DoublePointer> variables) throws RuntimeError {
		double d = variables.get(srcName).get();
		variables.get(varName).set(d);
	}
	

}
