package org.lemsml.jlems.core.discrete;

import java.util.ArrayList;
 
import org.lemsml.jlems.core.dimensionless.FloatAssignment;
import org.lemsml.jlems.core.dimensionless.IndependentVariable;
import org.lemsml.jlems.core.dimensionless.StateVariable;
import org.lemsml.jlems.core.dimensionless.VariableExposure;
import org.lemsml.jlems.core.dimensionless.AbstractVariable;
import org.lemsml.jlems.core.xml.XMLElement;

public class DiscreteUpdateModel {

	String id;
	
	ArrayList<IndependentVariable> independentVariables = new ArrayList<IndependentVariable>();
	ArrayList<StateVariable> stateVariables = new ArrayList<StateVariable>();
	
	ArrayList<VariableExposure> variableExposures = new ArrayList<VariableExposure>();
	
	ArrayList<FloatAssignment> floatAssignments = new ArrayList<FloatAssignment>();

	ArrayList<FloatAssignment> updateAssignments = new ArrayList<FloatAssignment>(); 
	
	public DiscreteUpdateModel(String id) {
		this.id = id;
	}
	
	
	public String getID() {
		return id;
	}
	
	
	
	public void addStateVariable(String s) {
		stateVariables.add(new StateVariable(s));
	}
	
	public void addIfNewStateVariable(String s) {
		boolean got = false;
		for (StateVariable sv : stateVariables) {
			if (sv.getName().equals(s)) {
				got = true;
			}
		}
		if (!got) {
			addStateVariable(s);
		}
 	}
	

	public XMLElement toXML() {
		XMLElement ret = new XMLElement("DiscreteUpdateComponent");
		
		for (IndependentVariable iv : independentVariables) {
			XMLElement xiv = ret.addElement("IndependentVariable");
			xiv.addAttribute("name", iv.getName());
		}
		for (StateVariable sv : stateVariables) {
			XMLElement siv = ret.addElement("StateVariable");
			siv.addAttribute("name", sv.getName());
		}
		for (FloatAssignment fa : floatAssignments) {
			XMLElement efa = ret.addElement("Assign");
			efa.addAttribute("varible", fa.getVariableName());
			efa.addAttribute("value", fa.getExpression());
			String rp = fa.getReversePolishExpression();
			if (rp != null) {
				efa.addAttribute("rpValue", rp);
			}
		}
		
		for (FloatAssignment fa : updateAssignments) {
			XMLElement efa = ret.addElement("Assign");
			efa.addAttribute("variable", fa.getVariableName());
			efa.addAttribute("value", fa.getExpression());
			String rp = fa.getReversePolishExpression();
			if (rp != null) {
				efa.addAttribute("rpValue", rp);
			}
		}
		
		for (VariableExposure ve : variableExposures) {
			XMLElement eve = ret.addElement("Expose");
			String lnm = ve.getLocalName();
			String enm = ve.getExposedName();
			eve.addAttribute("variable", lnm);
			if (enm == null || enm.equals(lnm)) {
				// no name change on exposure
			} else {
				eve.addAttribute("as", enm);
			}
		}
		return ret;
	}

	
	public void addFloatExposure(String var, String as) {
		variableExposures.add(new VariableExposure(var, as));
 	}

	
	public void addIndependentVariagble(String s) {
		independentVariables.add(new IndependentVariable(s));
	}

	public void addFloatAssignment(String variableName, String expressionString) {
		FloatAssignment fa = new FloatAssignment(variableName, expressionString);
		floatAssignments.add(fa);
		
	}
	
	public void addFloatAssignment(FloatAssignment fa) {
		floatAssignments.add(fa);
	}
	
	
	public void addUpdateFloatAssignment(String variableName, String expressionString) {
		FloatAssignment fa = new FloatAssignment(variableName, expressionString);
		updateAssignments.add(fa);
		
	}

	
	public ArrayList<IndependentVariable> getIndependentVariables() {
			return independentVariables;
	}

	public ArrayList<StateVariable> getStateVariables() { 
		return stateVariables;
	}
	
	public ArrayList<VariableExposure> getVariableExposures() {
		return variableExposures; 
	}
	
	public ArrayList<FloatAssignment> getFloatAssignments() {
		return floatAssignments;  
	}

	public ArrayList<FloatAssignment> getUpdateAssignments() {
		return updateAssignments; 
	}

	
	
	
}
