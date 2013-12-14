package org.lemsml.jlems.core.lite.run.network;

import java.util.ArrayList;


public class ConnectionArray {

	
	ArrayList<Connection> connections = new ArrayList<Connection>();
	
	public ConnectionArray() {
		
	}

	public void add(Connection c) {
		connections.add(c);
	}

	public ArrayList<Connection> getConnections() {
		return connections;
	}
	
	public void clear() {
		connections.clear();
	}

	public int size() {
		 return connections.size();
	}
	
}
