package org.lemsml.jld.plot;

import java.util.ArrayList;
import java.util.HashMap;

public class DisplayList {

	Repaintable repaintable;
	
	
	ArrayList<DisplayLine> lines;
	HashMap<String, DisplayLine> lineHM;
	
	
	public DisplayList() {
		lines = new ArrayList<DisplayLine>();
		lineHM = new HashMap<String, DisplayLine>();
	}
 	
	public void setRepaintable(Repaintable rp) {
		repaintable = rp;		
	}

	public ArrayList<DisplayLine> getLines() {
		return lines;
	}
	
	public void addPoint(String sl, double x, double y) {
		 addPoint(sl, x, y, null);
	}

	public void addPoint(String sl, double x, double y, String color) {
		if (lineHM.containsKey(sl)) {
			lineHM.get(sl).addPoint(x, y);
			
		} else {
			DisplayLine dl = new DisplayLine(sl, color);
			lineHM.put(sl, dl);
			lines.add(dl);
			dl.addPoint(x, y);
		}
		
	}

	public void addLine(double[] xp, double[] yp, String color) {
		DisplayLine dl = new DisplayLine("", color);
		dl.setPoints(xp, yp);
		lines.add(dl);
	}

	public void clear() {
		lines = new ArrayList<DisplayLine>();
		lineHM = new HashMap<String, DisplayLine>();
	}

	
	public String getStringData() {
		StringBuilder sb = new StringBuilder();
		sb.append("x");
		for (DisplayLine dl : lines) {
			sb.append(", " + dl.getName());
		}
		sb.append("\n");
		if (lines.size() > 0) {
			DisplayLine dl = lines.get(0);
	
			int n = dl.getNpts();
			double[] xpts = dl.getXpts();
			
			double dx = 1;
			if (n > 0) {
				dx = xpts[1] - xpts[0];
			}
			double rdx = 1. / dx;
			
			for (int i = 0; i < n; i++) {
				sb.append("" + xpts[i]);
				
				for (DisplayLine adl : lines) {
					sb.append(", " + adl.getIfAt(i, xpts[i], rdx) + "");
				}
				sb.append("\n");
			}
		}
		return sb.toString();
		
	}
	
	
}
