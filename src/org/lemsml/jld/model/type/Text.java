package org.lemsml.jld.model.type;

import org.lemsml.jld.model.core.TypeElement;

public class Text extends TypeElement {

	protected Text(ComponentType ct, String s) {
		super(ct, s);
	}

	public String toString() {
		return "Text";
	}
	
}
