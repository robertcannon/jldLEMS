package org.lemsml.jlems.core.lite;

public class Connection {

	
	int srcList;
	int srcIndex;
	
	int tgtList;
	int tgtIndex;
	
	
	public Connection(int ia, int ib, int ic, int id) {
		srcList = ia;
		srcIndex = ib;
		
		tgtList = ic;
		tgtIndex = id;
	}
	
	public int getSourceListIndex() {
		return srcList;
	}
	
	public int getSourceCellIndex() {
		return srcIndex;
	}

}
