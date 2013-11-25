package org.lemsml.jlems.core.lite;

import org.lemsml.jlems.core.logging.E;

public class ConnectionTable {

	// one CAConnections element for each source ComponentArray
	CAConnections[] caConnections; 
	
	
	public ConnectionTable(int nlists) {
		caConnections = new CAConnections[nlists];
	}
	
	
	
	public void addConnection(Connection conn) {
			
		int srcArrayIndex = conn.getSourceListIndex();
	 		
		caConnections[srcArrayIndex].addConnection(conn);
		
		E.missing();
		
	}

}
