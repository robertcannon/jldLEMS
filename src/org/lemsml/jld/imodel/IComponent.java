package org.lemsml.jld.imodel;

import java.util.List;

public interface IComponent {

	String getId();

	void setId(String autoID);

	
	IComponent getParent();

	IComponentType getIComponentType();

	
	
	double getNumericalParameterValue(String qn);

	String getStringParameterValue(String tnm);
 
	
	
	List<String> getChildNames();

	IComponent getIChild(String fieldName);

	List<String> getChildrenNames();

	List<? extends IComponent> getIChildren(String s);



}
