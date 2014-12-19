package org.lemsml.model;

import org.lemsml.model.Dimension.SI;

public class APIExample {

	
	
	public void buildExample1() throws APIException, APISequenceException {
		Lems lemsModel = new Lems();
		
		
		// create a new dimension value, set its components in terms of the base dimensions
		Dimension acc = lemsModel.addDimension("accelleration");
		acc.set(Dimension.SI.MASS, 1);
		acc.set(Dimension.SI.LENGTH, 1);
		acc.set(Dimension.SI.TIME, -2);
	 
	
		Dimension mass = lemsModel.addDimension("mass");
		mass.set(Dimension.SI.MASS, 1);
		
		
		Dimension length = lemsModel.addDimension("mass");
		length.set(Dimension.SI.LENGTH, 1);
		
		
		Unit gram = lemsModel.addUnit("gram");
		gram.setSymbol("g");
		gram.setDimension(mass);
		gram.setPower(-3);
		
		
		Unit milligram = lemsModel.addUnit("milligram", mass, -6, "mg");
	 
		// this will throw an exception 
		length.set(Dimension.SI.CURRENT, 1);
		
		ComponentType lct = lemsModel.addComponentType("cell");	
		lct.addParameter("radius", length);
		
		// this points to the mass dimension by name -will need 
		// resolving later - only needed when deserializing a model
		lct.addParameter("m", "mass");
		
		lct.addChild("child1", "type1");
		
		lct.addChildren("children1", "type2");
		
		
		ComponentType lct2 = lemsModel.addExtendingComponentType("cell2", lct);
		
		
		Component cpt = lemsModel.addComponent("cptid", "type2");
		cpt.setParameterValue("a", "1");
		
		cpt.setParameterValue("a", new Quantity(1, gram));
	
	}
	
	
	
	
	
	public static void main(String[] argv) throws APIException, APISequenceException {
		APIExample be = new APIExample();
		be.buildExample1();
		
	}
	
	
	
}
