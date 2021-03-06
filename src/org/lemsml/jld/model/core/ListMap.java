package org.lemsml.jld.model.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListMap<T> {

	private ArrayList<T> items = new ArrayList<T>();
	private HashMap<String, T> map = new HashMap<String, T>();

	
	public void put(String name, T item) {
		items.add(item);
		map.put(name,  item);
	}


	public List<T> getItems() {
		return items;
	}


	public T get(String name) {
		T ret = null;
		if (map.containsKey(name)) {
			ret = map.get(name);
		}
		return ret;
	}


	public boolean containsKey(String name) {
		boolean ret = false;
		if (map.containsKey(name)) {
			ret = true;
		}
		return ret;
	}


	public int size() {
		return items.size();
	}


	public T get(int i) {
		return items.get(i);
	}


	public T getFirst() {
		 return items.get(0);
	}
	
	public T getLast() {
		 return items.get(items.size() - 1);
	}


	public List<String> getKeys() {
		 ArrayList<String> ret = new ArrayList<String>();
		ret.addAll(map.keySet());
		return ret;
	}


	public void remove(String name) {
		if (map.containsKey(name)) {
			T t = map.get(name);
			items.remove(t);
			map.remove(name);
		}
	}

 
	
	
}
