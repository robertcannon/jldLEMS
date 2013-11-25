package org.lemsml.jlems.core.lite;

import java.util.ArrayList;
import java.util.HashMap;

import org.lemsml.jlems.core.expression.ParseError;
import org.lemsml.jlems.core.lite.model.Array;
import org.lemsml.jlems.core.lite.model.ComponentArray;
import org.lemsml.jlems.core.lite.model.ConnectionProperties;
import org.lemsml.jlems.core.lite.model.DataSources;
import org.lemsml.jlems.core.lite.model.Delay;
import org.lemsml.jlems.core.lite.model.DiscreteUpdateComponent;
import org.lemsml.jlems.core.lite.model.EventConnections;
import org.lemsml.jlems.core.lite.model.File;
import org.lemsml.jlems.core.lite.model.FileSource;
import org.lemsml.jlems.core.lite.model.FromArrayConnector;
import org.lemsml.jlems.core.lite.model.Initialize;
import org.lemsml.jlems.core.lite.model.LemsLite;
import org.lemsml.jlems.core.lite.model.Let;
import org.lemsml.jlems.core.lite.model.Property;
import org.lemsml.jlems.core.lite.run.DiscreteUpdateStateType;
import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.core.run.ConnectionError;
import org.lemsml.jlems.core.run.RuntimeError;
import org.lemsml.jlems.core.sim.ContentError;

public class LemsLiteSimulation {

	LemsLite lemsLite;
	
	
	ArrayList<InstanceArray> instanceArrays;
	HashMap<String, InstanceArray> instanceArrayHM;
	
	ConnectionTable connectionTable;
	
	ConnectionManager connectionManager;
	
	
	
	public LemsLiteSimulation(LemsLite ll) {
		lemsLite = ll;
		E.info("Made LL simulation");
	}
	
	
	public void run(java.io.File rootDir) throws ContentError, ParseError, ConnectionError, RuntimeError {
		
		E.info("Building arrays...");
		HashMap<String, double[]> arrayHM = buildArrays(rootDir);
		
		E.info("Instantiating component arrays...");
		buildInstanceArrays(arrayHM);
		
		

		int ctr = 0;
		for (InstanceArray arr : instanceArrays) {
			arr.setIndex(ctr);
			ctr += 1;
		}

		E.info("Making connections...");
		connectionTable = new ConnectionTable(ctr);
		
		
		createEventConnections(arrayHM);
		
		
	
		E.info("Running simulation...");
	
		EventManager em = new EventManager(connectionTable);
		for (InstanceArray ia : instanceArrays) {
			ia.connectEventManager(em);
		}
		arrayHM = null;
		
		
		
	}
	
	
	private void createEventConnections(HashMap<String, double[]> arrayHM) throws ContentError {
		for (EventConnections ecs : lemsLite.getEventConnectionss()) {
			
			processEventConnections(ecs, arrayHM);
		}
	}
	
	 
	
	
	private void processEventConnections(EventConnections ecs, HashMap<String, double[]> arrayHM) throws ContentError {
		String afrom = ecs.getFrom();
		String ato = ecs.getTo();
		
		if (instanceArrayHM.containsKey(afrom) && instanceArrayHM.containsKey(ato)) {
			// OK
		} else {
			throw new ContentError("Connections refer to unknown array: from=" + afrom + " to=" + ato);
		}
		
		String pfrom = ecs.getSourcePortName();
		String pto = ecs.getTargetPortName();
		
		
		FromArrayConnector con = ecs.getConnector();
	
		 
		InstanceArray iasrc = getInstanceArray(pfrom);
		InstanceArray itgt = getInstanceArray(pto);
		
		double[] preArray = getArray(arrayHM, con.getPre());
		double[] postArray = getArray(arrayHM, con.getPost());
		
		if (preArray.length != postArray.length) {
			throw new ContentError("Mismatched connection arrays " + preArray.length + 
					" " + postArray.length);
		}
		
		ArrayList<MultiConnectionProperty> mcProperties = readConnectionProperties(ecs.getConnectionProperties(), arrayHM);
		
		
		for (int i = 0; i < preArray.length; i++) {
			int ipre = (int)(Math.round(preArray[i]));
			int ipost = (int)(Math.round(postArray[i]));
			
			
			Connection conn = new Connection(iasrc.getIndex(), ipre, itgt.getIndex(), ipost);
		 
			
			for (MultiConnectionProperty mcp : mcProperties) {
				if (mcp.isDelay()) {
					con.setDelay(mcp.getValue(i));
				} else if (mcp.getName() != null && mcp.getName().equals("weight")) {
					con.setWeight(mcp.getValue(i));
				} else {
					con.setCustomProperty(mcp.getName(), mcp.getValue(i));
				}
			}
			
			connectionTable.addConnection(conn);
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
					mcp.setValue(Double.parseDouble(d.value));
				} else if (p.array != null) {
					mcp.setArray(getArray(arrayHM, p.array));
				}
			}
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


	private void buildInstanceArrays(HashMap<String, double[]> arrayHM) throws ContentError, ParseError, ConnectionError, RuntimeError {
		instanceArrays = new ArrayList<InstanceArray>();
		instanceArrayHM = new HashMap<String, InstanceArray>();
		
		HashMap<String, DiscreteUpdateComponent> ducHM = lemsLite.getComponentMap();
		
		HashMap<String, DiscreteUpdateStateType> dustHM = new HashMap<String, DiscreteUpdateStateType>();
		
		
		
		for (ComponentArray ca : lemsLite.getComponentArrays()) {
			
			String cnm = ca.getComponentName();
			if (ducHM.containsKey(cnm)) {
				
				if (!dustHM.containsKey(cnm)) {
					DiscreteUpdateComponent duc = ducHM.get(cnm);
					DiscreteUpdateStateType t = new DiscreteUpdateStateType(duc);
					t.resolve();
					dustHM.put(cnm, t);
				}
				
				DiscreteUpdateStateType dust = dustHM.get(cnm);
				
				
				InstanceArray arr = new InstanceArray(cnm);
				int nel = ca.getComponentCount();
				arr.populate(dust, nel);
				E.info("Made array " + ca.getName() + " with " + nel + " instances of " + cnm);
				
				
				instanceArrays.add(arr);
				instanceArrayHM.put(ca.getName(), arr);
				
				for (Let let : ca.getLets()) {
					
					String p = let.getParameter();
					String a = let.getArrayName();
					if (a == null) {
						throw new ContentError("Can only handle lets with arrays so far");
					}
					if (arrayHM.containsKey(a)) {
						arr.setParameter(p, arrayHM.get(a));
						
					} else {
						throw new ContentError("No such array " + a + " needed by let " + let);
					}		
				}
				
				for (Initialize iz : ca.getInitializes()) {
					String p = iz.getVariableName();
					String a = iz.getArrayName();
					if (a == null) {
						throw new ContentError("Can only handle lets with arrays so far");
					}
					if (arrayHM.containsKey(a)) {
						arr.setVariable(p, arrayHM.get(a));
						
					} else {
						throw new ContentError("No such array " + a + " needed for initialization " + iz);
					}		
				}
				
				
				
				
			} else {
				throw new ContentError("No such component: " + cnm);
			}
			
		}
	}
	
	
	
	
	
	
	
	private HashMap<String, double[]> buildArrays(java.io.File rootDir) throws ContentError {
		HashMap<String, double[]> ret = new HashMap<String, double[]>();
		
		
		for (DataSources dss : lemsLite.getDataSourcess()) {
			E.info("Processing data source " + dss);
			
			HashMap<String, FileContent> fileHM = new HashMap<String, FileContent>();
			for (File f : dss.getFiles()) {	
				FileContent fc = new FileContent(f.id, rootDir);
				fc.setFileName(f.getName());
				fc.setFormat(f.getFormat());
				fc.setShape(f.getShape());
				fileHM.put(f.id, fc);
			}
			
			E.info(fileHM.size() + " data source files");

			
			for (Array array : dss.getArrays()) {
				double[] dat = getArrayData(array, fileHM);
				
				ret.put(array.getName(), dat);
				E.info("Added array " + array.getName() + " with " + dat.length + " elements");
			}
		}
	 	return ret;
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
	
	
	
}
