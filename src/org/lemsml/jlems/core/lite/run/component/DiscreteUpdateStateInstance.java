package org.lemsml.jlems.core.lite.run.component;

import java.util.ArrayList;
import java.util.HashMap;

import org.lemsml.jlems.core.display.LineDisplay;
import org.lemsml.jlems.core.lite.run.network.Connection;
import org.lemsml.jlems.core.lite.run.network.EventManager;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.run.ConnectionError;
import org.lemsml.jlems.core.run.DoublePointer;
import org.lemsml.jlems.core.run.InPort;
import org.lemsml.jlems.core.run.InstanceSet;
import org.lemsml.jlems.core.run.MultiInstance;
import org.lemsml.jlems.core.run.OutPort;
import org.lemsml.jlems.core.run.RuntimeError;
import org.lemsml.jlems.core.run.StateInstance;
import org.lemsml.jlems.core.run.StateRunnable;
import org.lemsml.jlems.core.run.StateWrapper;
import org.lemsml.jlems.core.sim.ContentError;

public class DiscreteUpdateStateInstance implements StateRunnable {

	DiscreteUpdateStateType duType;
	HashMap<String, DoublePointer> variables = new HashMap<String, DoublePointer>();
	
	StateRunnable parent;
	 
	
	HashMap<String, ArrayList<Connection>> connectionsHM;
	
	EventManager eventManager;
	
	
	public DiscreteUpdateStateInstance(DiscreteUpdateStateType dut, HashMap<String, DoublePointer> vars) {
		duType = dut;
		variables = vars;
 	}

	
	public void dumpState() {
		E.info("Local vars: " + variables);
	}
	
	
	
	@Override
	public StateRunnable getChild(String snm) throws ConnectionError {
		E.missing();
		return null;
	}

	@Override
	public void advance(StateRunnable parent, double t, double dt) throws RuntimeError, ContentError {
		variables.get("dt").set(dt);
		
		duType.advance(dt, variables, this, parent);
	}

	

	public void receiveEvent(String tgtPort) throws RuntimeError {
		duType.handleEvent(this, tgtPort, variables);
	}

	
	
	
	@Override
	public void exportState(String pfx, double t, LineDisplay ld) {
 		E.missing();
	}

	@Override
	public InPort getFirstInPort() throws ConnectionError {
 		return null;
	}

	@Override
	public InPort getInPort(String portId) throws ConnectionError {
 		return null;
	}

	
	@Override
	public StateWrapper getWrapper(String str) {
		StateWrapper ret = null;
		if (duType.hasStateVariable(str)) {
			ret = new DiscreteUpdateStateInstanceWrapper(this, str);
			
		} else if (variables.containsKey(str)) {
			ret = new DiscreteUpdateStateInstanceWrapper(this, str);
		}
		return ret;
	}

	
	public double getValue(String s) {
		return variables.get(s).getValue();
	}
	
	
	@Override
	public HashMap<String, DoublePointer> getVariables() {
		return variables;
	}

	@Override
	public String stateString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVariable(String varname, double d) {
		variables.get(varname).set(d);
	}

	public void setVariableMaybeNew(String varname, double d) {
		if (variables.containsKey(varname)) {
			variables.get(varname).set(d);
		} else {
			variables.put(varname, new DoublePointer(d));
		}
	}
	
	
	// TODO - parameter could be in state type?
	public void setLocalParameter(String pnm, double d) throws ContentError {
		if (variables.containsKey(pnm)) {
			variables.get(pnm).set(d);
			
		} else if (duType.hasParameter(pnm)) {
			variables.put(pnm, new DoublePointer(d));
		
		} else {
			throw new ContentError("No such variable " + pnm + " in " + this);
		}
	}
	
	
	@Override
	public double getVariable(String varname) throws RuntimeError {
		double ret = variables.get(varname).get();
		return ret;
	}

	@Override
	public String getID() {
		E.missing();
		return duType.getID();
	}

	@Override
	public void setNewVariable(String string, double d) {
		E.missing();
	}

	@Override
	public void evaluate(StateRunnable parent) throws RuntimeError, ContentError {
		E.missing();
	}

	@Override
	public void initialize(StateRunnable sr) throws RuntimeError, ContentError {
		//E.missing();
	}

	@Override
	public Object getComponentID() {
		return duType.getID();
	}
	
	@Override
	public boolean hasChildInstance(String string) {
		return true;
	}

	@Override
	public StateRunnable getChildInstance(String string) throws ContentError {
		return null;
	}

	@Override
	public ArrayList<StateRunnable> quietGetStateInstances(String path) throws ConnectionError, ContentError,
			RuntimeError {
		return null;
	}

	@Override
	public void setParent(StateRunnable par) {
		parent = par;
	}

	@Override
	public ArrayList<StateRunnable> getStateInstances() throws ConnectionError, ContentError, RuntimeError {
 		return null;
	}

	@Override
	public void checkBuilt() throws ConnectionError, ContentError, RuntimeError {
		// anything needed?
	}

	@Override
	public StateRunnable getScopeInstance(String id) {
		return null;
	}

	@Override
	public ArrayList<StateRunnable> getPathInstances(String sel) throws ContentError, ConnectionError, RuntimeError {
	 		return null;
	}

	@Override
	public double quietGetFloatProperty(String sel) throws ContentError {
		double ret = Double.NaN;
		if (variables.containsKey(sel)) {
			ret = variables.get(sel).get();
		}
		return ret;
	}

	@Override
	public boolean hasSingleMI() {
 		return false;
	}

	@Override
	public OutPort getOutPort(String sourcePortId) {
		E.missing();
		return null;
	}

	@Override
	public StateRunnable getPathStateInstance(String path) throws ContentError {
		return null;
	}

	@Override
	public OutPort getFirstOutPort() {
		E.missing();
		return null;
	}

	@Override
	public StateRunnable getParent() {
		return parent;
	}

	@Override
	public InstanceSet<StateRunnable> getUniqueInstanceSet() throws ContentError {
		return null;
	}

	@Override
	public InstanceSet<StateRunnable> getInstanceSet(String col) {
		return null;
	}

	@Override
	public Object getWork() {
		E.missing();
		return null;
	}

	@Override
	public ArrayList<StateRunnable> getStateInstances(String path) throws ConnectionError, ContentError, RuntimeError {
		return null;
	}

	@Override
	public double getFloatProperty(String sel) throws ContentError {
		double ret = 0.;
		if (variables.containsKey(sel)) {
			ret = variables.get(sel).get();
		} else {
			throw new ContentError("no such property: " + sel + " in " + this);
		}
		return ret;
	}

	@Override
	public String getPathStringValue(String fieldName, double fac, double off) throws ContentError, RuntimeError {
		E.missing();
		return null;
	}

	@Override
	public void addAttachment(String destAttachments, StateInstance rsi) throws ConnectionError, ContentError,
			RuntimeError {
		 E.missing();
		
	}

	@Override
	public MultiInstance getSingleMI() {
		E.missing();
		return null;
	}

	@Override
	public void setList(String childrenName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getChildSummary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBuilt() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void addAttachment(StateInstance rsi) throws ConnectionError,
			ContentError, RuntimeError {
		// TODO Auto-generated method stub
		
	}

	public void setEventManager(EventManager em) {
		eventManager = em;
	}

	
	public void addConnection(String pfrom, Connection conn) {
		if (connectionsHM == null) {
			connectionsHM = new HashMap<String, ArrayList<Connection>>();
		}
		if (!connectionsHM.containsKey(pfrom)) {
			connectionsHM.put(pfrom,  new ArrayList<Connection>());
		}
		connectionsHM.get(pfrom).add(conn);
	}

	public void sendEvent(String port) throws RuntimeError {
		if (connectionsHM != null && connectionsHM.containsKey(port)) {
			eventManager.addEvents(connectionsHM.get(port));
		} else {
			E.warning("No connections from port " + port + " on " + this);
		}
	}

}
