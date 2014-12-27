package org.lemsml.model;


public class AbstractElement {

	Lems lems;
	protected String name;
	
	protected AbstractElement(Lems lm, String s) {
		lems = lm;
		name = s;
		lm.setFocusElement(this);
	}

	
	protected void checkFocus() throws ModelException {
		if (this == lems.focusElement) {
			// OK
		} else {
			throw new ModelException("All poperties of an element must be set " +
					"before moving on to thenext one.");
		}
	}

}
