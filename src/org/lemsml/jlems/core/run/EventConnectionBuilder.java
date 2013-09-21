package org.lemsml.jlems.core.run;

import java.util.ArrayList;
import java.util.HashMap;

import org.lemsml.jlems.core.eval.DoubleEvaluator;
 
import org.lemsml.jlems.core.sim.ContentError;

public class EventConnectionBuilder extends AbstractPostBuilder {

	String from;
	String to;
	
	StateType receiverCB;
	
	String destAttachments;

    String sourcePortId;
    String targetPortId;

    double delay = 0;

	ArrayList<ExpressionDerivedVariable> edvAL = new ArrayList<ExpressionDerivedVariable>();

	
	public EventConnectionBuilder(String sf, String st) {
		super();
		from = sf;
		to = st;
 
	}

	 
	public void postBuild(StateRunnable base, HashMap<String, StateRunnable> sihm, BuildContext bc) throws ConnectionError, ContentError, RuntimeError {
  		StateRunnable sf = sihm.get(from);
		StateRunnable st = sihm.get(to);		
		
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
	 	
		HashMap<String, DoublePointer> baseHM = base.getVariables();

		
		StateRunnable tgtInstance = st;
	 
		if (receiverCB != null) {
			StateInstance tgtsi = (StateInstance)(receiverCB.newInstance());
			st.addAttachment(destAttachments, tgtsi);
			tgtInstance = tgtsi;
		}
			
        for (ExpressionDerivedVariable edv: edvAL) {
			    //   E.info("Evaluating " + edv + " using "+ baseHM);
        	try {
        		double d = edv.evalptr(baseHM);
					
        		String vnm = edv.getVariableName();
				tgtInstance.setNewVariable(vnm, d);
					//E.info("Set new var " + vnm + " " + d);
        	} catch (RuntimeError re) {
        		throw new ConnectionError(re);
        	}
		}

        InPort inPort = null;
        if (targetPortId != null) {
        	inPort = tgtInstance.getInPort(targetPortId);
        } else {
        	inPort = tgtInstance.getFirstInPort();
        }
       

        OutPort op = null;
        if (sourcePortId != null) {
        	op = sf.getOutPort(sourcePortId);
        } else {
        	op = sf.getFirstOutPort();
        }
        op.connectTo(inPort, delay, EventManager.getInstance());
	}

	 
	public void setSourcePortID(String sp) {
		this.sourcePortId = sp;
		
	}


	public void setTargetPortID(String tp) {
		this.targetPortId = tp;
		
	}


	public void setReceiverStateType(StateType cb) {
		receiverCB = cb;
	}


	public void setReceiverContainer(String sv) {
		destAttachments = sv;
		
	}

    public void setDelay(double delay) {
        this.delay = delay;
    }

	public void addAssignment(String property, DoubleEvaluator de) throws ContentError {
		// TOOD - de, or de.makeCopy() ?
		ExpressionDerivedVariable edv = new ExpressionDerivedVariable(property, de);
		edvAL.add(edv);
	}

 
	@Override
	public void consolidateStateTypes() {
		 if (receiverCB != null) {
			 receiverCB = receiverCB.getConsolidatedStateType("(receiver)");
		 }	
	}

}
