package org.lemsml.jld.validation;

import org.lemsml.jld.model.Lems;

public class ConsistencyCheck {

	private Lems lems;
	
	
	public ConsistencyCheck(Lems lems) {
		this.lems = lems;
	}


	public void checkExpressions() {
		 DimensionsCheck dc = new DimensionsCheck(lems);
		 dc.checkExpressions();
	}


	public void checkPaths() {
		// TODO Auto-generated method stub
		
	}


	

}
