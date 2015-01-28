package org.lemsml.jld.model.structure;

import org.lemsml.jld.imodel.structure.IMultiInstance;

public class MultiInstance extends AbstractStructureElement implements IMultiInstance {

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


	public String getIndexVariable() {
		// TODO Auto-generated method stub
		return null;
	}

}
