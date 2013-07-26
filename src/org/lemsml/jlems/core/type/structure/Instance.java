package org.lemsml.jlems.core.type.structure;

import java.util.ArrayList;
import java.util.HashMap;

import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.run.BuilderElement;
import org.lemsml.jlems.core.run.DoublePointer;
import org.lemsml.jlems.core.run.SubstitutionBuilder;
import org.lemsml.jlems.core.run.RuntimeType;
import org.lemsml.jlems.core.run.SingleChildBuilder;
import org.lemsml.jlems.core.run.StateType;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.Component;
import org.lemsml.jlems.core.type.ComponentType;
import org.lemsml.jlems.core.type.Lems;
import org.lemsml.jlems.core.type.LemsCollection;
import org.lemsml.jlems.core.type.LocalParameterValue;
import org.lemsml.jlems.core.type.LocalParameters;

public class Instance extends BuildElement {

	
	public String localParameters;
	public LocalParameters r_localParameters;
	
	public String component;
	public Component r_component;
	 
	
	public LemsCollection<Assign> assigns = new LemsCollection<Assign>();
 
	@Override
	public void resolveLocal(Lems lems, ComponentType ct) throws ContentError, ParseError {
	 
	
		if (localParameters != null) {
			r_localParameters = ct.getLocalParameters(localParameters);
		}
	 
	}
	
	
 
	public BuilderElement makeBuilder(Component cpt) throws ContentError, ParseError {
            RuntimeType cb = null;
            
            
            if (component != null) {
                Component c = null;
                if (component.startsWith("../")) {
                    String newPath = component.substring(3);
                    c = cpt.getParent().getChild(newPath);
                } else {
                    c = cpt.getChild(component);
                }
                cb = c.getRuntimeType();
                
            }  
            
            HashMap<String, DoublePointer> lpvals = cpt.getLocalValuesMap(localParameters);
            
            SubstitutionBuilder sb = new SubstitutionBuilder(cb, lpvals);	
		return sb;
	}
	
}
