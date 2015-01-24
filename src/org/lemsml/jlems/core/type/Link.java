package org.lemsml.jlems.core.type;

import org.lemsml.jlems.core.annotation.ModelElement;
import org.lemsml.jlems.core.annotation.ModelProperty;


@ModelElement(info="A reference to another component. The target component can be accessed with path expressions in the " +
		"same way as a child component, but can be defined independently")
public class Link implements Named, DeprecatedElement  {

	@ModelProperty(info="")
	public String name;

	@ModelProperty(info="Target type")
	public String type;
	public ComponentType r_type;
	
	 

	  

	public String getName() {
		return name;
	}

 

	@Override
	public Object getReplacement() {
		ComponentReference ret = new ComponentReference();
		ret.name = name;
		ret.type = type;
		ret.local = true;
		return ret;
	}

 
	 


 
}
