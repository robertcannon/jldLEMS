package org.lemsml.jlems.core.lite;

import org.lemsml.jlems.core.display.DataViewer;

public class InstanceWriter {

	InstanceArray srcArray;
	int[] indices;
	String varname;
	
	
	public InstanceWriter(InstanceArray arr, String vnm, int[] inds) {
		srcArray = arr;
		varname = vnm;
		indices = inds;
		
	}


	public String getString() {
		return srcArray.getElementValues(varname, indices);
	}


	public void display(DataViewer dv, double t, String col) {
		 srcArray.display(dv, varname, indices, t, col);
		
	}

}
