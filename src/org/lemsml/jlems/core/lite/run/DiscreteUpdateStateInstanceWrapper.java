package org.lemsml.jlems.core.lite.run;

import org.lemsml.jlems.core.run.RegimeStateInstance;
import org.lemsml.jlems.core.run.RuntimeError;
import org.lemsml.jlems.core.run.StateWrapper;
import org.lemsml.jlems.core.sim.ContentError;

public class DiscreteUpdateStateInstanceWrapper extends StateWrapper {

	DiscreteUpdateStateInstance dusi;
 	
	public DiscreteUpdateStateInstanceWrapper(DiscreteUpdateStateInstance si, String snm) {
		super(snm);
		dusi = si;
	 
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double getValue() throws ContentError, RuntimeError {
		double ret = Double.NaN;
		
		ret = dusi.getValue(varname);
		
		return ret;
	}
}
