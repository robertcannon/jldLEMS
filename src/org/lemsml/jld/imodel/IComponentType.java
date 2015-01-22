package org.lemsml.jld.imodel;

import java.util.List;

import org.lemsml.jld.imodel.dynamics.IDynamics;
import org.lemsml.jld.imodel.simulation.ISimulation;

public interface IComponentType {

	String getName();

	IComponentType getSupertype();

	IDynamics getIDynamics();

	ISimulation getISimulation();

	
	List<String> getParameterNames();

	List<String> getRequirementNames();

	List<String> getExposureNames();

	List<String> getPropertyNames();

	List<String> getTextNames();
	
	String getDimension(String rn);
 




}
