package org.lemsml.jld.imodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IComponent {

	IComponentType getIComponentType();

	String getId();

 
	IComponent getIChild(String fieldName);

	double getSIParameterValue(String qn);

	String getTextParameterValue(String tnm);

	List<String> getChildNames();

	List<String> getChildrenNames();

	List<? extends IComponent> getIChildren(String s);
}
