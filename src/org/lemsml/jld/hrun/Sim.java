package org.lemsml.jld.hrun;
 
import java.util.ArrayList;
import java.util.HashMap;

import org.lemsml.jld.display.DataViewer;
import org.lemsml.jld.display.DataViewerFactory;
import org.lemsml.jld.display.ResultWriter;
import org.lemsml.jld.display.ResultWriterFactory;
import org.lemsml.jld.display.RuntimeOutput;
import org.lemsml.jld.io.E;
 


public class Sim {

   StateType rootBehavior;
    StateType targetBehavior;
    
     
    HashMap<String, DataViewer> dvHM;
    HashMap<String, ResultWriter> rwHM;
    
    ArrayList<ResultWriter> resultWriters = new ArrayList<ResultWriter>();
    
    ArrayList<RunConfig> runConfigs;
    
    int maxExecutionTime = 0;
    
    EventManager eventManager;
    
  
    
    
    public Sim(StateType st) {
    	rootBehavior = st;
    
    	RunConfig rc = rootBehavior.getRunConfig();
    	targetBehavior = rc.getTargetStateType();
    }
 
 	
    	
    public void build() {
    	
    	// applySubstitutions();
    	
    	eventManager = EventManager.getInstance();
        
	    // collect everything in the StateType tree that makes a display
	    ArrayList<RuntimeDisplay> runtimeDisplays = new ArrayList<RuntimeDisplay>();
	    DisplayCollector oc = new DisplayCollector(runtimeDisplays);
	    rootBehavior.visitAll(oc);
	   
	    // build the displays and keep them in dvHM
	    dvHM = new HashMap<String, DataViewer>();
	    for (RuntimeDisplay ro : runtimeDisplays) {
	    	DataViewer dv = DataViewerFactory.getFactory().newDataViewer(ro.getTitle());
	    	dvHM.put(ro.getID(), dv);
	    	dv.setRegion(ro.getBox());
	    }
	     
	 
	    // collect everything in the StateType tree that records something
	    ArrayList<RuntimeOutput> runtimeOutputs = new ArrayList<RuntimeOutput>();
	    OutputCollector oco = new OutputCollector(runtimeOutputs);
	    rootBehavior.visitAll(oco);
	   
	    // build the displays and keep them in dvHM
	    rwHM = new HashMap<String, ResultWriter>();
 	    for (RuntimeOutput ro : runtimeOutputs) {
 	    	ResultWriter rw = ResultWriterFactory.getFactory().newResultWriter(ro);
	    	rwHM.put(ro.getID(), rw);
	    	resultWriters.add(rw);
	    }
	   	    
	    runConfigs = new ArrayList<RunConfig>();
	    RunConfigCollector rcc = new RunConfigCollector(runConfigs);
	    rootBehavior.visitAll(rcc);
	}

    
    public void run() throws RuntimeError, ConnectionError {
    	run(true);
    }
    
    public void runTree() throws  RuntimeError, ConnectionError  {
    	run(false);
    }
    
    private void run(boolean flatten) throws   RuntimeError, ConnectionError  {
     	for (RunConfig rc : runConfigs) {
    		run(rc, flatten);
    	}
      }

     

    
    public void run(RunConfig rc, boolean flatten) throws   RuntimeError, ConnectionError {
    
      
  	    StateInstance rootState = targetBehavior.newInstance();
  	    rootState.checkBuilt();
  	
  	    RunnableAccessor ra = new RunnableAccessor(rootState);
  	       
  	    ArrayList<RuntimeRecorder> recorders = rc.getRecorders();
  	    
  	    
  	    
  	    
  	    for (RuntimeRecorder rr : recorders) {
  	    	String disp = rr.getDisplay();
  	    	if (dvHM.containsKey(disp)) {
  	    		rr.connectRunnable(ra, dvHM.get(disp));
  	    	
  	    	} else if (rwHM.containsKey(disp)) {
  	    		ResultWriter rw = rwHM.get(disp);
  	    		rw.addedRecorder();
  	    		rr.connectRunnable(ra, rw);
  	    		
  	    		E.info("Connected runnable to " + disp + " " + rwHM.get(disp));
  	    		
  	    	} else {
  	    		throw new ConnectionError("No such data viewer " + disp + " needed for " + rr);
  	    	}
  	    }
  	    
        double dt = rc.getTimestep();
        int nstep = (int) Math.round(rc.getRuntime() / dt);

   
        double t = 0;
        
       
        rootState.initialize(null);  
          
        long realTimeStart = System.currentTimeMillis();
        int nsDone = 0;
     
        for (int istep = 0; istep < nstep; istep++) {
        	if (istep > 0) {
        		eventManager.advance(t);
                rootState.advance(null, t, dt);
        	}
        	
        	
        	for (ResultWriter rw : resultWriters) {
        		rw.advance(t);
        	}
        	
        	for (RuntimeRecorder rr : recorders) {
        		rr.appendState(t);
        	}
           
  
            t += dt;
            
            if (maxExecutionTime > 0 && istep % 100 == 0) {
            	long realTimeNow = System.currentTimeMillis();
            	long dtReal = realTimeNow - realTimeStart;
            	if (dtReal > maxExecutionTime) {
            		E.info("Stopped execution at t=" + t + " (exceeded maxExecutionTime) " + dtReal);
            		break;
            	}
            }
            nsDone = istep;
        }
    	
        
        for (ResultWriter rw : resultWriters) {
    		rw.close();
    	}
        
        long end = System.currentTimeMillis();
        E.info("Finished " + nsDone + " steps, end time " + end);
    }

    
	 
	
	public void printRoot() throws  RuntimeError {
		StateTypeWriter cbw = new StateTypeWriter();
		 
		cbw.print(rootBehavior);
	
	}

	public void setMaxExecutionTime(int nms) {
		maxExecutionTime = nms;
	}
	
	
}
