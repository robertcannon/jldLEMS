package org.lemsml.jlems.core.lite.model;

import org.lemsml.jlems.core.logging.E;

public class FromArrayConnector {

	
	public String pre;

	public String post;

	
	double delay;
	
	double weight;
	
	
	
	public String getPre() {
		return pre;
	}
	
	public String getPost() {
		return post;
	}

	public void setDelay(double d) {
		 delay = d;
	}
	
	public void setWeight(double d) {
		weight = d;
	}

	public void setCustomProperty(String name, double value) {
		
		E.missing();
		
	}
	
	
}
