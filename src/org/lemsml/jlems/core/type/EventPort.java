package org.lemsml.jlems.core.type;

import org.lemsml.jlems.core.annotation.ModelElement;
import org.lemsml.jlems.core.annotation.ModelProperty;
import org.lemsml.jlems.core.sim.ContentError;

@ModelElement(info = "A port on a component that can send or receive events, depending on the direction speicfied.")

public class EventPort implements Named, DeprecatedElement {

    @ModelProperty(info = "")
    public String name;
  
    public String description;
    
    public String direction;
   

    public EventPort() {
    	// TODO - only one constructor
    }

    public EventPort(String sn) {
        name = sn;
    }
  

    public Object getReplacement() {
    	Object ret = null;
    	if (direction != null) {
    		if (direction.equals("out")) {
    			SendPort sp = new SendPort();
    			sp.name = name;
    			ret = sp;
    		} else if (direction.equals("in")) {
    			ReceivePort rp = new ReceivePort();
    			rp.name = name;
    			ret = rp;
    		}
    	}
    	return ret;
    }
    
    
    @Override
    public String toString() {
        String sret = "EventPort " + name + " ";
        
        return sret;
    }
 

    public String getName() {
        return name;
    }
  
    
  
}
