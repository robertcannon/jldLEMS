package org.lemsml.jld.resolve;

import org.lemsml.jld.expression.ExpressionParser;
import org.lemsml.jld.io.E;
import org.lemsml.jld.model.Dimension;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.dynamics.DerivedVariable;
import org.lemsml.jld.model.dynamics.Dynamics;
import org.lemsml.jld.model.dynamics.StateVariable;
import org.lemsml.jld.model.dynamics.TimeDerivative;
import org.lemsml.jld.model.type.AbstractField;
import org.lemsml.jld.model.type.ComponentType;
import org.lemsml.jld.model.type.Exposure;
import org.lemsml.jld.model.type.Parameter;
import org.lemsml.jld.model.type.Requirement;
 
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
			resolveDynamics(d, ct);
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
		
		if (sd == null) {
			// TODO ok?
		
		} else if (sd != null) {
			Dimension d = lems.getDimension(sd);
			if (d == null) {
				
			} else {
				e.setDimension(d);
			}
		}
		
	}


	private void resolveDynamics(Dynamics d, ComponentType ct) {
		for (StateVariable sv : d.getStateVariables()) {
			resolveStateVariable(sv, ct);
		}
		for (DerivedVariable dv : d.getDerivedVariables()) {
			resolveDerivedVariable(dv, ct);
			
			String v = dv.getValue();
			if (v != null) {
				
			}
		}
		for (TimeDerivative td : d.getTimeDerivatives()) {
			resolveTimeDerivative(td, d);
		}
		
	}



	private void resolveTimeDerivative(TimeDerivative td, Dynamics dynamics) {
		String s = td.getVariable();
		StateVariable sv = dynamics.getStateVariable(s);
		if (sv != null) {
			td.setStateVariable(sv);
		} else {
			E.error("Time derivative " + td + " refers to unknown state variable " + s);
		}
		
	}



	private void resolveStateVariable(StateVariable sv, ComponentType ct) {
		String sd = sv.getDimension();
		if (sd != null) {
			Dimension d = lems.getDimension(sd);
			if (d == null) {
				
			} else {
				sv.setDimension(d);
			}
		}
		String expo = sv.getExposure();
		if (expo != null) {
			Exposure e = ct.getExposure(expo);
			if (e != null) {
				sv.setExposure(e);
			} else {
				E.error("Derived variable " + sv.getName() + " refers to exposure " + expo + " which can't be found.");
			}
		}
		
	}
	
	
	private void resolveDerivedVariable(DerivedVariable sv, ComponentType ct) {
		String sd = sv.getDimension();
		if (sd != null) {
			Dimension d = lems.getDimension(sd);
			if (d == null) {
				
			} else {
				sv.setDimension(d);
			}
		}
		String expo = sv.getExposure();
		if (expo != null) {
			Exposure e = getExposure(ct, expo);
			if (e != null) {
				sv.setExposure(e);
			} else {
				E.error("Derived variable " + sv.getName() + " refers to exposure " + expo + " which can't be found.");
			}
		}
		
	}

	
	private Exposure getExposure(ComponentType ct, String expo) {
		Exposure ret = ct.getExposure(expo);
		if (ret == null) {
			if (ct.getSupertype() != null) {
 				ret = getExposure(ct.getSupertype(), expo);
			} else {
				E.error("No supertype for " + ct + " but also no local exposure " + expo);
			}
		}
		return ret;
	}
	
	
}
