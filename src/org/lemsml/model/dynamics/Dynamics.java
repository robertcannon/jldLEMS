package org.lemsml.model.dynamics;

import java.util.List;

import org.lemsml.api.APIException;
import org.lemsml.model.core.AbstractTypeElement;
import org.lemsml.model.core.ListMap;
import org.lemsml.model.core.Single;
import org.lemsml.model.type.ComponentType;

public class Dynamics extends AbstractTypeElement {

	protected ListMap<StateVariable> stateVariableMap = new ListMap<StateVariable>();
	
	protected ListMap<TimeDerivative> timeDerivativeMap = new ListMap<TimeDerivative>();
	
	protected Single<OnStart> onStartSingle = new Single<OnStart>();

	protected ListMap<OnCondition> onConditionMap = new ListMap<OnCondition>();
	
	
	protected Dynamics(ComponentType ct) {
		super(ct, null);
	}


	public StateVariable addStateVariable(String eltname) {
		StateVariable sv = new StateVariable(this, eltname);
		stateVariableMap.put(eltname, sv);
		return sv;
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

	
	
	
}
