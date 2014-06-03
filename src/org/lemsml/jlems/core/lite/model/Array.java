package org.lemsml.jlems.core.lite.model;

import org.lemsml.jlems.core.type.LemsCollection;

public class Array {

	public String name;
	
	public boolean integer = false;
	
	public LemsCollection<FileSource> fileSources = new LemsCollection<FileSource>();

	public LemsCollection<ListSource> listSources = new LemsCollection<ListSource>();
	
	
	public boolean hasFileSource() {
		boolean ret = false;
		if (fileSources.size() > 0) {
			ret = true;
		}
		return ret;
	}
	
	 
	public FileSource getFileSource() {
		return fileSources.get(0);
	}

	
	public boolean hasIntListSource() {
		boolean ret = false;
		if (listSources.size() > 0) {
			ret = true;
		}
		return ret;
	}
	
	public int[] getIntArray() {
		return listSources.get(0).getValues();
	}
		
	
	public String getName() {
		return name;
	}


	public boolean isInteger() {
		return integer;
	}
	
	
}
