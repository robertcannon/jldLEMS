package org.lemsml.jld.io.reader;

import java.io.File;

import org.lemsml.jld.api.APIException;
import org.lemsml.jld.model.core.ModelException;

public class ReadExample {

	
	public static void main(String[] argv) throws ModelException, APIException {
		ReadExample re = new ReadExample();
		re.readModel();
	}
	
	
	
	private void readModel() throws ModelException, APIException {
		File exd = new File("examples");
		File fb = new File(exd, "bounce.xml");
		
		JLDModelReader r = new JLDModelReader();
		r.readModelFromXMLFile(fb);
	}
	
}
