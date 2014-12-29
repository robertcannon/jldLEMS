package org.lemsml.io.jld.reader;

import java.io.File;

import org.lemsml.api.APIException;
import org.lemsml.model.core.ModelException;

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
