package org.lemsml.jlems.core.lite;

public class CAConnections {

	CellConnections[] cellConnections = new CellConnections[50];
	
	
	public void addConnection(Connection conn) {

		
		int srcCellIndex = conn.getSourceCellIndex();
		
		if (srcCellIndex > cellConnections.length) {
			int nn = (3 * srcCellIndex) / 2;
			CellConnections[] cc = new CellConnections[nn];
			for (int i = 0; i < cellConnections.length; i++) {
				if (cellConnections[i] != null) {
					cc[i] = cellConnections[i];
				}
			}
			cellConnections = cc;
		}
		if (cellConnections[srcCellIndex] == null) {
			cellConnections[srcCellIndex] = new CellConnections();
		}
		cellConnections[srcCellIndex].addConnection(conn);
		
		// TODO Auto-generated method stub
		
	}

}
