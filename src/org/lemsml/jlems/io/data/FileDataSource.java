package org.lemsml.jlems.io.data;

import java.io.File;

import org.lemsml.jlems.core.lite.simulation.AbstractDataSource;


public class FileDataSource extends AbstractDataSource {

	File rootDir;
	
	
	public FileDataSource(File f) {
		super();
		rootDir = f;
	}
	
	
	@Override
	public double[][] readDataArray(String fileName) {
		File fdat = new File(rootDir, fileName);
		
		double[][] ret= FormattedDataUtil.readDataArray(fdat);
		
		return ret;
	}

}
