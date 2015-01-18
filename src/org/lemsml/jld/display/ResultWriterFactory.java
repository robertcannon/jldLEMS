package org.lemsml.jld.display;
 
public class ResultWriterFactory {

	public ResultWriterFactory delegatedFactory = null;
	
	
	static ResultWriterFactory instance;

	
	public static ResultWriterFactory getFactory() {
		synchronized(ResultWriterFactory.class) {
 		if (instance == null) {
			instance = new ResultWriterFactory();
		}
		}
		return instance;
	}
	
	
	public void setDelegate(ResultWriterFactory dvf) {
		delegatedFactory = dvf;
	}
	
	
	public ResultWriter newResultWriter(RuntimeOutput ro) {
		ResultWriter ret = null;
		
		if (delegatedFactory != null) {
			ret = delegatedFactory.newResultWriter(ro);
		} else {
			ret = new DummyResultWriter(ro);
		}
		
		return ret;
	}
	
}
