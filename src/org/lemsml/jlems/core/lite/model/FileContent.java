package org.lemsml.jlems.core.lite.model;

import java.io.File;

import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.io.data.FormattedDataUtil;

public class FileContent {
	
	File rootDir;
	
	String id;
	
	String fileName;

	String format;
	
	String shape;
	
	double[][] rows = null;
	
	
	public FileContent(String aid, File rd) {
		id = aid;
		rootDir = rd;
	}


	public void setFileName(String s) {
		 fileName = s;
	}


	public void setFormat(String s) {
		format = s;
	}
	
	public void setShape(String s) {
		shape = s;
	}


	
	public double[] getDoubleArrayColumn(int column) throws ContentError {
	 	
		if (rows == null) {
 			readData();
 		}
		
		double[] ret = new double[rows.length];
		
		if (rows.length > 0 && rows[0].length > column) {
			for (int i = 0; i < rows.length; i++) {
				ret[i] = rows[i][column];
			}
		
		} else {
			throw new ContentError("Not enough columns to get column " + column +
					" from " + fileName);
		}
		return ret;
	}
	

	// TODO IO dependencies elsewhere
	private void readData() {
		File fdat=  new File(rootDir, fileName);
		rows = FormattedDataUtil.readDataArray(fdat);
	}
	
	
}
