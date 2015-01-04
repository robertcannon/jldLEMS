package org.lemsml.jld.model.structure;

public class MultiInstance extends AbstractStructureElement {

	protected String number;
	protected String component;
	
	
	public MultiInstance(AbstractStructureBlock b) {
		super(b);
 	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getComponent() {
		return component;
	}


	public void setComponent(String component) {
		this.component = component;
	}

}
