package org.lemsml.jld.model.dynamics;

import java.util.List;

import org.lemsml.jld.api.APIException;
import org.lemsml.jld.imodel.dynamics.IDerivedVariable;
import org.lemsml.jld.imodel.dynamics.IDynamics;
import org.lemsml.jld.imodel.dynamics.IStateVariable;
import org.lemsml.jld.imodel.dynamics.ITimeDerivative;
import org.lemsml.jld.model.core.TypeElement;
import org.lemsml.jld.model.core.ListMap;
import org.lemsml.jld.model.core.Single;
import org.lemsml.jld.model.type.ComponentType;

public class Dynamics extends TypeElement implements IDynamics {

	protected ListMap<StateVariable> stateVariableMap = new ListMap<StateVariable>();
	
	protected ListMap<DerivedVariable> derivedVariableMap = new ListMap<DerivedVariable>();
	
	protected ListMap<TimeDerivative> timeDerivativeMap = new ListMap<TimeDerivative>();
	
	protected ListMap<OnStart> onStartMap = new ListMap<OnStart>();

	protected ListMap<OnCondition> onConditionMap = new ListMap<OnCondition>();
	
	protected ListMap<OnEvent> onEventMap = new ListMap<OnEvent>();
	
	
	
	
	protected Dynamics(ComponentType ct) {
		super(ct, null);
	}
	
	
	public String toString() {
		return "Dynamics";
	}


	public StateVariable addStateVariable(String eltname) {
		StateVariable sv = new StateVariable(this, eltname);
		stateVariableMap.put(eltname, sv);
		return sv;
	}

	public DerivedVariable addDerivedVariable(String eltname) {
		 DerivedVariable dv = new DerivedVariable(this, eltname);
		 derivedVariableMap.put(eltname, dv);
		 return dv;
	}


	public TimeDerivative addTimeDerivative(String eltname) {
		TimeDerivative td = new TimeDerivative(this, eltname);
		timeDerivativeMap.put(eltname, td);
		return td;
	}


	public OnStart addOnStart(String eltname) throws APIException {
		 OnStart os = new OnStart();
		 onStartMap.put(eltname, os);
		 return os;
	}


	public OnCondition addOnCondition(String eltname) {
		OnCondition oc = new OnCondition();
		onConditionMap.put(eltname, oc);
		return oc;
	}

	public OnEvent addOnEvent(String eltname) {
		OnEvent oe = new OnEvent();
		onEventMap.put(eltname, oe);
		return oe;
	}

	public List<StateVariable> getStateVariables() {
		 return stateVariableMap.getItems();
	}

	public List<? extends IStateVariable> getIStateVariables() {
		return stateVariableMap.getItems();
	}
	

	 public List<OnCondition> getOnConditions() {
		return onConditionMap.getItems();
	}


	public List<OnStart> getOnStarts() {
		return onStartMap.getItems();
	}


	public List<TimeDerivative> getTimeDerivatives() {
		return timeDerivativeMap.getItems();
	}

	public List<? extends ITimeDerivative> getITimeDerivatives() {
		return getTimeDerivatives();
	}

	public List<DerivedVariable> getDerivedVariables() {
		return derivedVariableMap.getItems();
	}

	
	public List<? extends IDerivedVariable> getIDerivedVariables() {
		return getDerivedVariables();
	}
	
	
	public List<OnEvent> getOnEvents() {
		return onEventMap.getItems();
	}


	public Object getStructures() {
		// TODO Auto-generated method stub
		return null;
	}


	public StateVariable getStateVariable(String s) {
		return stateVariableMap.get(s);
	}

 
	
	
	
}
