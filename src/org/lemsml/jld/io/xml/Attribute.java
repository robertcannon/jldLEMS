
package org.lemsml.jld.io.xml;



public interface Attribute {

	String getName();
	   
	String getValue(); 

	void setFlag();
	
	void clearFlag();
	
    boolean flagged();
	
}
