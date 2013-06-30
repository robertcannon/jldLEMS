package org.lemsml.jlems.core.type.dynamics;

import java.util.HashMap;

import org.lemsml.jlems.core.annotation.ModelElement;
import org.lemsml.jlems.core.annotation.ModelProperty;
import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.expression.ExpressionParser;
import org.lemsml.jlems.core.expression.Valued;
import org.lemsml.jlems.core.sim.ContentError;
import org.lemsml.jlems.core.type.EventPort;
import org.lemsml.jlems.core.type.LemsCollection;



@ModelElement(info="Event handler block")
public class OnEvent extends PointResponse {
 
	@ModelProperty(info="the port to listen on")
	public String port;
	
	EventPort eventPort;
	  
	 
	public void resolve(Dynamics bhv, LemsCollection<StateVariable> stateVariables, HashMap<String, Valued> valHM, ExpressionParser expressionParser) throws ContentError, ParseError {
	
		eventPort = bhv.getComponentType().getInEventPort(port);
	 
		supResolve(bhv, stateVariables, valHM, expressionParser);
	}




	public String getPortName() {
		return eventPort.getName();
	}




	public OnEvent makeCopy() {
		OnEvent oe = new OnEvent();
		oe.port = port;
		return oe;
	}




	
}
