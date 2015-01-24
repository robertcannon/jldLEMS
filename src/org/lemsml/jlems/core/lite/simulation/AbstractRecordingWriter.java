package org.lemsml.jlems.core.lite.simulation;
 
import java.util.ArrayList;

import org.lemsml.jlems.core.run.RuntimeError;

public abstract class AbstractRecordingWriter {
 
	
	ArrayList<InstanceWriter> writers = new ArrayList<InstanceWriter>();
	 
	public void addWriter(InstanceWriter iw) {
		 writers.add(iw);
	}


	public abstract void write(double t) throws RuntimeError;
	 
 

	public abstract void close() throws RuntimeError;
	 


	 
	
}
