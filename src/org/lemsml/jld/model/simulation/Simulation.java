package org.lemsml.jld.model.simulation;

import java.util.List;

import org.lemsml.jld.imodel.simulation.IDataDisplay;
import org.lemsml.jld.imodel.simulation.IDataWriter;
import org.lemsml.jld.imodel.simulation.IRecord;
import org.lemsml.jld.imodel.simulation.IRun;
import org.lemsml.jld.imodel.simulation.ISimulation;
import org.lemsml.jld.model.core.TypeElement;
import org.lemsml.jld.model.core.ListMap;
import org.lemsml.jld.model.type.ComponentType;

public class Simulation extends TypeElement implements ISimulation {

	protected ListMap<DataDisplay> dataDisplayMap = new ListMap<DataDisplay>();
	
	protected ListMap<DataWriter> dataWriterMap = new ListMap<DataWriter>();
	
	protected ListMap<Recording> recordingMap = new ListMap<Recording>();
	
	protected ListMap<Run> runMap = new ListMap<Run>();
	
	
	
	protected Simulation(ComponentType ct) {
		super(ct, null);
	}

	public String toString() {
		return "Simulation";
	}


	public List<DataDisplay> getDataDisplays() {
		return dataDisplayMap.getItems();
	}
	
	public List<DataWriter> getDataWriters() {
		return dataWriterMap.getItems();
	}
	
	public List<Recording> getRecordings() {
		return recordingMap.getItems();
	}
	
	@Override
	public List<? extends IRun> getIRuns() {
		return runMap.getItems();
	}
	
	
	@Override
	public List<? extends IDataDisplay> getIDataDisplays() {
		return dataDisplayMap.getItems();
	}
	
	@Override
	public List<? extends IDataWriter> getIDataWriters() {
		return dataWriterMap.getItems();
	}
	
	@Override
	public List<? extends IRecord> getIRecords() {
		return recordingMap.getItems();
	}
	
	
	public List<Run> getRuns() {
		return runMap.getItems();
	}


	public DataDisplay addDataDisplay(String eltname) {
		DataDisplay dd = new DataDisplay(this, eltname);
		dataDisplayMap.put(eltname, dd);
		return dd;
	}



	public DataWriter addDataWriter(String eltname) {
		DataWriter dw = new DataWriter(this, eltname);
		dataWriterMap.put(eltname, dw);
		return dw;
	}
	
	

	public Recording addRecording(String eltname) {
		Recording r = new Recording(this, eltname);
		recordingMap.put(eltname, r);
		return r;
	}
	
	

	public Run addRun(String eltname) {
		Run r = new Run(this, eltname);
		runMap.put(eltname, r);
		return r;
	}

 
	
}
