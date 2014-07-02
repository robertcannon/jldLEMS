package org.lemsml.jlems.core.out;

import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.run.RuntimeOutput;

public class DummyResultWriter implements ResultWriter {

 
	
	public DummyResultWriter(RuntimeOutput ro) {
		 E.warning("Created dummy writer for " + ro);
	}

	@Override
	public void addPoint(String id, double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void advance(double t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addedRecorder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
	
}
