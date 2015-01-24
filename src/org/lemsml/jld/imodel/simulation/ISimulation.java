package org.lemsml.jld.imodel.simulation;

import java.util.List;

public interface ISimulation {

	List<? extends IRun> getIRuns();

	List<? extends IDataDisplay> getIDataDisplays();

	List<? extends IDataWriter> getIDataWriters();

	List<? extends IRecord> getIRecords();

}
