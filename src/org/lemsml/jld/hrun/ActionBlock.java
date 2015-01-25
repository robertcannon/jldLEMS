package org.lemsml.jld.hrun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.lemsml.jld.eval.BooleanEvaluator;
import org.lemsml.jld.eval.DoubleEvaluator;
import org.lemsml.jld.eval.DoublePointer;
import org.lemsml.jld.io.E;



public class ActionBlock {
	
	private final ArrayList<VariableAssignment> assignments = new ArrayList<VariableAssignment>();
	 
	private final ArrayList<String> outPorts = new ArrayList<String>();
	
	private final ArrayList<ConditionAction> conditionActions = new ArrayList<ConditionAction>();
	
	
	public boolean doesTransition = false;
	public String transitionTo;
	
	 
	
	public void addAssignment(String name, DoubleEvaluator das) {	
 		assignments.add(new VariableAssignment(name, das));	
	}

	
	public void addVariableAssignment(VariableAssignment va) {
 		assignments.add(va);
	}
	
	public void addEventOut(String portName) {
		outPorts.add(portName);	
	}

	// MUSTDO - call this
	public void setTransitionTo(String st) {
		transitionTo = st;
		doesTransition = true;
	}

	public ArrayList<VariableAssignment> getAssignments() {
		return assignments;
    }
	

	public ArrayList<ConditionAction> getConditionActions() {
		return conditionActions;
	}
	

	public void addVarsTo(ArrayList<String> vars) {
		for (VariableAssignment va : assignments) {
			String svn = va.getVarName();
			if (vars.contains(svn)) {
				// nothing to do
			} else {
				vars.add(svn);
			}
		}
	}


	// TODO shouldn't pass the list in here to change
	public void addPortsTo(ArrayList<String> opa) {
		if (outPorts.size() > 0) {
			HashSet<String> hs = new HashSet<String>();
			hs.addAll(opa);
			for (String s : outPorts) {
				if (!hs.contains(s)) {
					opa.add(s);
				}
			}
		}
	}
	

    public void run(StateInstance uin) throws RuntimeError {
    	HashMap<String, DoublePointer> varHM = uin.getVarHM();
        for (VariableAssignment vass : assignments) {
            double v = vass.evalptr(varHM);
            varHM.get(vass.varname).set(v);
        }
        
    	for (ConditionAction ca : conditionActions) {
			if (ca.evalptr(varHM)) {
				ca.getAction().run(uin);
			}
		}
        
        for (String sop : outPorts) {
            uin.sendFromPort(sop);
        }
    }

	
	public void run(RegimeStateInstance rsi) throws RuntimeError {
		HashMap<String, DoublePointer> varHM = rsi.getVarHM();
		for (VariableAssignment vass : assignments) {
			varHM.get(vass.varname).set(vass.evalptr(varHM));
		}
		for (ConditionAction ca : conditionActions) {
			if (ca.evalptr(varHM)) {
				ca.getAction().run(rsi);
			}
		}
		
		for (String sop : outPorts) {
			rsi.sendFromPort(sop);
		}
		if (doesTransition) {
			rsi.transitionTo(transitionTo);
		}
	}

	public void addTransition(String regime) throws RuntimeError {
		if (doesTransition) {
			throw new RuntimeError("multiple transitions defined in an action. Only one is allowed.");
		} else {
			doesTransition = true;
			transitionTo = regime;
		}
	}

	public ActionBlock makeCopy() {
		ActionBlock ret = new ActionBlock();
		ret.doesTransition = doesTransition;
		ret.transitionTo = transitionTo;
		for (String s : outPorts) {
			ret.addEventOut(s);
		}
		for (VariableAssignment a : assignments) {
			ret.addVariableAssignment(a.makeCopy());
		}
		for (ConditionAction ca : conditionActions) {
			ret.addConditionalActionBlock(ca.getCondition(), ca.getAction());
		}
		return ret;
		
	}


	public ArrayList<String> getOutEvents() {
		return outPorts;
	}


	public void addConditionalActionBlock(BooleanEvaluator be, ActionBlock ab) {
		 ConditionAction ca = new ConditionAction(be);
		 ca.setAction(ab);
		 conditionActions.add(ca);
		
	}

 
	
}
