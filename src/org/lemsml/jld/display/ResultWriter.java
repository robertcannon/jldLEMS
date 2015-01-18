package org.lemsml.jld.display;

import org.lemsml.jld.hrun.RuntimeError;
 
public interface ResultWriter {

	void addPoint(String id, double x, double y);

	void advance(double t) throws RuntimeError;

	void addedRecorder();

	void close() throws RuntimeError;

}
