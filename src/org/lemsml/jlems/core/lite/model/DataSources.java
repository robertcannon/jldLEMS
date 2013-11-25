package org.lemsml.jlems.core.lite.model;

import org.lemsml.jlems.core.type.LemsCollection;

public class DataSources {

	
	public LemsCollection<File> files = new LemsCollection<File>();
	
	public LemsCollection<Array> arrays = new LemsCollection<Array>();

	public LemsCollection<File> getFiles() {
		 return files;
	}
	
	public LemsCollection<Array> getArrays() {
		return arrays;
	}
	
}
