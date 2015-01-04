package org.lemsml.jld.model.simulation;

public class Run extends AbstractSimulationElement {

	protected String component;
	
	protected String increment;

	protected String total;
	
	protected String variable;
	
	
	public Run(Simulation sim, String eltname) {
		super(sim, eltname);
	}



	public String getComponent() {
		return component;
	}



	public void setComponent(String s) {
		this.component = s;
	}



	public String getIncrement() {
		return increment;
	}



	public void setIncrement(String s) {
		this.increment = s;
	}



	public String getTotal() {
		return total;
	}



	public void setTotal(String t) {
		this.total = t;
	}



	public String getVariable() {
		return variable;
	}



	public void setVariable(String variable) {
		this.variable = variable;
	}

}
