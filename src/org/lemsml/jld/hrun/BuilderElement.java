package org.lemsml.jld.hrun;

import java.util.ArrayList;
 

public abstract class BuilderElement {

	
	ArrayList<BuilderElement> elts = new ArrayList<BuilderElement>();
	
	
	public void add(BuilderElement be) {
		elts.add(be);
	}
 
  
}
