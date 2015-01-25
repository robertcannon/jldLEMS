package org.lemsml.jld.path;

import java.util.ArrayList;
import java.util.List;

import org.lemsml.jld.hrun.RuntimeError;
import org.lemsml.jld.io.E;
import org.lemsml.jld.imodel.IComponent;
import org.lemsml.jld.model.Lems;
 

// TODO this is just a quick hack as a POC for a few special cases. It needs a proper parser as for expressions.


public class PathEvaluator {

	Lems base;
	IComponent root;
	String path;
	
	
	public PathEvaluator() {
		this(null, null, null);
	}
	
	public PathEvaluator(Lems lems, IComponent cpt, String p) {
		base = lems;
		root = cpt;
		path = p;
	}


	public static double getValue(Lems lems, IComponent cpt, String path) throws RuntimeError {
		PathEvaluator pv = new PathEvaluator(lems, cpt, path);
		return pv.eval();
	}

	 

	private double eval() throws RuntimeError {
 		int ils = path.lastIndexOf("/");
		String cpath = path.substring(0, ils);
		String pname = path.substring(ils+1, path.length());
		
		IComponent c = getIComponent(cpath);
		double ret = c.getNumericalParameterValue(pname);
	 
 		return ret;
	}
	
	
	
	
	public String getStringValue() throws RuntimeError {

        //E.info("Evaluating path " + path + " relative to " + root);
 		int ils = path.lastIndexOf("/");
 		String ret = null;
 		if (ils < 0) {
 			ret = root.getStringParameterValue(path);
 		} else {
 			String cpath = path.substring(0, ils);
 			String pname = path.substring(ils+1, path.length());
		
 			IComponent c = getIComponent(cpath);
            if (c == null) {
                throw new RuntimeError("Problem evaluating path " + path + " relative to " + root);
            }
 			ret = c.getStringParameterValue(pname); 
	
 		}
 		if (ret == null) {
			throw new RuntimeError("No such String parameter " + path + " relative to " + root);
		}
		return ret;
	}
	
	
	public String getRelativeStringValue() throws RuntimeError {
		String ret = null;
		
		IComponent wk = root;
		int nup = 0;
		while (nup < 10) {
			nup += 1;
			String s = wk.getStringParameterValue(path);
			if (s != null) {
				ret = s;
			} else {
				wk = wk.getParent();
				if (wk == null) {
					break;
				}
			}
		}
   		 
 		if (ret == null) {
			throw new RuntimeError("No such String parameter " + path + " relative to " + root);
		}
		return ret;
	}
	
	private IComponent getIComponent(String cpatha) throws RuntimeError {
		String cpath = cpatha;
		if (cpath.startsWith("//")) {
			cpath = "ALLTYPE:" + cpath.substring(2, cpath.length());
		}
		return getIComponent(root, cpath);
	}
	
	
	
	public IComponent getIComponent(IComponent wk0, String cpath) throws RuntimeError {
		ArrayList<String> bits = new ArrayList<String>();
		
		int bktlev = 0;
		String bit = "";
//		StringTokenizer st = new StringTokenizer(cpath, "/");
	//	while (st.hasMoreTokens()) {
	//		String tok = st.nextToken();
	
		for (String tok : cpath.split("/")) {
			bit += tok;
			bktlev += count(tok, "[");
			bktlev -= count(tok, "]");
			if (bktlev == 0) {
				bits.add(bit);
				bit = "";
			} else {
				bit += "/";
			}
		}
		
		int nbit = bits.size();
		IComponent wk = wk0;
		for (int i = 0; i < nbit; i++) {
			String rp = bits.get(i);
		 
			if (rp.indexOf("[") < 0) {
				wk = getRelativeIComponent(wk, rp);
			} else {
				wk = getPredicateIComponent(wk, rp);
			}
 		}
 		return wk;
	}


	


	private IComponent getRelativeIComponent(IComponent wk, String rp) throws RuntimeError {
		IComponent ret = null; 
		if (rp.equals(".")) {
			ret = wk;
		} else if (rp.equals("..")) {
			ret = wk.getParent();
		} else {
			ret = wk.getIChild(rp);
		}
		
		return ret;
	}
	
	private IComponent getPredicateIComponent(IComponent wk, String rp) throws RuntimeError {
 		 int iob = rp.indexOf("[");
		 String cnm = rp.substring(0, iob);
		 List<? extends IComponent> ac = null;
		 if (cnm.startsWith("ALLTYPE:")) {
			E.missing();
			 //  ac = base.getAllByType(cnm.substring("ALLTYPE:".length(), cnm.length()));
			 
		 } else {
			ac = wk.getIChildren(cnm);
		 }
		 if (ac == null) {
			 throw new RuntimeError("no such children list " + cnm + " in " + wk);
		 }
		 
		 
		 String pred = rp.substring(iob+1, rp.indexOf("]"));
		 
		 IComponent ret = null;
		 if (pred.indexOf("=") > 0) {
			 int ieq = pred.indexOf("=");
			 String attname = pred.substring(0, ieq).trim();
			 String attval = pred.substring(ieq+1, pred.length()).trim();
			 
			 IComponent avc = getIComponent(attval);
			 
			 for (IComponent cwk : ac) {
				 IComponent ctry = getIComponent(cwk, attname);
				 if (ctry.equals(avc)) {
					 ret = cwk;
				 }
			 }
			 
		 } else {
			 E.missing("cant handle predicate form yet: " + pred);
		 }
		 
		 return ret;
	}

	
	private int count(String str, String c) {
		int ret = 0;
		int p = -1;
		while (true) {
			p = str.indexOf(c, p+1);
			if (p >= 0) {
				ret += 1;
			} else {
				break;
			}
		}
		return ret;
	}
	
	
}
