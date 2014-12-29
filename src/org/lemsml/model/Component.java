package org.lemsml.model;

import java.util.List;

import org.lemsml.model.core.AbstractElement;
import org.lemsml.model.core.ListMap;


public class Component {

	protected String id;
	protected String typeName;
	
	protected ListMap<ParameterValue> parameterValueMap = new ListMap<ParameterValue>();
	
	protected ListMap<Component> componentMap = new ListMap<Component>();
	
	
	
	protected Component(String s, String tn) {
		id = s;
		typeName = tn;
	}
 
	
	public void setParameterValue(String sn, String sv) {
		ParameterValue pv = new ParameterValue(sn, sv);
		parameterValueMap.put(sn,  pv);
	}


	public void setParameterValue(String sn, Quantity q) {
		// TODO Auto-generated method stub
		
	}


	public void setExtends(String value) {
		// TODO Auto-generated method stub
		
	}

	public Component addComponent(String id, String tn) {
		Component ret = new Component(id, tn);
		componentMap.put(id,  ret);
		return ret;
	}


	public String getType() {
		return typeName;
	}
	
	public String getId() {
		return id;
	}


	public List<ParameterValue> getParameterValues() {
		 return parameterValueMap.getItems();
	}


	public List<Component> getComponents() {
		return componentMap.getItems();
	}
	
}
