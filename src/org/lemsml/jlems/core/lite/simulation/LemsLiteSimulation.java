package org.lemsml.jlems.core.lite.simulation;

import java.util.ArrayList;
import java.util.HashMap;

import org.lemsml.jlems.core.display.DataViewer;
import org.lemsml.jlems.core.display.DataViewerFactory;
import org.lemsml.jlems.core.expression.ExpressionParser;
import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.expression.ParseTree;
import org.lemsml.jlems.core.lite.convert.DUComponentToDUStateType;
import org.lemsml.jlems.core.lite.model.Arg;
import org.lemsml.jlems.core.lite.model.Array;
import org.lemsml.jlems.core.lite.model.ComponentArray;
import org.lemsml.jlems.core.lite.model.ConnectionProperties;
import org.lemsml.jlems.core.lite.model.DataSources;
import org.lemsml.jlems.core.lite.model.Delay;
import org.lemsml.jlems.core.lite.model.DiscreteUpdateComponent;
import org.lemsml.jlems.core.lite.model.Display;
import org.lemsml.jlems.core.lite.model.EventArguments;
import org.lemsml.jlems.core.lite.model.EventConnections;
import org.lemsml.jlems.core.lite.model.File;
import org.lemsml.jlems.core.lite.model.FileContent;
import org.lemsml.jlems.core.lite.model.FileSource;
import org.lemsml.jlems.core.lite.model.FromArrayConnector;
import org.lemsml.jlems.core.lite.model.Initialize;
import org.lemsml.jlems.core.lite.model.LemsLite;
import org.lemsml.jlems.core.lite.model.Let;
import org.lemsml.jlems.core.lite.model.Property;
import org.lemsml.jlems.core.lite.model.Recording;
import org.lemsml.jlems.core.lite.model.Simulation;
import org.lemsml.jlems.core.lite.model.VariableDisplay;
import org.lemsml.jlems.core.lite.model.VariableRecording;
import org.lemsml.jlems.core.lite.run.component.Assignment;
import org.lemsml.jlems.core.lite.run.component.DiscreteUpdateStateInstance;
import org.lemsml.jlems.core.lite.run.component.DiscreteUpdateStateType;
import org.lemsml.jlems.core.lite.run.network.Connection;
import org.lemsml.jlems.core.lite.run.network.EventBuilder;
import org.lemsml.jlems.core.lite.run.network.EventManager;
import org.lemsml.jlems.core.lite.run.network.InstanceArray;
import org.lemsml.jlems.core.lite.run.network.MultiConnectionProperty;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.out.RecWriterFactory;
import org.lemsml.jlems.core.run.ConnectionError;
import org.lemsml.jlems.core.run.RuntimeError;
import org.lemsml.jlems.core.sim.ContentError;

public class LemsLiteSimulation {

	LemsLite lemsLite;
	
	
	ArrayList<InstanceArray> instanceArrays;
	HashMap<String, InstanceArray> instanceArrayHM;
	
	
	double maxDelay = 0;
  	
	EventManager eventManager;
	
	Simulation simulation;
	
	HashMap<String, RecWriter> recHM = new HashMap<String, RecWriter>();
	ArrayList<RecWriter> recWriters = new ArrayList<RecWriter>();

	
	ArrayList<RecDisplay> recDisplays = new ArrayList<RecDisplay>();
	
	// transients while setting up network
	HashMap<String, double[]> arrayHM;
	HashMap<String, int[]> intArrayHM;
	
	
	
	public LemsLiteSimulation(LemsLite ll) {
		lemsLite = ll;
		E.info("Made LL simulation");
	}
	
	
	public void run(DataSource dsource) throws ContentError, ParseError, ConnectionError, RuntimeError {
		
		E.info("Building arrays...");
		buildArrays(dsource);
		
		E.info("Instantiating component arrays...");
		buildInstanceArrays();
		
	
		int ctr = 0;
		for (InstanceArray arr : instanceArrays) {
			arr.setIndex(ctr);
			ctr += 1;
		}

		E.info("Making connections...");
	 	
		maxDelay = 0;
		// creating event connections has a side effect of updating maxDelay
		// to the largest delay value found
		createEventConnections();
		
		
	
		E.info("Connecting event manager...");
		eventManager = new EventManager();
		eventManager.setMaxDelay(maxDelay);
		
		for (InstanceArray ia : instanceArrays) {
			ia.connectEventManager(eventManager);
		}
		
		// no longer need arrays
		arrayHM = null;
		intArrayHM = null;
		
		
		simulation = lemsLite.getSimulation();
		if (simulation == null) {
			E.error("No simulation element defined");
		} else {
	 		
			runSimulation();
		}
	}
	
	
	
	private void runSimulation() throws RuntimeError, ContentError {
		
		connectRecorders();
		connectDisplays();
		
		
		
		double trun = simulation.getRuntime();
		double dt = simulation.getTimestep();
		
		eventManager.setTimestep(dt);
		
		int ns = (int)(Math.round(trun / dt));
		
		double t = 0;
		
		
		for (int istep = 0; istep < ns; istep++) {
			for (InstanceArray arr : instanceArrays) {
				arr.advance(t, dt);
			}
			
			//instanceArrays.get(0).dumpState(0);
			// instanceArrays.get(0).dumpState(1);
			
			
			eventManager.advance(t, dt);
			t += dt;
			
			for (RecWriter rw :recWriters) {
				rw.write(t);
			}
			
			for (RecDisplay rw :recDisplays) {
				rw.display(t);
			}
			
			if (istep %50 == 0) {
				E.info("Step " + istep + " t=" + t);
			}
		}
		
		for (RecWriter rw :recWriters) {
			rw.close();
		}
	}
	
	
	
	
	private RecWriter getOutWriter(String fid) throws ContentError {
		RecWriter ret = null;
		Recording rec = simulation.getRecording();
		for (File f :  simulation.getFiles()) {
			String cid = f.getID();
			if (cid != null && cid.equals(fid)) {
				ret = RecWriterFactory.getFactory().newRecWriter(fid, f.getName(), f.getFormat());
			}
		}
		if (ret == null) {
			throw new ContentError("No output file with id " + fid);
		}
		return ret;
	}
	
	
	
	private void connectRecorders() throws ContentError {
		Recording rec = simulation.getRecording();
		if (rec != null) {
			for (VariableRecording vr : rec.getVariableRecordings()) {
				
				String fid = vr.getFileID();
				
				RecWriter rw = null;
				if (recHM.containsKey(fid)) {
					rw = recHM.get(fid);
				} else {
					rw = getOutWriter(fid);
					recHM.put(fid, rw);
					recWriters.add(rw);
				}
				connectWriter(vr, rw);
			}
		}
	}
	
	
	private void connectDisplays() throws ContentError {
		int nd = 0;
		for (Display dec : simulation.getDisplays()) {
			String did = dec.getID("" + nd);
			nd += 1;
			DataViewer dv = DataViewerFactory.getFactory().newDataViewer(did);
			
			for (VariableDisplay vd : dec.getVariableDisplays()) {
				
				RecDisplay rd = new RecDisplay(did, dv, vd.getColor());
				
				recDisplays.add(rd);
				
				connectDisplay(vd, rd);
			}
		}
	}
	
	
	
	
	
	
	private void connectDisplay(VariableDisplay vd, RecDisplay rd) throws ContentError {
		String caid = vd.getComponentArray();
		if (instanceArrayHM.containsKey(caid)) {
			InstanceArray arr = instanceArrayHM.get(caid);
			
			int[] indices = parseIndices(vd.getIndices(), arr.size());

			InstanceWriter iw = new InstanceWriter(arr, vd.getVariable(), indices);
			rd.addWriter(iw);
			
		} else {
			throw new ContentError("No such component array " + caid);
		}
		
	}


	private void connectWriter(VariableRecording vr, RecWriter rw) throws ContentError {
		String caid = vr.getComponentArray();
		if (instanceArrayHM.containsKey(caid)) {
			InstanceArray arr = instanceArrayHM.get(caid);
			
			int[] indices = parseIndices(vr.getIndices(), arr.size());

			InstanceWriter iw = new InstanceWriter(arr, vr.getVariable(), indices);
			rw.addWriter(iw);
			
		} else {
			throw new ContentError("No such component array " + caid);
		}
	}
	
	
	
	private int[] parseIndices(String str, int nel) throws ContentError {
		int[] ret = null;
		if (str == null) {
			ret = new int[nel];
			for (int i = 0; i < nel; i++) {
				ret[i] = i;
			}
			
		} else {
		ArrayList<Integer> wk = new ArrayList<Integer>();
		
		String[] bits;
		if (str.indexOf(",") > 0) {
			bits = str.split(",");
		} else {
			bits = str.split(" ");
		}
		
		for (int i = 0; i < bits.length; i++) {
			String tok = bits[i].trim();
			if (tok.length() > 0) {
			try {
				if (tok.indexOf(":") >= 0) {
					wk.addAll(makeRange(tok, nel));
				
				} else {
					int ind = Integer.parseInt(tok);
					wk.add(ind);
				}
			
			} catch (NumberFormatException ex) {
				E.error("Can't parse indicex " + str);
			}
			}
		}
		
		ret = new int[wk.size()];
		for (int i = 0; i < wk.size(); i++) {
			ret[i] = wk.get(i);
		}
		}
		return ret;
	}
	
	private ArrayList<Integer> makeRange(String str, int nel) throws ContentError {
		String[] bits = str.split(":");
		int step = 1;
		int ifrom = Integer.parseInt(bits[0]);
		int ito = Integer.parseInt(bits[1]);
		if (bits.length > 2) {
			step = Integer.parseInt(bits[2]);
		}
		if (step < 0) {
			throw new ContentError("Wrong sign for step " + ifrom + " " + ito + " " + step);
		}
		
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int i = ifrom; i < ito; i += step) {
			int val = i;
			if (val < 0) {
				val += nel;
			}
			ret.add(val);
		}
		return ret;
	}
	
	
	
	
	private void createEventConnections() throws ContentError, ParseError {
		for (EventConnections ecs : lemsLite.getEventConnectionss()) {
			
			processEventConnections(ecs);
		}
	}
	
	 
	
	
	private void processEventConnections(EventConnections ecs) throws ContentError, ParseError {
		String afrom = ecs.getFrom();
		String ato = ecs.getTo();
		
		if (instanceArrayHM.containsKey(afrom) && instanceArrayHM.containsKey(ato)) {
			// OK
		} else {
			throw new ContentError("Connections refer to unknown array: from=" + afrom + " to=" + ato);
		}
		
		String pfrom = ecs.getSourcePortName();
		String pto = ecs.getTargetPortName();
		
		
		FromArrayConnector facon = ecs.getConnector();
	
		 
		InstanceArray iasrc = getInstanceArray(afrom);
		InstanceArray itgt = getInstanceArray(ato);
		
		int[] preArray = getIntArray(facon.getPre());
		int[] postArray = getIntArray(facon.getPost());
		
		if (preArray.length != postArray.length) {
			throw new ContentError("Mismatched connection arrays " + preArray.length + 
					" " + postArray.length);
		}
		
		ArrayList<MultiConnectionProperty> mcProperties = readConnectionProperties(ecs.getConnectionProperties(), arrayHM);
		
	
		EventArguments ea = ecs.getEventArguments();

		EventBuilder eb = new EventBuilder();
		if (ea != null) {
			
			ExpressionParser expressionParser = new ExpressionParser();
			
			
			for (Arg fa : ea.getArgs()) {			
				ParseTree pt = expressionParser.parseExpression(fa.getExpression());
				Assignment as = new Assignment(fa.getVariableName(), pt.makeFloatEvaluator());
				eb.addAssignment(as);
			}
		}
		
		
		
		for (int i = 0; i < preArray.length; i++) {
			int ipre = preArray[i];
			int ipost = postArray[i];
	
			
			DiscreteUpdateStateInstance srcDUSI = iasrc.getElement(ipre);
			DiscreteUpdateStateInstance tgtDUSI = itgt.getElement(ipost);
			Connection conn = new Connection(tgtDUSI, pto);
			
			if (eb != null) {
				conn.setEventBuilder(eb);
			}
			
			srcDUSI.addConnection(pfrom, conn);
	
			
			for (MultiConnectionProperty mcp : mcProperties) {
				if (mcp.isDelay()) {
					double d = mcp.getValue(i);
					if (d > maxDelay) {
						maxDelay = d;
					}
					conn.setDelay(d);
					
				} else if (mcp.getName() != null && mcp.getName().equals("weight")) {
					conn.setWeight(mcp.getValue(i));
				
				} else {
					conn.setCustomProperty(mcp.getName(), mcp.getValue(i));
				}
			}
			
			// connectionTable.addConnection(conn);
		}
		 
		
	}
	
	
	 
	
	private ArrayList<MultiConnectionProperty> readConnectionProperties(ConnectionProperties connectionProperties, HashMap<String, double[]> arrayHM) throws ContentError {
		ArrayList<MultiConnectionProperty> ret = new ArrayList<MultiConnectionProperty>();
		
		if (connectionProperties != null) {
			Delay d = connectionProperties.getDelay();
			if (d != null) {
				MultiConnectionProperty mcp = new MultiConnectionProperty();
				mcp.setIsDelay();
				if (d.value != null) {
					mcp.setValue(Double.parseDouble(d.value));
				} else if (d.array != null) {
					mcp.setArray(getArray(arrayHM, d.array));
				}
				ret.add(mcp);
			}
			
			for (Property p : connectionProperties.getProperties()) {
				MultiConnectionProperty mcp = new MultiConnectionProperty();
				mcp.setName(p.name);
	 			
				if (p.value != null) {
					mcp.setValue(Double.parseDouble(p.value));
				} else if (p.array != null) {
					mcp.setArray(getArray(arrayHM, p.array));
				}
				ret.add(mcp);
			}
		}
		 
		return ret;
	}


	private int[] getIntArray(String nm) throws ContentError {
		int[] ret = null;
		if (intArrayHM.containsKey(nm)) {
			ret = intArrayHM.get(nm);
		
		} else if (arrayHM.containsKey(nm)) {
			ret = toIntArray(arrayHM.get(nm));
			
		 } else {
			 throw new ContentError("No such data array " + nm);
		 }
		 return ret;
	}

	
	private double[] getArray(HashMap<String, double[]> arrayHM, String nm) throws ContentError {
		 if (arrayHM.containsKey(nm)) {
			 // OK
		 } else {
			 throw new ContentError("No such data array " + nm);
		 }
		 double[] ret= arrayHM.get(nm);
		 return ret;
	}


	private InstanceArray getInstanceArray(String nm) throws ContentError {
		if (instanceArrayHM.containsKey(nm)) {
			// OK
		} else {
			throw new ContentError("No such instance array " + nm);
		}
		InstanceArray ret = instanceArrayHM.get(nm);
		return ret;
	}


	private void buildInstanceArrays() throws ContentError, ParseError, ConnectionError, RuntimeError {
		instanceArrays = new ArrayList<InstanceArray>();
		instanceArrayHM = new HashMap<String, InstanceArray>();
		
		HashMap<String, DiscreteUpdateComponent> ducHM = lemsLite.getComponentMap();
		
		HashMap<String, DiscreteUpdateStateType> dustHM = new HashMap<String, DiscreteUpdateStateType>();
		
		
		
		for (ComponentArray ca : lemsLite.getComponentArrays()) {
			
			String cnm = ca.getComponentName();
			if (ducHM.containsKey(cnm)) {
				
				if (!dustHM.containsKey(cnm)) {
					DiscreteUpdateComponent duc = ducHM.get(cnm);
					DUComponentToDUStateType cdustg = new DUComponentToDUStateType(duc);
					DiscreteUpdateStateType t = cdustg.makeDiscretUpdateStateType();
					dustHM.put(cnm, t);
				}
				
				DiscreteUpdateStateType dust = dustHM.get(cnm);
				
				
				InstanceArray arr = new InstanceArray(ca.getName());
				int nel = ca.getComponentCount();
				arr.populate(dust, nel);
				E.info("Made array " + ca.getName() + " with " + nel + " instances of " + cnm);
				
				
				instanceArrays.add(arr);
				instanceArrayHM.put(ca.getName(), arr);
				
				for (Let let : ca.getLets()) {
					
					String p = let.getParameter();
					String a = let.getArrayName();
					String vstr = let.getValueString();
					
					if (vstr != null) {
						try {
							double d =  Double.parseDouble(vstr);
							arr.setParameter(p, d);
							
						} catch (Exception ex) {
							throw new ContentError("Can only handle simple values in lets, not " + vstr);
						}
						
						
						
					} else if (a != null) {
						if (arrayHM.containsKey(a)) {
							arr.setParameter(p, arrayHM.get(a));
							
						} else {
							throw new ContentError("No such array " + a + " needed by let " + let);
						}		
						
					} else {
						throw new ContentError("Can only handle lets with arrays or simple values so far");
					}
					 
				}
				
				
				
				
				for (Initialize iz : ca.getInitializes()) {
					String p = iz.getVariableName();
					String a = iz.getArrayName();
				
					String vstr = iz.getValueString();
					
					if (vstr != null) {
						try {
							double d =  Double.parseDouble(vstr);
							arr.setParameter(p, d);
							
						} catch (Exception ex) {
							throw new ContentError("Can only handle simple values in lets, not " + vstr);
						}
						
						
						
					} else if (a != null) {
						if (arrayHM.containsKey(a)) {
							arr.setVariable(p, arrayHM.get(a));
							
						} else {
							throw new ContentError("No such array " + a + " needed for initialization " + iz);
						}		
					
					} else {
						throw new ContentError("Can only handle initialization with simple values or arrays so far");
					}
				 
				}
				
				
				
				
			} else {
				throw new ContentError("No such component: " + cnm);
			}
			
		}
	}
	
	
	
	
	
	
	
	private void buildArrays(DataSource dsource) throws ContentError {
		arrayHM = new HashMap<String, double[]>();
		intArrayHM = new HashMap<String, int[]>();
		
		for (DataSources dss : lemsLite.getDataSourcess()) {
			E.info("Processing data source " + dss);
			
			HashMap<String, FileContent> fileHM = new HashMap<String, FileContent>();
			for (File f : dss.getFiles()) {	
				FileContent fc = new FileContent(f.id, dsource);
				fc.setFileName(f.getName());
				fc.setFormat(f.getFormat());
				fc.setShape(f.getShape());
				fileHM.put(f.id, fc);
			}
			
			E.info(fileHM.size() + " data source files");

			
			for (Array array : dss.getArrays()) {
				int n = 0;
				if (array.isInteger()) {
					int[] dat = getIntArrayData(array, fileHM);
					intArrayHM.put(array.getName(), dat);
					n = dat.length;
				} else {
					double[] dat = getArrayData(array, fileHM);				
					arrayHM.put(array.getName(), dat);
					n = dat.length;
				}
				E.info("Added " + (array.isInteger() ? "int" : "double") + " array " + array.getName() + " with " + n + " elements");
			}
		}
	}
	
	
	public double[] getArrayData(Array array, HashMap<String, FileContent> fileHM) throws ContentError {
		double[] ret = null;
		
		if (array.hasFileSource()) {
		
			FileSource fs = array.getFileSource();
			String f = fs.getFileID();
			if (fileHM.containsKey(f)) {
				FileContent fc = fileHM.get(f);
				ret = fc.getDoubleArrayColumn(fs.getColumn());
			} else {
				E.error("No file for " + f);
			}
			
	 		
		} else {
			E.error("No file source?");
			throw new ContentError("Can only handle file data sources in arrays so far");
		}
		return ret;
	}
	
	public int[] getIntArrayData(Array array, HashMap<String, FileContent> fileHM) throws ContentError {
		int[] ret = null;
		
		if (array.hasFileSource()) {
		
			FileSource fs = array.getFileSource();
			String f = fs.getFileID();
			if (fileHM.containsKey(f)) {
				FileContent fc = fileHM.get(f);
				ret = toIntArray(fc.getDoubleArrayColumn(fs.getColumn()));
			} else {
				E.error("No file for " + f);
			}
			
		} else if (array.hasIntListSource()) {
			ret = array.getIntArray();
			
		} else {
			E.error("No file source?");
			throw new ContentError("Can only handle file data sources in arrays so far");
		}
		return ret;
	}
	
	
	private int[] toIntArray(double[] da) {
		int n = da.length;
		int[] ret = new int[n];
		for (int i = 0; i < n; i++) {
			ret[i] = (int)(Math.round(da[i]));
		}
		return ret;
	}
	
}
