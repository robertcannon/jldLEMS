package org.lemsml.jlems.core.discrete;

import org.lemsml.jlems.core.dimensionless.FloatAssignment;
import org.lemsml.jlems.core.dimensionless.OnEvent;
import org.lemsml.jlems.core.dimensionless.OnState;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.xml.XMLElement;

public class DiscreteUpdateModelReader {

	 
	
	public DiscreteUpdateModelReader() {

	}
	
	
	public DiscreteUpdateModel read(XMLElement e) {
		XMLElement root = e;
		
		String id = root.getAttribute("id", "anon");
		DiscreteUpdateModel ret = new DiscreteUpdateModel(id);
		
		
		XMLElement iface = root.getElement("Interface");
		DUMInterface dumi = new DUMInterface();
		if (iface != null) {
			dumi = readInterface (iface, ret);
		}
		
		XMLElement state = root.getElement("State");
		if (state != null) {
			readState (state, ret);
		}
		
		XMLElement step = root.getElement("Step");
		if (step != null) {
			readStep (step, ret);
		}
		
		for (XMLElement oc : root.getElements("OnCondition")) {
			OnState os = readOnCondition(oc);
			ret.addOnState(os);
		}
		
		for (XMLElement elt : root.getElements("OnEvent")) {
			OnEvent oe = readOnEvent(elt);
			ret.addOnEvent(oe);
		}
		return ret;
	}
	
	
	
	private OnState readOnCondition(XMLElement oc) {
		OnState ret = new OnState("");
		ret.setCondition(oc.getAttribute("if"));
		for (XMLElement e : oc.getElements()) {
			String tn = e.getName();
			if (tn.equals("emit")) {
				ret.addEmit(e.getAttribute("port"));
			} else if (tn.equals("update")) {
				FloatAssignment fa = new FloatAssignment(e.getAttribute("variable"),
						e.getAttribute("value"));
				ret.addUpdateAssignment(fa);
				
			} else {
				E.missing();
			}
		}
		return ret;
	}
	
	
	private OnEvent readOnEvent(XMLElement oc) {
		OnEvent ret = new OnEvent(oc.getAttribute("port"));
 		for (XMLElement e : oc.getElements()) {
			String tn = e.getName();
			if (tn.equals("emit")) {
				ret.addEmit(e.getAttribute("port"));
			} else if (tn.equals("update")) {
				FloatAssignment fa = new FloatAssignment(e.getAttribute("variable"),
						e.getAttribute("value"));
				ret.addUpdateAssignment(fa);
				
			} else {
				E.missing();
			}
		}
		return ret;
	}

	private DUMInterface readInterface(XMLElement xi, DiscreteUpdateModel mod) {
		DUMInterface ret = new DUMInterface();
		for (XMLElement e : xi.getElements()) {
			String tn = e.getName();
			String enm = e.getAttribute("name");
			if (tn.equals("Parameter")) {
				ret.addParameter(enm);
				E.missing();
				
			} else if (tn.equals("InputEventPort")) {
				ret.addInputEventPort(enm);
				
			} else if (tn.equals("OutputEventPort")) {
				ret.addOutputEventPort(enm);
				
			} else if (tn.equals("AccumulatingInputVariable")) {
				ret.addAccumulatingInputVariable(enm);
				E.missing();
				
			} else if (tn.equals("InputVariable")) {
				ret.addInputVariable(enm);
				
			} else if (tn.equals("OutputVariable")) {
				ret.addOutputVariable(enm);
				
			} else {
				E.warning("Unrecognized interface element " + e);
			}
		} 
		return ret;
	}
	
	
	private void readState(XMLElement state, DiscreteUpdateModel ret) {
		for (XMLElement e : state.getElements()) {
			String tn = e.getName();
			if (tn.equals("StateVariable")) {
				ret.addStateVariable(e.getAttribute("name"));
				
			} else {
				E.warning("Unrecognized element in state: " + e);
			}
		}
	}
	
	
	public void readStep(XMLElement step, DiscreteUpdateModel ret) {
		for (XMLElement e : step.getElements()) {
			String tn = e.getName();
			if (tn.equals("var")) {
				FloatAssignment fa = new FloatAssignment(e.getAttribute("name"),
						e.getAttribute("value"));
				ret.addFloatAssignment(fa);
				
			} else if (tn.equals("update")) {
				FloatAssignment fa = new FloatAssignment(e.getAttribute("variable"),
						e.getAttribute("value"));
				ret.addUpdateFloatAssignment(fa);
				
			} else if (tn.equals("output")) {
				String val = e.getAttribute("value");
				String var = e.getAttribute("variable");				
				if (isSimpleValue(val)) {
					ret.addFloatExposure(var, val);
					
				} else {
					FloatAssignment fa = new FloatAssignment(var,
							e.getAttribute("value"));
					ret.addFloatAssignment(fa);
					ret.addFloatExposure(var, var);
				}
				
			} else {
				E.warning("Unrecognized element in step: " + e);
			}
		}
		
	}
	
	
	private boolean isSimpleValue(String str) {
		boolean ret = false;
		String wk = str.replaceAll("[\\*+-/^ ]", "");
		if (wk.length() == str.length()) {
			ret = true;
		}
		return ret;
	}
	
	
	
}
