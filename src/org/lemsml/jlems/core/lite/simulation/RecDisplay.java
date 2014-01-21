package org.lemsml.jlems.core.lite.simulation;
 
 
import java.util.ArrayList;

import org.lemsml.jlems.core.display.DataViewer;
 
public class RecDisplay {
  

	String id;
 	ArrayList<InstanceWriter> writers = new ArrayList<InstanceWriter>();
	 
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
