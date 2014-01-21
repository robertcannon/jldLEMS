package org.lemsml.jlems.io.out;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.lemsml.jlems.core.display.DataViewer;
import org.lemsml.jlems.core.lite.simulation.InstanceWriter;
import org.lemsml.jlems.core.lite.simulation.RecWriter;
import org.lemsml.jlems.core.run.RuntimeError;

public class RecFileWriter extends RecWriter {
	
	String id;
	String fileName;
	String format;
	
	ArrayList<InstanceWriter> writers = new ArrayList<InstanceWriter>();
	
	BufferedWriter bw;
	
 
	public RecFileWriter(String fid, String fnm, String fmt) {
		id = fid;
		fileName = fnm;
		format = fmt;
	}
	

	public void addWriter(InstanceWriter iw) {
		 writers.add(iw);
	}


	public void write(double t) throws RuntimeError {
		try {
			if (bw == null) {
				File f = new File(fileName);
				bw = new BufferedWriter(new FileWriter(f));
			}
		 
			StringBuilder sb = new StringBuilder();
			sb.append("" + t + " ");
			for (InstanceWriter iw : writers) {
				sb.append(iw.getString());
				sb.append(" ");
			}
			sb.append("\n");
			bw.write(sb.toString());
			
		
		} catch (IOException ex) {
			throw new RuntimeError("Ca't writer to file " + fileName, ex);
		}
	}
 

	public void close() throws RuntimeError {
		try {
		if (bw != null) {
			bw.close();
		}
		} catch (IOException ex) {
			throw new RuntimeError("Ca't writer to file " + fileName, ex);
		}
	}


	 
	
}
