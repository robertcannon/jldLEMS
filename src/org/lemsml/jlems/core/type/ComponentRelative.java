package org.lemsml.jlems.core.type;

public class ComponentRelative {

	String path;
	Component target;
	
	public ComponentRelative(Component c, String p) {
		path = p;
		target = c;
	}
	
	public String toString() {
		return path + "/" + target.getID();
	}
	
}
