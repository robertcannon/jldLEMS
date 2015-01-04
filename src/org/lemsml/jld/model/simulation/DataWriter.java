package org.lemsml.jld.model.simulation;

public class DataWriter extends AbstractSimulationElement {

	protected String path;
	
	protected String fileName;
	
	
	public DataWriter(Simulation sim, String eltname) {
		super(sim, eltname);
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
