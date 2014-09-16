package org.lemsml.jlems.tests.dev;

import org.lemsml.jlems.LemsToLite;
import org.lemsml.jlems.core.logging.E;

public class LEMSToLiteTest {

	
	public static void main(String[] argv) {
		String[] args = {"examples/exampleIAFCurrAlpha.xml", "brunel"};
		try {
			LemsToLite.main(args);
		} catch (Exception ex) {
			E.report("", ex);
 		}
	}
	
	
}
