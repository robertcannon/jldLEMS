package org.lemsml.model.core;

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

 
	
	
}
