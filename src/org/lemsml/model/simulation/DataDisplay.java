package org.lemsml.model.simulation;

public class DataDisplay extends AbstractSimulationElement {

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
