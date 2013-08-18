package org.lemsml.examplemains;

import java.io.File;

import org.lemsml.jlems.core.sim.Sim;
import org.lemsml.jlems.io.reader.FileInclusionReader;

public class ExNumerics {

	
	
	
	private void read() {
		File fdir = new File("devexamples");
		File fn = new File(fdir, "numerics.xml");
		
		FileInclusionReader fir = new FileInclusionReader(fn);
		
		try {
			Sim sim = new Sim(fir.read());

			sim.readModel();	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] argv) {
		ExNumerics en = new ExNumerics();
		en.read();
	}
	 
}
