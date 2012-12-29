package org.lemsml.jlems.run;

public class RuntimeOutput {

	String id;
 
	String path;
	
	String fileName;
	

	public RuntimeOutput() {
		
	}
	
	
	public String toString() {
		return "RuntimeOutput, id=" + id + " file=" + fileName;
	}
	

	public String getID() {
		return id;
	}
	
	 
	
	public void setID(String sid) {
		id = sid;
	}
	
	public void setPath(String s) {
		path = s;
	}

	public void setFileName(String s) {
		fileName = s;
	}


	public String getPath() {
		return path;
	}
	 
	public String getFileName() {
		return fileName;
	}

}
