package org.lemsml.jlems.tests.dev;

import org.lemsml.jlems.LEMSToLite;

public class LEMSToLiteTest {

	
	public static void main(String[] argv) {
		String[] args = {"examples/example1.xml", "na"};
		try {
			LEMSToLite.main(args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
}
