package org.lemsml.jld.model.dynamics;

import org.lemsml.jld.model.Dimension;
import org.lemsml.jld.model.core.AbstractAST;

public class DerivedVariable extends AbstractDynamicsElement {

	protected String name;
	
	protected String dimension;
	
	protected String exposure;
	
	protected String value;
	
	protected String select;
	
	protected String reduce;
	
	private Dimension r_dimension;
	private AbstractAST  r_AST;


	public DerivedVariable(Dynamics d, String s) {
		super(d);
		name = s;
	}
	
	
	public String toString() {
		return "DerivedVariable, name=" + name + ", dimension=" + dimension;
	}


	public String getDimension() {
		return dimension;
	}


	public void setDimension(String dimension) {
		this.dimension = dimension;
	}


	public String getExposure() {
		return exposure;
	}


	public void setExposure(String exposure) {
		this.exposure = exposure;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}

	public String getSelect() {
		return select;
	}


	public void setSelect(String select) {
		this.select = select;
	}


	public String getReduce() {
		return reduce;
	}


	public void setReduce(String reduce) {
		this.reduce = reduce;
	}


	public String getName() {
		return name;
	}


	public void setDimension(Dimension dim) {
		r_dimension = dim;
	}
	
	public Dimension getDimensionObject() {
		return r_dimension;
	}


	public void setAST(AbstractAST pt) {
		r_AST = pt;
	}
	
	public AbstractAST getAST() {
		return r_AST;
	}
}
