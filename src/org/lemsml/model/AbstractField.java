package org.lemsml.model;


public class AbstractField extends AbstractElement {
	
	protected String dimension = null;
	
	private Dimension r_dimension = null;
	
	
	protected AbstractField(Lems l, String n) {
		super(l, n);
	}
	
	public void setDimension(Dimension d) {
		r_dimension = d;
	}
	
	public void setDimension(String n) {
		dimension = n;
	}
	
}
