package org.lemsml.jld.model;

import java.util.List;

import org.lemsml.jld.model.core.AbstractElement;
import org.lemsml.jld.model.core.ListMap;


public class Component {

	protected String element;   // "Component", or the type ("iaf"), or the role in the parent ("Forward")

	protected String id;
	protected String type;
	protected String eXtends;
	
	protected ListMap<ParameterValue> parameterValueMap = new ListMap<ParameterValue>();
	
	protected ListMap<Component> componentMap = new ListMap<Component>();
	
	// the only use for this is when re-writing a model in the same style as it was read 
 	
	
	
	protected Component(String id) {
		this.id = id;
	}
	 
	public void setType(String type) {
		this.type = type;
	}
	
	public void setElement(String element) {
		this.element = element;
	}
	
	
	public void setParameterValue(String sn, String sv) {
		ParameterValue pv = new ParameterValue(sn, sv);
		parameterValueMap.put(sn,  pv);
	}


	public void setParameterValue(String sn, Quantity q) {
		// TODO Auto-generated method stub
		
	}

 

	public Component addComponent(String id) {
		Component ret = new Component(id);
		componentMap.put(id,  ret);
		return ret;
	}


	public String getType() {
		return type;
	}
	
	public String getId() {
		return id;
	}
	
	public String getElement() {
		return element;
	}


	public List<ParameterValue> getParameterValues() {
		 return parameterValueMap.getItems();
	}


	public List<Component> getComponents() {
		return componentMap.getItems();
	}

 
	public void setExtends(String s) {
		eXtends = s;
	}
	
	public String getExtends() {
		return eXtends;
	}
	
}
