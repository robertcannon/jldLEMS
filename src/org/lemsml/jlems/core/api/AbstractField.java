package org.lemsml.jlems.core.api;

public class AbstractField extends AbstractElement {
	
	private String dimensionName = null;
	
	protected Dimension dimension = null;
	
	
	protected AbstractField(Lems l, String n) {
		super(l, n);
	}
	
	public void setDimension(Dimension d) {
		dimension = d;
	}
	
	public void setDimension(String n) {
		dimensionName = n;
	}
	
}
