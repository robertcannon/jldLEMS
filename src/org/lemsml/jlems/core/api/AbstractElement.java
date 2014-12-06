package org.lemsml.jlems.core.api;

public class AbstractElement {

	Lems lems;
	String name;
	
	protected AbstractElement(Lems lm, String s) {
		lems = lm;
		name = s;
		lm.setFocusElement(this);
	}

	
	protected void checkFocus() throws APISequenceException {
		if (this == lems.focusElement) {
			// OK
		} else {
			throw new APISequenceException("All poperties of an element must be set " +
					"before moving on to thenext one.");
		}
	}
	
}
