package org.lemsml.jlems.io.data;

import java.io.File;

import org.lemsml.jlems.core.lite.simulation.DataSource;


public class FileDataSource extends DataSource {

	File rootDir;
	
	
	public FileDataSource(File f) {
		rootDir = f;
	}
	
	
	@Override
	public double[][] readDataArray(String fileName) {
		File fdat = new File(rootDir, fileName);
		
		double[][] ret= FormattedDataUtil.readDataArray(fdat);
		
		return ret;
	}

}
