package org.lemsml.jlems.nineml;

import org.lemsml.jlems.io.FormatException;
import org.lemsml.jlems.type.dynamics.StateAssignment;

public class NineML_StateAssignment  {

	public String variable;
	public NineML_MathInline mathInline;

 
	
	public StateAssignment getStateAssignment() throws FormatException {	
		StateAssignment sa = new StateAssignment(variable);
		if (mathInline != null) {
			sa.value = mathInline.getFortranFormatBodyValue();
		} else {
			throw new FormatException("No math inline elemnt in " + this);
		}
		
		return sa;
	}
	 

  
}