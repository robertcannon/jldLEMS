package org.lemsml.jld.imodel;

import java.util.List;

public interface ILems {


	IComponent getIComponent(String id);
 
	List<IConstant> getIConstants();

}
