package org.lemsml.jld.resolve;

import org.lemsml.jld.io.E;
import org.lemsml.jld.model.Component;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.ParameterValue;
import org.lemsml.jld.model.type.ComponentType;

public class ComponentResolver {

	private Lems lems;
	private Component targetComponent;
	
	
	public ComponentResolver(Lems lems, Component cpt) {
		this.lems = lems;
		this.targetComponent = cpt;
	}
	
	
	public void resolve() {
		resolveComponent(targetComponent);
	}
	
	
	
	private void resolveComponent(Component cpt) {
 
		applyInheritance(targetComponent);
	
		findType(targetComponent);
	
		allocateChildren(targetComponent);
		
		for (Component c : cpt.getComponents()) {
			resolveComponent(c);
		}
	
		int ncpt = cpt.getComponents().size();
		int nall = cpt.getAllSubcomponents().size();
		if (ncpt == nall) {
			// OK
		} else {
			E.error("Could not allocate some children: cpt=" + ncpt + ", allocated " + nall + " in " + this);
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
		}
	}

	
	private void findType(Component cpt) {
		String typename = cpt.getType();
		if (typename != null) {
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
					ComponentType typ = cpt.getComponentType();
					if (typ.hasChild(elt)) {
						cpt.setComponentType(typ.getChild(elt).getTargetType());
						gotChild = true;

					} else if (typ.hasChildren(elt)) {
						cpt.setComponentType(typ.getChildren(elt).getTargetType());
						gotChild = true;
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
		for (Component ch : cpt.getComponents()) {
			String enm = ch.getElement();
			boolean done = false;
			if (typ.hasChild(enm)) {
				cpt.allocateToChild(ch, enm);
				done = true;
				
			} else if (typ.hasChildren(enm)) {
				cpt.allocateToChild(ch, enm);
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
			if (!done) {
				E.error("Can't  allocate child component " + ch + " to container");
			}
		}
	}

}
