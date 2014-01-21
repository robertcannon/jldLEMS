package org.lemsml.jlems.core.sim;

import java.io.Serializable;

public class ContentError extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;


	public ContentError() {
		super();
	}
	
	public ContentError(String msg) {
		super(msg);
	}
	 
}
