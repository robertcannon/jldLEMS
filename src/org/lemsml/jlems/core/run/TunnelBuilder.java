package org.lemsml.jlems.core.run;

import java.util.ArrayList;
import java.util.HashMap;

import org.lemsml.jlems.core.eval.DoubleEvaluator;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.sim.ContentError;

public class TunnelBuilder extends AbstractPostBuilder {

	String from;
	String to;
	String expose;
	String as;
	
	public TunnelBuilder(String sf, String st, String exp, String a) {
		super();
		from = sf;
		to = st;
		expose = exp;
		as = a;
	}

	 
	public void postBuild(StateRunnable base, HashMap<String, StateRunnable> sihm, BuildContext bc) throws ConnectionError, ContentError, RuntimeError {
  		StateRunnable sf = sihm.get(from);
		StateRunnable st = sihm.get(to);
		
		E.missing(" time to build a tunnel:\n  " + sf + ";\n " + st + ";\n " + bc + ";\n " + base);
	
		if (sf == null) {
			sf = base.getChild(from);
		}
		if (st == null) {
			st = base.getChild(to);
		}
		
		
		if (sf == null) {
			throw new ConnectionError("The source state instance is null when getting " + from + " on " + base);
		}
		if (st == null) {
			throw new ConnectionError("The target state instance is null when getting " + to + " on " + base);
		}
		
        //E.info("postBuild for "+base+". from: "+from+" ("+sourcePortId+"), to: "+to+" ("+targetPortId+")");
        //E.info("sihm: "+sihm);
	 	
		 
		//	st.addAttachment(destAttachments, rsi);
			
	 
	}

  
	 

 
	@Override
	public void consolidateStateTypes() {
		 
	}

}
