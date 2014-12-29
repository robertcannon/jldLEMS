package org.lemsml.model.simulation;

public class Recording extends AbstractSimulationElement {

	protected String quantity;
	
	protected String timeScale;
	
	protected String scale;
	
	protected String color;
	
	
	public Recording(Simulation sim, String eltname) {
		super(sim, eltname);
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public String getTimeScale() {
		return timeScale;
	}


	public void setTimeScale(String timeScale) {
		this.timeScale = timeScale;
	}


	public String getScale() {
		return scale;
	}


	public void setScale(String scale) {
		this.scale = scale;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}

}
