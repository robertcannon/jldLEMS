package org.lemsml.jld.hrun;

import org.lemsml.jld.display.DataViewer;
import org.lemsml.jld.display.ResultWriter;


public class RuntimeRecorder {

	String id;
	String quantity;
 	String color;
	String display;
	 
	  StateWrapper stateWrapper;

	  // on or the other of these will be set - maybe common interface?
	  DataViewer dataViewer;
	  ResultWriter resultWriter;
	  
	  double tscale;
	  double yscale;
	  

	  public RuntimeRecorder(String q) {
		  this(q, q);
	  }
	  
	  public RuntimeRecorder(String id, String q) {
		  this(id, q, 1., 1., "#ff0000", "");
	  }
	  
	  
	  // TODO too many args
	public RuntimeRecorder(String aid, String q, double tsc, double ysc, String col, String d) {
		 id = aid;
		 quantity = q;
		 tscale = tsc;
		 yscale = ysc;
		 color = col;
		 display = d;
	}

	
	public String toString() {
		return "Recorder, " + id + " of " + quantity + " tscale=" + tscale + " yscale=" + yscale + 
				" display=" + display + " color=" + color;
	}
	

	public String getID() {
		return id;
	}


	public String getDisplay() {
		return display;
	}

 

	public void connectRunnable(RunnableAccessor ra, DataViewer dv) throws ConnectionError, RuntimeError {
		if (quantity == null) {
			throw new ConnectionError("Recorder has null quantity " + toString());
		}
        stateWrapper = ra.getStateWrapper(quantity);
        if (stateWrapper == null) {
            throw new ConnectionError("unable to access state variable: " + quantity);
        }
        dataViewer = dv;
    }
	
	
	public void connectRunnable(RunnableAccessor ra, ResultWriter rw) throws ConnectionError, RuntimeError {
		if (quantity == null) {
			throw new ConnectionError("Recorder has null quantity " + toString());
		}
        stateWrapper = ra.getStateWrapper(quantity);
        if (stateWrapper == null) {
            throw new ConnectionError("unable to access state variable: " + quantity);
        }
        resultWriter = rw;
    }
	

    public void appendState(double ft) throws RuntimeError {

        double x = ft / tscale;
        double y = stateWrapper.getValue() / yscale;
        //E.info("Adding point: ("+x+", "+y+")");

        if (dataViewer != null) {
        	dataViewer.addPoint(id, x, y, color);
        }
        if (resultWriter != null) {
        	resultWriter.addPoint(id, x, y);
        }
    }

    
	public void setColor(String c) {
		color = c;
	}
}
