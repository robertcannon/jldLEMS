package org.lemsml.jld.imodel;

import java.util.List;

import org.lemsml.jld.imodel.dynamics.IDynamics;
import org.lemsml.jld.imodel.simulation.ISimulation;
import org.lemsml.jld.imodel.structure.IStructure;

public interface IComponentType {

	String getName();


	IComponentType getSupertype();

	
	IDynamics getIDynamics();

	IStructure getIStructure();

	ISimulation getISimulation();

	
	
	List<String> getParameterNames();

	List<String> getRequirementNames();

	List<String> getExposureNames();

	List<String> getPropertyNames();

	List<String> getTextNames();
	

	String getFieldDimension(String rn);


	List<String> getReceivePortNames();
 
	List<String> getSendPortNames();
	
}
