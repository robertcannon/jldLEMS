package org.lemsml.jld.model.simulation;

import org.lemsml.jld.imodel.simulation.IDataWriter;

public class DataWriter extends AbstractSimulationElement implements IDataWriter {

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
