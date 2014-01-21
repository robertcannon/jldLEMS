package org.lemsml.jlems.core.out;

import org.lemsml.jlems.core.lite.simulation.RecWriter;
 

public class RecWriterFactory {

	public RecWriterFactory delegatedFactory = null;
	
	
	static RecWriterFactory instance;

	
	public static RecWriterFactory getFactory() {
		synchronized(RecWriterFactory.class) {
 		if (instance == null) {
			instance = new RecWriterFactory();
		}
		}
		return instance;
	}
	
	
	public void setDelegate(RecWriterFactory dvf) {
		delegatedFactory = dvf;
	}
	
	
	public RecWriter newRecWriter(String fid, String fnm, String fmt) {
		RecWriter ret = null;
		
		if (delegatedFactory != null) {
			ret = delegatedFactory.newRecWriter(fid, fnm, fmt);
		} else {
			ret = new DummyRecWriter(fid, fnm, fmt);
		}
		
		return ret;
	}
	
}
