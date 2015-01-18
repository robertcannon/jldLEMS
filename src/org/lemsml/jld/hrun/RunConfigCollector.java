package org.lemsml.jld.hrun;

import java.util.ArrayList;
 

public class RunConfigCollector implements StateTypeVisitor {

	ArrayList<RunConfig> runConfigs;
	
	public RunConfigCollector(ArrayList<RunConfig> al) {
		runConfigs = al;
	}

	@Override
	public void visit(StateType cb) {
		RunConfig rc = cb.getRunConfig();
		if (rc != null) {
			runConfigs.add(rc);
			
			ArrayList<RuntimeRecorder> arc = new ArrayList<RuntimeRecorder>();
			RecorderCollector recc = new RecorderCollector(arc);
			cb.visitAll(recc);
			 
			rc.setRecorders(arc);
		}
	}

	
	
}
