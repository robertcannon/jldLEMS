package org.lemsml.jld.expression;

import org.lemsml.jld.exception.ExpressionError;
import org.lemsml.jld.io.E;
 
 
public class MathMLWriter implements ExpressionVisitor {

	StringBuilder sb;
	 
	int depth = 0;
	 
	private String indent = "    ";
	private String offset = "                ";
    
    public MathMLWriter() {
    }
    
    public MathMLWriter(String indent, String offset) {
        this.indent = indent;
        this.offset = offset;
    }
	
	public String serialize(ParseTree pt) throws ExpressionError {
		MathMLWriter mw = new MathMLWriter();
		return mw.generateMathML(pt);
	}

    public void setOffset(String offset) {
        this.offset = offset;
    }
	
	
	private String generateMathML(ParseTree pt) throws ExpressionError {
		sb = new StringBuilder();
		depth = 0;
		pt.visitAll(this);
		return sb.toString();
	}
	
	private String indent() {
		String ret = this.offset;
		for (int i = 0; i < this.depth; i++) {
			ret += this.indent;
		}
		return ret;
	}
	
	
	@Override
	public void visitVariable(String svar) {
		sb.append(indent() + "<ci>" + svar + "</ci>\n");
	}


	@Override
	public void visitOrNode(OrNode orNode) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visitFunctionNode(String fname, DoubleParseTreeNode argEvaluable) throws ExpressionError {
		sb.append(indent() + "<apply>\n");
		depth += 1;
		sb.append(indent() + "<" + fname + "/>\n");
		argEvaluable.doVisit(this);
		depth -= 1;
		sb.append(indent() + "</apply>\n");
	}


	@Override
	public void visitConstant(double dval) {
		sb.append(indent() + "<cn>" + dval + "</cn>\n");
	}


	private void visitOp(String opname, DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) throws ExpressionError {
		sb.append(indent() + "<apply>\n");
		depth += 1;
		sb.append(indent() + "<" + opname + "/>\n");
		if (leftEvaluable != null) {
			leftEvaluable.doVisit(this);
		}
		 rightEvaluable.doVisit(this);
		depth -= 1;
		sb.append(indent() + "</apply>\n");
	}
	

	@Override
	public void visitPlusNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) throws ExpressionError {
		visitOp("plus", leftEvaluable, rightEvaluable);
	}


	@Override
	public void visitTimesNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) throws ExpressionError {
		visitOp("times", leftEvaluable, rightEvaluable);
	}


	@Override
	public void visitPowerNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) throws ExpressionError {
		visitOp("power", leftEvaluable, rightEvaluable);
	}


	@Override
	public void visitMinusNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) throws ExpressionError {
		visitOp("minus", leftEvaluable, rightEvaluable);
	}


	@Override
	public void visitUnaryMinusNode(DoubleParseTreeNode rightEvaluable) throws ExpressionError {
		visitOp("minus", null, rightEvaluable);
	}


	@Override
	public void visitDivideNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) throws ExpressionError {
		visitOp("divide", leftEvaluable, rightEvaluable);
	}
	
	@Override
	public void visitModuloNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) throws ExpressionError {
		visitOp("modulo", leftEvaluable, rightEvaluable);
	}

	
	
	

	@Override
	public void visitNotEqualsNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) {
		// TODO Auto-generated method stub
		E.missing();
	}

	@Override
	public void visitAndNode(BooleanParseTreeNode leftEvaluable, BooleanParseTreeNode rightEvaluable) {
		// TODO Auto-generated method stub	
		E.missing();
	}
	 

	@Override
	public void visitLessThanOrEqualsNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) {
		// TODO Auto-generated method stub	
		E.missing();
	}
 
	@Override
	public void visitLessThanNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) {
		// TODO Auto-generated method stub	
		E.missing();
	}

	@Override
	public void visitGreaterThanOrEqualsNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) {
		// TODO Auto-generated method stub
		E.missing();
	}


	@Override
	public void visitGreaterThanNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) {
		E.missing();
	}


	@Override
	public void visitEqualsNode(DoubleParseTreeNode leftEvaluable, DoubleParseTreeNode rightEvaluable) {
		E.missing();
	}
	
}
