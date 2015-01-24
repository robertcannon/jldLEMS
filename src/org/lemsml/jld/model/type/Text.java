package org.lemsml.jld.model.type;

import org.lemsml.jld.model.core.AbstractTypeElement;

public class Text extends AbstractTypeElement {

	protected Text(ComponentType ct, String s) {
		super(ct, s);
	}

	public String toString() {
		return "Text";
	}
	
}
