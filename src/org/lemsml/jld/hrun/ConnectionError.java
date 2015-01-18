package org.lemsml.jld.hrun;

import org.lemsml.jld.exception.LEMSException;


public class ConnectionError extends LEMSException {
	private static final long serialVersionUID = 1L;

	
	public ConnectionError(String msg) {
		super(msg);
	}
	
	public ConnectionError(Exception ex) {
		super(ex);
	}
}
