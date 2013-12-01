package org.lemsml.jlems.core.lite.model;

import org.lemsml.jlems.core.type.LemsCollection;

public class OutputFiles {

	public LemsCollection<File> files = new LemsCollection<File>();

	public LemsCollection<File> getFiles() {
		return files;
	}
	
}
