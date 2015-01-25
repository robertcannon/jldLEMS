package org.lemsml.jld.hrun;

import java.util.ArrayList;

import org.lemsml.jld.io.E;
 
public class RecorderCollector implements StateTypeVisitor {

	ArrayList<RuntimeRecorder> recorders;
	
	public RecorderCollector(ArrayList<RuntimeRecorder> al) {
		recorders = al;
	}

	@Override
	public void visit(StateType cb) {
		ArrayList<RuntimeRecorder> a = cb.getRuntimeRecorders();
		if (a != null && !a.isEmpty()) {
			recorders.addAll(a);
 		}
		
	}

	
	
}
