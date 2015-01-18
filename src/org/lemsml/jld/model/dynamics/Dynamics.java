package org.lemsml.jld.model.dynamics;

import java.util.List;

import org.lemsml.jld.api.APIException;
import org.lemsml.jld.model.core.AbstractTypeElement;
import org.lemsml.jld.model.core.ListMap;
import org.lemsml.jld.model.core.Single;
import org.lemsml.jld.model.type.ComponentType;

public class Dynamics extends AbstractTypeElement {

	protected ListMap<StateVariable> stateVariableMap = new ListMap<StateVariable>();
	
	protected ListMap<DerivedVariable> derivedVariableMap = new ListMap<DerivedVariable>();
	
	protected ListMap<TimeDerivative> timeDerivativeMap = new ListMap<TimeDerivative>();
	
	protected Single<OnStart> onStartSingle = new Single<OnStart>();

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


	public OnStart createOnStart() throws APIException {
		 OnStart os = new OnStart(this);
		 onStartSingle.setValue(os);
		 return os;
	}


	public OnCondition addOnCondition(String eltname) {
		OnCondition oc = new OnCondition(this);
		onConditionMap.put(eltname, oc);
		return oc;
	}

	public OnEvent addOnEvent(String eltname) {
		OnEvent oe = new OnEvent(this);
		onEventMap.put(eltname, oe);
		return oe;
	}

	public List<StateVariable> getStateVariables() {
		 return stateVariableMap.getItems();
	}


	 public List<OnCondition> getOnConditions() {
		return onConditionMap.getItems();
	}


	public OnStart getOnStart() {
		return onStartSingle.getItem();
	}


	public List<TimeDerivative> getTimeDerivatives() {
		return timeDerivativeMap.getItems();
	}


	public List<DerivedVariable> getDerivedVariables() {
		return derivedVariableMap.getItems();
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
