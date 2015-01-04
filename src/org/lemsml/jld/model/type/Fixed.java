package org.lemsml.jld.model.type;

public class Fixed extends AbstractField {

	
	protected String parameter;
	
	protected String value;
	
	
	
	protected Fixed(ComponentType ct, String n) {
		super(ct, n);
	}
	
	
	public String toString() {
		return "Fixed";
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	
	
}
