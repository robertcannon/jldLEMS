package org.lemsml.model;


public class Component extends AbstractElement {

	String typeName;
	
	
	public Component(Lems l, String sid) {
		super(l, sid);
	}
	
	public void setTypeName(String tn) {
		typeName = tn;
	}

	public void setParameterValue(String string, String string2) {
		// TODO Auto-generated method stub
		
	}

	public void setParameterValue(String string, Quantity quantity) {
		// TODO Auto-generated method stub
		
	}
	
}
