package org.lemsml.jld.resolve;

import java.util.ArrayList;

import org.lemsml.jld.expression.Dim;
import org.lemsml.jld.expression.ParseError;
import org.lemsml.jld.io.E;
import org.lemsml.jld.model.Component;
import org.lemsml.jld.model.Dimension;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.ParameterValue;
import org.lemsml.jld.model.core.DimensionalQuantity;
import org.lemsml.jld.model.core.ParseException;
import org.lemsml.jld.model.core.QuantityReader;
import org.lemsml.jld.model.type.Child;
import org.lemsml.jld.model.type.ComponentType;
import org.lemsml.jld.model.type.Parameter;

public class ComponentResolver {

	private Lems lems;
	
	private QuantityReader quantityReader;
	
	
	public ComponentResolver(Lems lems) {
		this.lems = lems;
		quantityReader = new QuantityReader(lems);
	}
	
	
	
	public void resolveComponent(Component cpt) {
		
		applyInheritance(cpt);
	
		findType(cpt);
	
		allocateChildren(cpt);
		
		parseParameters(cpt);
		
		for (Component c : cpt.getComponents()) {
			resolveComponent(c);
		}
		
		
	
		int ncpt = cpt.getComponents().size();
		int nall = cpt.getAllSubcomponents().size();
		if (ncpt == nall) {
			// OK
		} else {
			E.error("Could not allocate some children: total=" + ncpt + ", allocated " + nall + " in " + cpt);
		}
		
		locateReferenceChilds(cpt);
		 
	}
	
	
	private void locateReferenceChilds(Component cpt) {
		ComponentType ct = cpt.getComponentType();
		for (Child ch : ct.getChilds()) {
			String cnm = ch.getName();
			
			Component current = cpt.getChild(cnm);
			if (current == null) {
				// the child component is not defined in-place. The child name should be present in the 
				// parent component as an attribute of which the value provides the id of the component to use 
				ParameterValue pv = cpt.getParameterValue(cnm);
				if (pv != null) {
					String id = pv.getValue();

					// first try resolving in the local context, ie, as a child of our parent
					Component ref = null;
					Component p = cpt.getParent();
					if (p != null) {
						// maybe it is a named child? TODO does this ever happen
						ref = p.getChild(id);
						
						if (ref == null) {
							ref = getChildWithId(p, id);							 
						}
						
					}
					if (ref == null) {
						ref = lems.getComponent(id);
					}
					
					if (ref != null) {
						cpt.allocateToChild(ref, cnm);
					} else {
						E.error("No component found with id: " + id);
					}
					
				} else {
					E.error("Component needs a child '" + cnm + "' but there is no local child or reference of that name");
				}
			}
			
			
		}
	}


	private Component getChildWithId(Component p, String id) {
		Component ret = null;
		for (Component c : p.getComponents()) {
			if (id.equals(c.getId())) {
				ret = c;
			}
		}
		return ret;
	}
	
	
	

	private void parseParameters(Component cpt) {
		ComponentType ct = cpt.getComponentType();
		for (Parameter p : ct.getParameters()) {
			ParameterValue pv = cpt.getParameterValue(p.getName());
			if (pv != null) {
				try {
					resolveParameterValue(pv, p);
				} catch (ParseError pe) {
					E.error("Can't parse " + pv.getValue() + ": " + pe);
				}
			}
		}
	}
	
	private void resolveParameterValue(ParameterValue pv, Parameter p) throws ParseError {			
		
		DimensionalQuantity dq = quantityReader.parseValue(pv.getValue());
	
		Dimension dtgt = p.getDimensionObject();
		
		Dim dim = new Dim(dq.getDimensionObject());
 				
		if (dtgt == null) {
			E.error("No dimension for param " + p);
			
		} else {
			Dim dimtgt = new Dim(dtgt);
			
			if (dimtgt.isAny()) {
				double value = dq.getDoubleValue();
				pv.setDoubleValue(value);
			
			} else if (dimtgt.matches(dim)) {
				double value = dq.getDoubleValue();
				pv.setDoubleValue(value);
			
			} else if (dq.isZero()) {
	 			pv.setDoubleValue(0.);
			
			} else {			
				E.error("Can't set parameter: "+ p +" with dimensions " + p.getDimension() + " with string " + pv.getValue());
			}
		}
	}
	 
	
	
	

	private void applyInheritance(Component cpt) {
		Component wk = cpt;
		while (true) {
			String ext = wk.getExtends();
			if (ext == null) {
				break;
			}
			wk = lems.getComponent(ext);
			if (wk == null) {
				E.error("Can't fine super-component " + ext + " of " + wk);
				break;
			} else {
				copyFieldsFromTo(wk, cpt);
			}
		}
	}

	
	
	private void copyFieldsFromTo(Component src, Component tgt) {
		for (ParameterValue pv : src.getParameterValues()) {
			if (tgt.hasParameter(pv.getName())) {
				// don't overwrite
			} else {
				tgt.setParameterValue(pv.getName(), pv.getValue());
			}
			if (tgt.getType() != null && tgt.getType().length() > 0) {
				// keep as is
				
			} else if (src.getType() != null) {
				tgt.setType(src.getType());
			}
		}
	}

	
	private void findType(Component cpt) {
		String typename = cpt.getType();
		if (typename != null && typename.length() > 0) {
			ComponentType ct = lems.getComponentType(typename);
			if (ct == null) {
				E.error("No such component type " + typename + "needed by component: " + cpt);
			} else {
				cpt.setComponentType(ct);
			}
			
		} else {
			// no type specified, 
			String elt = cpt.getElement();
			if (elt.equals("Component")) {
				// no information in the element name either
				E.error("Can't get component type for " + cpt);
			} else {
				// there are two possibilities. Either the element name is the name of a 
				// Child or Children element in the parent
				// or it is the type name
				boolean gotChild = false;
				Component par = cpt.getParent();
				if (par != null) {
					ComponentType typ = par.getComponentType();
					if (typ != null) {
						if (typ.hasChild(elt)) {
							cpt.setComponentType(typ.getChild(elt).getTargetType());
							gotChild = true;

						} else if (typ.hasChildren(elt)) {
							cpt.setComponentType(typ.getChildren(elt).getTargetType());
							gotChild = true;
						}
					}
				}
				
				if (!gotChild) {
					ComponentType ct = lems.getComponentType(elt);
					if (ct != null) {
						cpt.setComponentType(ct);
					} else {
						E.error("Can't get type for " + cpt);
					}
				}
			}
		}
		
	}
	
	
	private void allocateChildren(Component cpt) {
		ComponentType typ = cpt.getComponentType();
		
		int nsub = cpt.getComponents().size();
		
		for (Component ch : cpt.getComponents()) {
			ch.setParent(cpt);
			
			String enm = ch.getElement();
			boolean done = false;
			if (typ.hasChild(enm)) {
				cpt.allocateToChild(ch, enm);
				done = true;
				
			} else if (typ.hasChildren(enm)) {
				cpt.allocateToChild(ch, enm);
				done = true;
				
			} else if (typ.hasChildType(enm)) {
				cpt.allocateToChild(ch, typ.getChildNameForType(enm));
				done = true;
			} else if (typ.hasChildrenType(enm)) {
				cpt.allocateToChildren(ch, typ.getChildrenNameForType(enm));
				done = true;
				
			} else {
				String tnm = ch.getType();
				if (typ.hasChildType(tnm)) {
					cpt.allocateToChild(ch, typ.getChildNameForType(tnm));
					done = true;
					
				} else if (typ.hasChildrenType(tnm)) {
					cpt.allocateToChild(cpt, typ.getChildrenNameForType(tnm));
					done = true;
				}
			}
			if (!done) {
				int nchn = typ.getChildrenCount();
				int nchd = typ.getChildCount();
				if (nchn == 1 && nchd == 0) {
					cpt.allocateToChildren(cpt, typ.getSoleChildrenName());
					done = true;
				} else if (nchd == 1 && nchn == 0) {
					cpt.allocateToChild(cpt, typ.getSoleChildName());
					done = true;
				}
			} 
			if (done) {
				//E.info("OK, allocated " + ch + " within " + cpt);
			} else {
				E.error("Can't  allocate child component " + ch + " to container");
			}
		}
	}

}
