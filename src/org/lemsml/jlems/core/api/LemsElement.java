package org.lemsml.jlems.core.api;

public class LemsElement {

	LemsModel lemsModel;
	
	protected LemsElement(LemsModel lm) {
		lemsModel = lm;
		lm.setFocusElement(this);
	}

	
	protected void checkFocus() {
		if (this == lemsModel.focusElement) {
			// OK
		} else {
			throw new APISequenceException("All poperties of an element must be set " +
					"before moving on to thenext one.");
		}
	}
	
}
