package org.lemsml.jld.model.dynamics;
 
import org.lemsml.jld.model.core.AbstractAST;

public class StateAssignment extends AbstractDynamicsBlockElement {

	protected String value;
	protected String variable;
	
	private AbstractAST ast;
	
	public StateAssignment(AbstractDynamicsBlock b, String n) {
			super(b, n);
	}

	
	public void setVariable(String s) {
		variable = s;
	}
	
	public String getVariable() {
		return variable;
	}
	 
	
	public void setValue(String s) {
		value = s;
	}
	
	public String getValue() {
		return value;
	}


	public void setAST(AbstractAST pt) {
		ast = pt;
	}
	
	public AbstractAST getAST() {
		return ast;
	}

	
	
}
