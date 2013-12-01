package org.lemsml.jlems.core.lite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.lemsml.jlems.core.display.DataViewer;
import org.lemsml.jlems.core.run.RuntimeError;

public class RecDisplay {
  

	String id;
 	ArrayList<InstanceWriter> writers = new ArrayList<InstanceWriter>();
	
	BufferedWriter bw;
	
	DataViewer dataViewer;
	
	String color;
	
	public RecDisplay(String fid, DataViewer dv, String col) {
		id = fid;
		dataViewer = dv;
		color = col;
	}


	public void addWriter(InstanceWriter iw) {
		 writers.add(iw);
	}


	 

	public void display(double t) {
		 for (InstanceWriter w : writers) {
			 w.display(dataViewer, t, color);
		 }
	}
	
}
