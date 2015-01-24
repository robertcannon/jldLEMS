package org.lemsml.jld.model.simulation;

import org.lemsml.jld.imodel.simulation.IDataDisplay;
 
public class DataDisplay extends AbstractSimulationElement implements IDataDisplay {

	protected String title;
	 
	protected String dataRegion;
	
	
	
	public DataDisplay(Simulation sim, String eltname) {
		super(sim, eltname);
	}
	
	
	
	
	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}




	public String getDataRegion() {
		return dataRegion;
	}




	public void setDataRegion(String dataRegion) {
		this.dataRegion = dataRegion;
	}


 


}
