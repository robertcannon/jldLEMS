package org.lemsml.jld.hrun;

import java.util.ArrayList;
 

public class RunConfig {

	StateType targetStateType;
//	Component targetComponent;
	double step;
	double total;
	
	ArrayList<RuntimeRecorder> recorders;

	public RunConfig(StateType tgt, double st, double tot) {
		targetStateType = tgt;
		step = st;
		total = tot;
	}

    @Override
    public String toString() {
        return "RunConfig {" + "target=" + targetStateType + ", step=" + step + ", total=" + total + '}';
    }

    
 


	public double getTimestep() {
		return step;
	}

	public double getRuntime() {
		return total;
	}

	public RunConfig makeCopy() {
		RunConfig ret = new RunConfig(targetStateType, step, total);
		return ret;
	}

	public void setRecorders(ArrayList<RuntimeRecorder> arc) {
		recorders = arc;
	}
	
	public ArrayList<RuntimeRecorder> getRecorders() {
		if (recorders == null) {
			recorders = new ArrayList<RuntimeRecorder>();
		}
		return recorders;
	}

	public StateType getTargetStateType() {
		return targetStateType;
	}
 
	
}
