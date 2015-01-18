package org.lemsml.jld.model.dynamics;

import org.lemsml.jld.model.core.AbstractAST;

public class TimeDerivative extends AbstractDynamicsElement {

	
	protected String variable;
	protected String value;

	private StateVariable r_stateVariable;
	
	private AbstractAST r_ast;
	
	
	public TimeDerivative(Dynamics d, String n) {
		super(d);
		variable = n;
 	}


	public void setVariable(String s) {
		variable = s;
	}
	
	public void setValue(String s) {
		value = s;
	} 

	public String getValue() {
		 return value;
	}

	public String getVariable() {
		 return variable;
	}


	public void setStateVariable(StateVariable sv) {
		// TODO Auto-generated method stub
		r_stateVariable = sv;
	}
	
	public StateVariable getStateVariable() {
		return r_stateVariable;
	}


	public void setAST(AbstractAST ast) {
		r_ast = ast;
	}
	
	public AbstractAST getAST() {
		return r_ast;
	}
}
