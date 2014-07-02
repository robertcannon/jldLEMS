package org.lemsml.jlems.core.type;

import org.lemsml.jlems.core.annotation.ModelElement;
import org.lemsml.jlems.core.annotation.ModelProperty;
import org.lemsml.jlems.core.sim.ContentError;

@ModelElement(info = "A port on a component that can send or receive events, depending on the direction speicfied.")

public class SendPort implements Named {

    @ModelProperty(info = "")
    public String name;
  
    public String description;
    
   

    public SendPort() {
    	// TODO - only one constructor
    }

    public SendPort(String sn) {
        name = sn;
    }
  

    @Override
    public String toString() {
        String sret = "SendPort " + name + " ";
        
        return sret;
    }

    public void resolve(LemsCollection<Dimension> dimensions) throws ContentError {
    	// nothing to do
    }

    public String getName() {
        return name;
    }
  
    public SendPort makeCopy() {
        return new SendPort(name);
    }

  
}
