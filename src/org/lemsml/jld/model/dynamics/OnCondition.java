package org.lemsml.jld.model.dynamics;

import org.lemsml.jld.imodel.dynamics.IOnCondition;
import org.lemsml.jld.model.core.AbstractAST;


public class OnCondition extends AbstractDynamicsBlock implements IOnCondition {

	
	protected String test;
	
	private AbstractAST r_ast;
	
	
	public OnCondition() {
		super();
	}
	
	public void setTest(String s) {
		test = s;
	}
	
	public String getTest() {
		return test;
	}

	public void setAST(AbstractAST ast) {
		r_ast = ast;
	}
	
	public AbstractAST getAST() {
		return r_ast;
	}
 

}
