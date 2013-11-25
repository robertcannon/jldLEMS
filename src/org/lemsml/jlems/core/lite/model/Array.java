package org.lemsml.jlems.core.lite.model;

import org.lemsml.jlems.core.type.LemsCollection;

public class Array {

	public String name;
	
	public LemsCollection<FileSource> fileSources = new LemsCollection<FileSource>();

	
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

	public String getName() {
		return name;
	}
	
	
}
