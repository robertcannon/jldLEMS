package org.lemsml.jld.model.core;

import org.lemsml.jld.api.APIException;

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
