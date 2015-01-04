package org.lemsml.jld.model.structure;

public class ForEach extends AbstractStructureBlock {
 	
	protected String instances;
	protected String as;
	

	public ForEach(AbstractStructureBlock b) {
		super(b);
	}

	public String getInstances() {
		return instances;
	}


	public void setInstances(String instances) {
		this.instances = instances;
	}


	public String getAs() {
		return as;
	}


	public void setAs(String as) {
		this.as = as;
	}


}
