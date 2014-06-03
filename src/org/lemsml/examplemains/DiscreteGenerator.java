package org.lemsml.examplemains;

import org.lemsml.jlems.LemsMain;

public class DiscreteGenerator {

	
	public static void main(String[] argv) {
		
		String[] args = {"-d", "-t", "na", "examples/example1.xml"};
		
		LemsMain.selfContainedMain(args);
	
	}
	
	
}
