package org.lemsml.jlems.core.type;

import org.lemsml.jlems.core.annotation.ModelElement;
import org.lemsml.jlems.core.annotation.ModelProperty;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.sim.ContentError;

@ModelElement(info = "Allows local declaration of parameters not defined by the type for use with individual instances")
public class LocalParameters implements Named {
    
	@ModelProperty(info="The name of the group of local parameters.")
	public String name;
	
	 
 
  

    public void resolve(LemsCollection<Dimension> dimensions) throws ContentError {
    
       
    }

    public String getName() {
        return name;
    }

    
	protected void setName(String s) {
		 name = s;
	}
 
     
}
