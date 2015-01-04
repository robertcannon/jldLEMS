package org.lemsml.jld.model.type;

import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.core.AbstractElement;
import org.lemsml.jld.model.core.AbstractTypeElement;

public class Path extends AbstractTypeElement {

	protected Path(ComponentType ct, String s) {
		super(ct, s);
	}

	
	public String toString() {
		return "Path";
	}
}
