package org.lemsml.jlems.core.discrete;

import org.lemsml.jlems.core.dimensionless.AccumulatingInputVariable;
import org.lemsml.jlems.core.dimensionless.Emit;
import org.lemsml.jlems.core.dimensionless.FloatAssignment;
import org.lemsml.jlems.core.dimensionless.InputEventPort;
import org.lemsml.jlems.core.dimensionless.InputVariable;
import org.lemsml.jlems.core.dimensionless.OnEvent;
import org.lemsml.jlems.core.dimensionless.OnState;
import org.lemsml.jlems.core.dimensionless.OutputEventPort;
import org.lemsml.jlems.core.dimensionless.Parameter;
import org.lemsml.jlems.core.dimensionless.StateVariable;
import org.lemsml.jlems.core.dimensionless.VariableExposure;
import org.lemsml.jlems.core.xml.XMLElement;

public class DiscreteUpdateModelWriter {
	
	DiscreteUpdateModel dum;
	
	boolean includeRP = false;
	
	
	public DiscreteUpdateModelWriter(DiscreteUpdateModel m) {
		dum = m;
	}
	
	
	
	public XMLElement toXML() {
		XMLElement ret = new XMLElement("DiscreteUpdateComponent");
		
		
		XMLElement iface = ret.addElement("Interface");
		for (Parameter p : dum.parameters) {
			XMLElement pe = iface.addElement("Parameter");
			pe.addAttribute("name", p.getName());
		}
		
		for (InputEventPort iep : dum.inputEventPorts) {
			XMLElement elt = iface.addElement("InputEventPort");
			elt.addAttribute("name", iep.getName());
		}
		
		for (OutputEventPort oep : dum.outputEventPorts) {
			XMLElement elt = iface.addElement("OutputEventPort");
			elt.addAttribute("name", oep.getName());
		}
		
		for (AccumulatingInputVariable aiv : dum.accumulatingInputVariables) {
			XMLElement elt = iface.addElement("AccumulatingInputVariable");
			elt.addAttribute("name", aiv.getName());
		}
		
		for (InputVariable iin : dum.inputVariables) {
			XMLElement elt = iface.addElement("InputVariable");
			elt.addAttribute("name", iin.getName());
		}
		 
		
		for (VariableExposure ve : dum.variableExposures) {
			XMLElement eve = iface.addElement("OutputVariable");
			String lnm = ve.getLocalName();
			String enm = ve.getExposedName();
			if (enm == null) {
				enm = lnm;
			}
			eve.addAttribute("name", enm);
		}	
		
		// just for readability of serialized xml
		iface.setPostSpacing(2);
		
		
		
		XMLElement state = ret.addElement("State");
		for (StateVariable sv : dum.stateVariables) {
			XMLElement siv = state.addElement("StateVariable");
			siv.addAttribute("name", sv.getName());
		}
		state.setPostSpacing(2);
		
		
		
		XMLElement step = ret.addElement("Step");
		
	
		XMLElement wk = null;
		for (FloatAssignment fa : dum.floatAssignments) {
			XMLElement efa = step.addElement("var");
			efa.addAttribute("name", fa.getVariableName());
			efa.addAttribute("value", fa.getExpression());
			
			if (includeRP) {
				String rp = fa.getReversePolishExpression();
				if (rp != null) {
					efa.addAttribute("rpValue", rp);
				}
			}
			wk = efa;
		}
		if (wk != null) {
			wk.setPostSpacing(1);
		}
		
		XMLElement lastfa = null;
		for (FloatAssignment fa : dum.updateAssignments) {
			lastfa = addAssign(step, fa);
		}
		if (lastfa != null) {
			lastfa.setPostSpacing(1);
		}
		
		
		XMLElement lastout = null;
		for (VariableExposure ve : dum.variableExposures) {
			XMLElement eve = step.addElement("output");
			String lnm = ve.getLocalName();
			String enm = ve.getExposedName();
			if (enm == null) {
				enm = lnm;
			}
			eve.addAttribute("variable", enm);
			eve.addAttribute("value", lnm);

		 
			lastout = eve;
		}	
		step.setPostSpacing(2);
		
		
		 
		for (OnState os : dum.onStates) {
			XMLElement xos = ret.addElement("OnCondition");
			xos.addAttribute("if", os.getCondition());
			for (FloatAssignment fa : os.getFloatAssignments()) {
				addAssign(xos, fa);
			}
			for (Emit ee : os.getEmits()) {
				XMLElement xee = xos.addElement("emit");
				xee.addAttribute("port",  ee.getPort());
			}
			xos.setPostSpacing(1);
		}
	 
		
		for (OnEvent oe : dum.onEvents) {
			XMLElement xoe = ret.addElement("OnEvent");
			xoe.addAttribute("port", oe.getPortName());
			for (FloatAssignment fa : oe.getFloatAssignments()) {
				addAssign(xoe, fa);
			}
		}
		
	
		
	
		return ret;
	}

	
	
	
	
	private XMLElement addAssign(XMLElement ret, FloatAssignment fa) {
		XMLElement efa = ret.addElement("update");
		efa.addAttribute("variable", fa.getVariableName());
		efa.addAttribute("value", fa.getExpression());
	
		if (includeRP) {
			String rp = fa.getReversePolishExpression();
			if (rp != null) {
				efa.addAttribute("rpValue", rp);
			}
		}
		return efa;
	}
	
}
