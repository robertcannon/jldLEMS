package org.lemsml.jlems.core.type.dynamics;

import java.util.HashMap;

import org.lemsml.jlems.core.annotation.ModelElement;
import org.lemsml.jlems.core.annotation.ModelProperty;
import org.lemsml.jlems.core.expression.ExpressionParser;
import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.expression.Valued;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.LemsCollection;
import org.lemsml.jlems.core.type.ReceivePort;



@ModelElement(info="Event handler block")
public class OnEvent extends PointResponse {
 
	@ModelProperty(info="the port to listen on")
	public String port;
	
	ReceivePort receivePort;
	  
	 
	public void resolve(Dynamics bhv, LemsCollection<StateVariable> stateVariables, HashMap<String, Valued> valHM, ExpressionParser expressionParser) throws ContentError, ParseError {
	
		receivePort = bhv.getComponentType().getReceivePort(port);
	 
		supResolve(bhv, stateVariables, valHM, expressionParser);
	}




	public String getPortName() {
		return receivePort.getName();
	}




	public OnEvent makeCopy() {
		OnEvent oe = new OnEvent();
		oe.port = port;
		super.copyInto(oe);
		return oe;
	}




	
}
