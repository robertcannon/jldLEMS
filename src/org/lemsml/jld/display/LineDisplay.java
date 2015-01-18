package org.lemsml.jld.display;

public interface LineDisplay {

	void addPoint(String line, double x, double y);
	
	void addPoint(String line, double x, double y, String scol);

}
