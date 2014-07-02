package org.lemsml.jlems.core.type.structure;

import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.run.BuilderElement;
import org.lemsml.jlems.core.run.LocalValues;
import org.lemsml.jlems.core.run.RuntimeType;
import org.lemsml.jlems.core.run.SubstitutionBuilder;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.Component;
import org.lemsml.jlems.core.type.ComponentType;
import org.lemsml.jlems.core.type.Lems;
import org.lemsml.jlems.core.type.LemsCollection;
import org.lemsml.jlems.core.type.LocalParameters;

public class Instance extends BuildElement {

	
	public String localParameters;
	public LocalParameters r_localParameters;
	
	public String componentReference;
	public Component r_componentReference;
	 
	
	public LemsCollection<Assign> assigns = new LemsCollection<Assign>();
 
	@Override
	public void resolveLocal(Lems lems, ComponentType ct) throws ContentError, ParseError {
	 
	
		if (localParameters != null) {
			r_localParameters = ct.getLocalParameters(localParameters);
		}
	 
	}
	
	
 
	public BuilderElement makeBuilder(Component cpt) throws ContentError, ParseError {
            RuntimeType cb = null;
            
            
            if (componentReference != null) {
                Component c = null;
                if (componentReference.startsWith("../")) {
                    String newPath = componentReference.substring(3);
                    c = cpt.getParent().getChild(newPath);
                } else {
                    c = cpt.getChild(componentReference);
                }
            
                c.setHasInstances();
                
                cb = c.getRuntimeType();
                
            }  
            
            LocalValues lvs = cpt.getLocalValues(localParameters);
           
            
            SubstitutionBuilder sb = new SubstitutionBuilder(cb, lvs);	
		return sb;
	}
	
}
