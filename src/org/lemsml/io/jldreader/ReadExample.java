package org.lemsml.io.jldreader;

import java.io.File;

import org.lemsml.api.ContentError;

public class ReadExample {

	
	public static void main(String[] argv) throws ContentError {
		ReadExample re = new ReadExample();
		re.readModel();
	}
	
	
	
	private void readModel() throws ContentError {
		File exd = new File("examples");
		File fb = new File(exd, "bounce.xml");
		
		JLDModelReader r = new JLDModelReader();
		r.readModelFromXMLFile(fb);
	}
	
}
