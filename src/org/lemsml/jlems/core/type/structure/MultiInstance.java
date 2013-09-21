package org.lemsml.jlems.core.type.structure;

import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.run.BuilderElement;
import org.lemsml.jlems.core.run.MultiCollectorBuilder;
import org.lemsml.jlems.core.run.StateType;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.Component;
import org.lemsml.jlems.core.type.ComponentType;
import org.lemsml.jlems.core.type.Lems;
 

public class MultiInstance extends BuildElement {

	
 public String children;
	
	 

	@Override
	public void resolveLocal(Lems lems, ComponentType ct) throws ContentError, ParseError {
	 
	}
	
	
 
	public BuilderElement makeBuilder(Component cpt) throws ContentError, ParseError {
		StateType cb = null;
		
		MultiCollectorBuilder mb = new MultiCollectorBuilder(children);
			
		return mb;
	}
	
}
