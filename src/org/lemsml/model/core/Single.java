package org.lemsml.model.core;

import java.util.ArrayList;
import java.util.HashMap;

import org.lemsml.api.APIException;
import org.lemsml.model.dynamics.Dynamics;

public class Single<T> {

	private T item = null;
	
	

	public void setValue(T t) throws APIException {
		if (item != null) {
			throw new APIException("Can't reset the value of a single element");
		}
		item = t;
	}



	public T getItem() {
		return item;
	}
	
	
	
}
