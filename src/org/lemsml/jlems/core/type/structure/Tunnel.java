package org.lemsml.jlems.core.type.structure;

import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.run.BuilderElement;
import org.lemsml.jlems.core.run.TunnelBuilder;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.Component;
import org.lemsml.jlems.core.type.ComponentType;
import org.lemsml.jlems.core.type.Lems;

public class Tunnel extends BuildElement {

	public String name;
	public String from;
	public String to;
	
	public String expose;
	public String as;
	
	
	@Override
	public void resolveLocal(Lems lems, ComponentType ct) throws ContentError,
			ParseError {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BuilderElement makeBuilder(Component cpt) throws ContentError,
			ParseError {
		
		TunnelBuilder tb = new TunnelBuilder(from, to, expose, as);
		return tb;
		
	}

	
}
