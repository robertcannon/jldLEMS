package org.lemsml.jld.hrun;

import java.util.ArrayList;

import org.lemsml.jld.io.E;

public class PathDerivedVariable {

    String varname;
    String path;
    String dimension;
 
    String tgtvar;
    boolean simple = false;
  
    double fbase = 0;
    final static int SUM = 1;
    final static int PROD = 2;
    int mode;
    
    boolean required;
    

    public PathDerivedVariable(String snm, String p, boolean rd, String reduce, String dim) {
        varname = snm;
        path = p;
        required = rd;
        dimension = dim;
      
        if (reduce != null) {
        	if (reduce.equals("add")) {
        		mode = SUM;
        		fbase = 0;
        	} else if (reduce.equals("multiply")) {
        		mode = PROD;
        		fbase = 1;
        	} else {
        		E.warning("Unrecognized reduce value: " + reduce);
        	}
        }
         
        String[] bits = path.split("/");
        tgtvar = bits[bits.length - 1];
        

        if (path.indexOf("[") > 0 || path.indexOf("*") > 0) {
            simple = false; 
            if (reduce == null) {
            	E.error("No reduction function specified for multi-selector " + path);
            }
         
        } else {
            simple = true;
        }
    }

   
    
    
    @Override
    public String toString() {
        return "PathDerivedVariable{" + "varname=" + varname + ", path=" + path + ", tgtvar=" + tgtvar + '}';
    }
    
    

    public String getVariableName() {
        return varname;
    }

    public double eval(StateRunnable rsin) throws RuntimeError {
        double ret = Double.NaN;
        
        // NB can only eval a path derived varaible on state instances, note compiled ones
        StateInstance sin = (StateInstance)rsin;
        StateRunnable tgt = null;
       
            if (simple) {
                tgt = sin.getPathStateInstance(path);
                if (tgt == null) {
                	if (mode == SUM) {
                		ret = 0;
                		// its fine to have a sum of no matches - just return zero.
                		// anything else is an error
                	} else {
                		if (!required &&  mode == PROD) {
                			ret = 1; 
                	
                		} else {
                			throw new RuntimeError("Not a sum and no variable at path " + path + " in " + sin + " seeking " + tgtvar + " mode=" + mode);
                		}
                	}
                } else {
                	ret = tgt.getVariable(tgtvar);
                }
            } else {
                // E.info("seeking psa " + path);
            	// this calls getTargetArray below the first time, then caches
            	// the array of targets for future use
                ArrayList<StateRunnable> asa = sin.getPathStateArray(path);

                ret = fbase;
                for (StateRunnable sa : asa) {
                    double var = sa.getVariable(tgtvar);
                    if (mode == SUM) {
                        ret += var;
                    } else if (mode == PROD) {
                        ret *= var;
                    }
                }
            }
       
        if (Double.isNaN(ret) || Double.isInfinite(ret)) {
        	 throw new RuntimeError("NaN in " + this + " on " + sin + "  target=" + tgt + " path=" + path);
        }
         
        return ret;
    }

    /*
    bits = rest.split("/");
    int nbit = bits.length;
    slice = new boolean[nbit];
    for (int i = 0; i < nbit - 1; i++) {
        String b = bits[i];
        if (b.endsWith("[*]")) {
            bits[i] = b.substring(0, b.length() - 3);
            slice[i] = true;
        
        } else if (b.indexOf("[") > 0) {
        	E.missing("Can't process predicate: " + b);
        	
        } else {
            slice[i] = false;
        }
    }
*/

    public StateRunnable getTargetState(StateInstance uin) throws RuntimeError {
        StateRunnable ret = null;

        StateRunnable wkinst = uin;
        String[] bits = path.split("/");
        for (int i = 0; i < bits.length - 1; i++) {
        	if (wkinst.hasChildInstance(bits[i])) {
        		StateRunnable sr = wkinst.getChildInstance(bits[i]);
        		wkinst = sr;
        	} else {
        		// this may be OK if this is an optional path
        		if (isRequired()) {
        			throw new RuntimeError("No such child instance: " + bits[i] + " in " + wkinst);
        		} 
        		wkinst = null;
        		break;
        	}
        }
        ret = wkinst;
        return ret;
    }

    public ArrayList<StateRunnable> getTargetArray(StateInstance base) throws RuntimeError {
        ArrayList<StateRunnable> ret = new ArrayList<StateRunnable>();

        ArrayList<StateRunnable> wka = new ArrayList<StateRunnable>();
        wka.add(base);
        
        String[] bits = path.split("/");
        
        for (int i = 0; i < bits.length - 1; i++) {
            String bit = bits[i];
            ArrayList<StateRunnable> swka = new ArrayList<StateRunnable>();
            
            for (StateRunnable rpar : wka) {
            	StateInstance par = (StateInstance)rpar;
            	if (bit.indexOf("[") > 0) { 
            		int iob = bit.indexOf("[");
            		int icb = bit.indexOf("]");
            		if (icb < iob) {
            			throw new RuntimeError("Can't parse " + path);
            		}
            		String cnm = bit.substring(0, iob);
            		
            		
            		if (par.hasMultiInstance(cnm)) {
            			MultiInstance mi = par.getMultiInstance(cnm);
            		 
            	 
                    	 String pred = bit.substring(iob + 1, icb);
                    	 if (pred.equals("*")) {
                    		 
                    		 for (StateRunnable sr : mi.getStateInstances()) {
                    			 swka.add((StateInstance)sr);                    		 
                    		 }
                    		 
                    	 } else {
                    		 String[] sab = parsePredicate(pred);
                    		 String satt = sab[0];
                    	     String sval = sab[1];
                    	     
                    	     for (StateRunnable rasi : mi.getStateInstances()) {
                    	    	 
                    	    	 //TODO - check we're sure weve got a stateInstance here
                    	    	 StateInstance asi = (StateInstance)rasi;
                    	    	 
                                 if (asi.hasTypeParam(satt)) {
                                     String stv = asi.getTypeParam(satt);
                                     if (stv != null && stv.equals(sval)) {
                                         //E.log("Adding match (" + satt + "  = " + sval + ") on " + asi+ " for <<" + path+">>");
                                         swka.add(asi);
                                     } else {
                                         //E.log("Not adding match (" + satt + "  = " + sval + ") on " + asi+ " for <<" + path+">>, stv = "+stv);
                                     }
                                 } else {
                                     //E.log("Not adding match (" + satt + "  = " + sval + ") on " + asi+ " for <<" + path+">>");
                                 }
                    	    	 
                    	     }
                    	 }
            		} else {
            			if (required) {
            				String msg = ("No match to path element '" + cnm + "' in " + 
            						path + " on " + par + " base=" + base + ", parent=" + base.parent);
            				E.info(msg);
            				// TODO - need some check on required
            				//            				throw new ContentError(msg);
            			}
            		}
            		
            		
            	} else {
            		swka.add((StateInstance)par.getChildInstance(bit));
            	}
            	wka = swka;            	
            }
        }
        ret = wka;    
       
        //E.log("---- Selecting path got " + ret.size() + " for <<" + path+">>");
        
        return ret;
    }

    
    
    public String getPath() {
        return path;
    }

    public boolean isSimple() {
        return simple;
    }

  
    
    private String[] parsePredicate(String pred) throws RuntimeError {
		String[] ret = new String[2];
		// for now just handle propert='val' type predicates
		String[] bits = pred.split("=");
		if (bits.length == 2) {
			String prop = bits[0];
			String sval = deQuote(bits[1]);
		 	ret[0] = prop;
		 	ret[1] = sval;
			
		} else {
			throw new RuntimeError("cant parse redicate " + pred);
		}
		return ret;
	}

	
	private String deQuote(String sq) throws RuntimeError {
		String ret = sq;
		if (sq.startsWith("'") && sq.endsWith("'")) {
			ret = sq.substring(1, sq.length() - 1);
			
		} else if (sq.startsWith("\"") && sq.endsWith("\"")) {
			ret = sq.substring(1, sq.length() - 1);
		
		} else {
			throw new RuntimeError("String not quoted properly? " + sq);
		}
		return ret;
	}


	public String[] getBits() {
		return path.split("/");
	}


	public PathDerivedVariable makeFlat(String pfx) {
		String modeString = null;
		// TODO cleaner constructor
		if (mode == SUM) {
			modeString = "add";
		} else if (mode == PROD) {
			modeString = "multiply";
		}
		PathDerivedVariable ret = new PathDerivedVariable(pfx + varname, flattenPath(pfx), required, modeString, dimension); 
		return ret;
	}
	
    private String flattenPath(String pfx) {
    	String ret = null;
    	if (simple) {
    		ret = pfx + path.replaceAll("/", "_");
    	} else {
    		
    		String wc = "[*]/";
    		if (path.indexOf(wc) > 0) {
    			int iwc = path.indexOf(wc);
    			String ba = path.substring(0, iwc);
    			String bb = path.substring(iwc + wc.length(), path.length());
    			ret = pfx + ba + "*_" + bb;
//    			E.info("Partially flattened " + path + " to " + ret);
    		} else {
    			E.missing("Cant flattten " + path);
    		}
    	}
    	return ret;
    }


	public String getOperatorSymbol() {
		String ret = "";
		if (mode == SUM) {
			ret = "+";
		} else if (mode == PROD) {
			ret = "*";
		}
		return ret;
	}


	public boolean isSum() {
		boolean ret = false;
		if (mode == SUM) {
			ret = true;
		}
		return ret;
	}
	
	public boolean isProduct() {
		boolean ret = false;
		if (mode == PROD) {
			ret = true;
		}
		return ret;
	}


	public boolean isRequired() {
		return required;
	}


	public String getDimensionString() {
		return dimension;
	}
	
}
