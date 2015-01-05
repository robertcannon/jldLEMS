package org.lemsml.jld.resolve;

import org.lemsml.jld.expression.ExpressionParser;
import org.lemsml.jld.model.Dimension;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.dynamics.DerivedVariable;
import org.lemsml.jld.model.dynamics.Dynamics;
import org.lemsml.jld.model.dynamics.StateVariable;
import org.lemsml.jld.model.type.AbstractField;
import org.lemsml.jld.model.type.ComponentType;
import org.lemsml.jld.model.type.Exposure;
import org.lemsml.jld.model.type.Parameter;
import org.lemsml.jld.model.type.Requirement;
import org.lemsml.jlems.core.logging.E;

public class TypeResolver {
	
	Lems lems;
		
	public TypeResolver(Lems lems) {
		this.lems = lems;
	}
	
	
	
	public void resolveType(ComponentType ct) {
	 		
		resolveParameters(ct);
		resolveExposures(ct);
		resolveRequirements(ct);
	
	
		Dynamics d = ct.getDynamics();
		if (d != null) {
			resolveDynamics(d);
		}
	}
	
	
  
 

	private void resolveRequirements(ComponentType ct) {
		 for (Requirement r : ct.getRequirements()) {
			 resolveDimension(r);
		 }
		
	}
 
	private void resolveExposures(ComponentType ct) {
		 for (Exposure e : ct.getExposures()) {
			 resolveDimension(e);
		 }
	}
 
	private void resolveParameters(ComponentType ct) {
		for (Parameter p : ct.getParameters()) {
			resolveDimension(p);
		}
	}
	

	private void resolveDimension(AbstractField e) {
		String sd = e.getDimension();
		if (sd != null) {
			Dimension d = lems.getDimension(sd);
			if (d == null) {
				
			} else {
				e.setDimension(d);
			}
		}
		
	}


	private void resolveDynamics(Dynamics d) {
		for (StateVariable sv : d.getStateVariables()) {
			resolveStateVariable(sv);
		}
		for (DerivedVariable dv : d.getDerivedVariables()) {
			resolveDerivedVariable(dv);
			
			String v = dv.getValue();
			if (v != null) {
				
			}
			
		}
		
	}



	private void resolveStateVariable(StateVariable sv) {
		String sd = sv.getDimension();
		if (sd != null) {
			Dimension d = lems.getDimension(sd);
			if (d == null) {
				
			} else {
				sv.setDimension(d);
			}
		}
		
	}
	
	
	private void resolveDerivedVariable(DerivedVariable sv) {
		String sd = sv.getDimension();
		if (sd != null) {
			Dimension d = lems.getDimension(sd);
			if (d == null) {
				
			} else {
				sv.setDimension(d);
			}
		}
		
	}

	
}
