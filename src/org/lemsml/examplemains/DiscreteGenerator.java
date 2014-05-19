package org.lemsml.examplemains;

import org.lemsml.jlems.LEMSMain;

public class DiscreteGenerator {

	
	public static void main(String[] argv) {
		
		String[] args = {"-d", "-t", "na", "examples/example1.xml"};
		
		LEMSMain.selfContainedMain(args);
	
	}
	
	
}
