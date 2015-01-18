package org.lemsml.jld.hrun;

import java.util.ArrayList;
 


public class InstanceSet<T> {

	String name;
	
	ArrayList<T> items;
	
	StateRunnable parent;
	
	public InstanceSet(String nm, StateRunnable p) {
		name = nm;
		parent = p;
	}

	public void setItems(ArrayList<T> set) {
		items = set;
	}
	
	public String toString() {
		String ret = "Instance set  name " + name + " items: " + items;
		return ret;
	}
	

 
	
	public ArrayList<T> getItems() throws RuntimeError {
		if (items == null) {
			throw new RuntimeError("Seeking items from instance set '" + name + "' in " + parent + " but they have not been set yet");
		}
		return items;
	}
	

	public String getName() {
		return name;
	}

	public void addAll(ArrayList<T> instances) {
		items.addAll(instances);
	}

	public StateRunnable getParent() {
		return parent;
	}

	public int size() {
		return items.size();
	}

	public void add(T pc) {
		if (items == null) {
			items = new ArrayList<T>();
		}
		items.add(pc);
	    
    }
	
}
