package org.lemsml.jlems.core.lite;

public class CellConnections {

	int ncon = 0;
	Connection[] connections = new Connection[4];
	
	
	public void addConnection(Connection conn) {
		if (ncon == connections.length) {
			Connection[] a = new Connection[(3 * ncon) / 2];
			for (int i = 0; i < ncon; i++) {
				a[i] = connections[i];
			}
			connections = a;
		}
		connections[ncon] = conn;
		ncon += 1;
 	}

}
