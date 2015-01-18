package org.lemsml.jld.hrun;

import java.util.ArrayList;
import java.util.HashMap;

import org.lemsml.jld.display.LineDisplay;
import org.lemsml.jld.eval.DoublePointer;


public interface StateRunnable {

	
	StateRunnable getChild(String snm) throws ConnectionError;

	void advance(StateRunnable parent, double t, double dt) throws RuntimeError;

	void exportState(String pfx, double t, LineDisplay ld);

	InPort getFirstInPort() throws ConnectionError;

	InPort getInPort(String portId) throws ConnectionError;

	StateWrapper getWrapper(String string);

	HashMap<String, DoublePointer> getVariables();
	
	String stateString();

	void setVariable(String varname, double d);
	
	double getVariable(String varname) throws RuntimeError;
	
	String getID();
 
	void setNewVariable(String string, double d);

	void evaluate(StateRunnable parent) throws RuntimeError;

	void initialize(StateRunnable sr) throws RuntimeError;

	Object getComponentID();

	boolean hasChildInstance(String str) throws RuntimeError;
	
	StateRunnable getChildInstance(String string) throws RuntimeError;

	ArrayList<StateRunnable> quietGetStateInstances(String path) throws ConnectionError, RuntimeError;

	void setParent(StateRunnable par);

	ArrayList<StateRunnable> getStateInstances() throws ConnectionError, RuntimeError;

	void checkBuilt() throws ConnectionError, RuntimeError;

	StateRunnable getScopeInstance(String id);

	ArrayList<StateRunnable> getPathInstances(String sel) throws ConnectionError, RuntimeError;

	double quietGetFloatProperty(String sel) throws RuntimeError;

	boolean hasSingleMI();

	OutPort getOutPort(String sourcePortId);

	StateRunnable getPathStateInstance(String path) throws RuntimeError;

	OutPort getFirstOutPort() throws ConnectionError;

	StateRunnable getParent();

	InstanceSet<StateRunnable> getUniqueInstanceSet() throws RuntimeError;

	InstanceSet<StateRunnable> getInstanceSet(String col);

	Object getWork();

	ArrayList<StateRunnable> getStateInstances(String path) throws ConnectionError, RuntimeError, RuntimeError;

	double getFloatProperty(String sel) throws RuntimeError;

	String getPathStringValue(String fieldName, double fac, double off) throws RuntimeError;

	void addAttachment(String destAttachments, StateInstance rsi) throws ConnectionError, RuntimeError;

	void addAttachment(StateInstance rsi) throws ConnectionError, RuntimeError;

	MultiInstance getSingleMI();

	void setList(String childrenName);

	String getChildSummary();

	boolean isBuilt();

	String getDimensionString(String lastbit) throws RuntimeError;

 
}
