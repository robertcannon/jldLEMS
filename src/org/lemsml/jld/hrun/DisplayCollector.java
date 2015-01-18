package org.lemsml.jld.hrun;

import java.util.ArrayList;
 
public class DisplayCollector implements StateTypeVisitor {

	ArrayList<RuntimeDisplay> outputs;
	
	public DisplayCollector(ArrayList<RuntimeDisplay> al) {
		outputs = al;
	}

	@Override
	public void visit(StateType cb) {
		ArrayList<RuntimeDisplay> a = cb.getRuntimeDisplays();
		if (a != null) {
			outputs.addAll(a);
		}
	 
		
 		
	}

	
	
}
