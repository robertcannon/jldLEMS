package org.lemsml.jlems.core.run;

import java.util.ArrayList;
import java.util.HashMap;

import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.sim.RunnableAccessor;
import org.lemsml.jlems.core.type.Component;

public class WithListItemBuilder extends AbstractPostBuilder {

	String listPath;
	int idx;
	String var;
	
    public WithListItemBuilder(String lp, int n, String as) {
        super();
    	listPath = lp;
        idx = n;
    	var = as;
    }

    public void postBuild(StateRunnable base, HashMap<String, StateRunnable> siHM, BuildContext bc) throws ConnectionError, ContentError {
        RunnableAccessor ra = new RunnableAccessor(base);


        StateRunnable sr = null;
        
        
        //E.info("WithBuilder needs "+path+" relative to: "+base);
       
        ArrayList<StateRunnable> srs = ra.getRelativeStateInstances(base.getParent(), listPath);
         
        E.info("WLIB: " + listPath + " for var " +var +  " " + srs);
        
        if (srs.size() > idx) {
        	sr = srs.get(idx);
        } else {
        	throw new ConnectionError("out of range: list has " + srs.size() +
        			" elements but With refers to item " + idx);
        }
        
        //E.info("WithBuilder path "+path+" ( = "+var+") resolved to: "+sr);

        E.info("wlib building list=" + srs + "  item=" + sr);
        
        siHM.put(var, (StateInstance) sr);
    }

	@Override
	public void consolidateStateTypes() {
		// TODO Auto-generated method stub
		
	}

 
 

}
