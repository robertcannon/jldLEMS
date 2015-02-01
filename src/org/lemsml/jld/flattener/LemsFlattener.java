package org.lemsml.jld.flattener;

import org.lemsml.jld.model.Component;
import org.lemsml.jld.model.Dimension;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.Unit;

public class LemsFlattener {

	
	public LemsFlattener() {
		
	}
	
	
	public Lems flatten(Lems hlems) {
	
		Lems flems = new Lems();
		
		copyDimensions(hlems, flems);
		
		copyUnits(hlems, flems);
		
		flattenComponents(hlems, flems);
		
		return flems;
	}


	
	
	private void flattenComponents(Lems hlems, Lems flems) {
		for (Component c : hlems.getComponents()) {
			insertFlattened(c, flems);
		}
	}

	
	

	private void insertFlattened(Component c, Lems flems) {
		ComponentFlattener cf = new ComponentFlattener();
		cf.insertFlattened(c, flems);
	}


	
	private void copyUnits(Lems hlems, Lems flems) {
		for (Unit u : hlems.getUnits()) {
			Unit fu = flems.addUnit(u.getName());
			fu.setDimension(u.getDimension());
			fu.setSymbol(u.getSymbol());
			fu.setPower(u.getPower());
			fu.setScale(u.getScale());
		}
	}


	private void copyDimensions(Lems hlems, Lems flems) {
		for (Dimension d : hlems.getDimensions()) {
			Dimension fd = flems.addDimension(d.getName());
			
			if (d.isAny()) {
				fd.setAny();
			} else {
				fd.setM(d.getM());
				fd.setL(d.getL());
				fd.setT(d.getT());
				fd.setI(d.getI());
				fd.setJ(d.getJ());
				fd.setK(d.getK());
				fd.setN(d.getN());
			}
		}
	}
	
	
}
