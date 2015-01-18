package org.lemsml.jld.eval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.lemsml.jld.hrun.RuntimeError;


public class DBase implements DoubleEvaluator {

	AbstractDVal root;
	
	DVar[] vars;
	
	 
    public DBase(AbstractDVal abstractDVal) { // TODO Auto-generated constructor stub
    	root = abstractDVal;
		ArrayList<DVar> val = new ArrayList<DVar>();
		root.recAdd(val);
		
		vars = val.toArray(new DVar[val.size()]);
    }

	public AbstractDVal getRoot() {
            return root;
    }

    @Override
    public String toString() {
        return "DBase{" + "root=" + root + ", vars=" + Arrays.toString(vars) + '}';
    }

    
    public String getExpressionString() {
 		return root.toExpression();
	}
    
    
    public String getReversePolishExpressionString() {
 		return root.toReversePolishExpression();
	}
    
	
	public double evalD(HashMap<String, Double> valHM) {
		for (int i = 0; i < vars.length; i++) {
			vars[i].set(valHM);
		}
		return root.eval();
	}
	
	
	public double evalptr(HashMap<String, DoublePointer> valptrHM) throws RuntimeError {
		for (int i = 0; i < vars.length; i++) {
			vars[i].setPtr(valptrHM);
		}
		return root.eval();
	}
	

	public double evalptr(HashMap<String, DoublePointer> valptrHM, HashMap<String, DoublePointer> v2HM) {
		for (int i = 0; i < vars.length; i++) {
			vars[i].setPtr(valptrHM, v2HM);
		}
		return root.eval();
	}

	public DBase makeCopy() {
		DBase ret = new DBase(root.makeCopy());
		return ret;
	}

	public DBase makePrefixedCopy(String pfx, HashSet<String> stetHS) {
		DBase ret = new DBase(root.makePrefixedCopy(pfx, stetHS));
		return ret;
	}

	public void substituteVariableWith(String vnm, String pth) {
		root.substituteVariableWith(vnm, pth);
		for (AbstractDVal dv : vars) {
			dv.substituteVariableWith(vnm, pth);
		}
		
	}

	public boolean variablesIn(HashSet<String> known) {
		return root.variablesIn(known);
	}

	public boolean isTrivial() {
		return root.isTrivial();
	}

	@Override
	public String getSimpleValueName() {
		 return root.getSimpleValueName();
	}
	
}
