package org.lemsml.jlems.io.main;

import java.util.HashMap;

public class ArgReader {
	
	
	  public static HashMap<String, String> parseArguments(String[] argv) {
	    	HashMap<String, String> ret = new HashMap<String, String>();
	    	
	    	int iarg = 0;
	    	int ifree = 0;
	    	while (true) {
	    		if (iarg < argv.length) {
	    			String s = argv[iarg];
	      			iarg += 1;
	    			if (s.startsWith("-")) {
	     				if (iarg < argv.length) {
	     					String snext = argv[iarg];
	     					if (snext.startsWith("-")) {
	     						// another arg, not our value
	     						ret.put(s, "true");
	     					
	     					} else {
	     						ret.put(s, argv[iarg]);
	     						iarg += 1;
	     					}
	    				} else {
	    					// got a -x flag as the last arg
	    					ret.put(s, "true");
	    				}
	    			} else {
	      				ret.put("" + ifree, s);
	    				ifree += 1;
	    			}
	    		} else {
	    			break;
	    		}
	    	}
	    	return ret;
	    }
	    
}
