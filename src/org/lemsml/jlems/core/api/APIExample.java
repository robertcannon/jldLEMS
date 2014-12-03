package org.lemsml.jlems.core.api;

public class APIExample {

	
	 
	
	
	public void buildExample1() throws APIException, APISequenceException {
		LemsModel lemsModel = new LemsModel();
		
		
		// create a new dimension value, set its components in terms of the base dimensions
		LemsDimension acc = lemsModel.addDimension("accelleration");
		acc.set(LemsDimension.SI.MASS, 1);
		acc.set(LemsDimension.SI.LENGTH, 1);
		acc.set(LemsDimension.SI.TIME, -2);
	 
	
		LemsDimension mass = lemsModel.addDimension("mass");
		mass.set(LemsDimension.SI.MASS, 1);
		
		
		LemsDimension length = lemsModel.addDimension("mass");
		length.set(LemsDimension.SI.LENGTH, 1);
		
		
		LemsUnit gram = lemsModel.addUnit("gram");
		gram.setSymbol("g");
		gram.setDmension(mass);
		gram.setPower(-3);
		
		// this will throw an exception 
		length.set(LemsDimension.SI.CURRENT, 1);
		
		LemsComponentType lct = lemsModel.addComponentType("cell");	
		lct.addParameter("radius", length);
		
		
		LemsComponentType lct2 = lemsModel.addExtendingComponentType("cell2", lct);
		
		
		
		
		
		
	
	}
	
	
	
	
	
	public static void main(String[] argv) throws APIException, APISequenceException {
		APIExample be = new APIExample();
		be.buildExample1();
		
	}
	
	
	
}
