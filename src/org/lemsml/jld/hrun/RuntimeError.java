package org.lemsml.jld.hrun;

import org.lemsml.jld.exception.LEMSException;



public class RuntimeError extends LEMSException {
	private static final long serialVersionUID = 1L;

	
	public RuntimeError(String msg) {
		super(msg);
	}
	
	public RuntimeError(Exception ex) {
		super(ex);
	}
}
