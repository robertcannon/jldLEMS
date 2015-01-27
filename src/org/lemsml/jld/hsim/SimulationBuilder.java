package org.lemsml.jld.hsim;

import java.util.List;

import org.lemsml.jld.display.RuntimeOutput;
import org.lemsml.jld.hrun.RunConfig;
import org.lemsml.jld.hrun.RuntimeDisplay;
import org.lemsml.jld.hrun.StateType;
import org.lemsml.jld.imodel.IComponent;
import org.lemsml.jld.imodel.simulation.IDataDisplay;
import org.lemsml.jld.imodel.simulation.IDataWriter;
import org.lemsml.jld.imodel.simulation.IRecord;
import org.lemsml.jld.imodel.simulation.IRun;
import org.lemsml.jld.imodel.simulation.ISimulation;
import org.lemsml.jld.io.E;

public class SimulationBuilder {
	
	StateTypeBuilder stateTypeBuilder;
	
	public SimulationBuilder(StateTypeBuilder sb) {
		stateTypeBuilder = sb;
	}
	
	public void applySimulation(ISimulation sim, IComponent target, StateType ret, int depth) {
		for (IRun run : sim.getIRuns()) {
			String incField = run.getIncrement();
			double dt = target.getNumericalParameterValue(incField);
	 		
			String totField = run.getTotal();
			double trun = target.getNumericalParameterValue(totField);
		 
			String fieldName = run.getComponent();
			

			IComponent runTarget = target.getIChild(fieldName);
 			
			StateType targetST = stateTypeBuilder.getOrMakeStateType(runTarget);
			
			RunConfig rc = new RunConfig(targetST, dt, trun);
			ret.addRunConfig(rc);
 		}
			
	 			
		for (IDataDisplay dd : sim.getIDataDisplays()) {
			final RuntimeDisplay ro = getRuntimeDisplay(dd, target);
			ret.addRuntimeDisplay(ro);
		}
			 
			
			
		for (IDataWriter dw : sim.getIDataWriters()) {
			final RuntimeOutput ro = getRuntimeOutput(dw, target);
			ret.addRuntimeOutput(ro);
		}
		 
			 for (IRecord r : sim.getIRecords()) {
				 final String path = target.getStringParameterValue(r.getQuantity());
				 if (path == null) {
					 E.error("No path specified for recorder (" + r.getQuantity() + ") in " + target);
				 }
				 IComponent cdisp = null;
				 if (r.getDestination() != null) {
					 cdisp = getInheritableLinkTarget(target, r.getDestination());
				 } else {
					 cdisp = target.getParent();
				 }
				 
				 if (cdisp == null) {
					 E.error("No display defined for recorder " + r);		
			
				 } else {
				 if (cdisp.getId() == null) {
					 cdisp.setId(stateTypeBuilder.autoID());
				 }
				 
				 double tsc = 1.;
				 double ysc = 1.;
				 String tscl = r.getTimeScale();
				 if (tscl != null) {
					 double ptsc = target.getNumericalParameterValue(tscl);
					 if (ptsc != 0.) {
						 tsc = ptsc;
					 }
				 }
				 String scl = r.getScale();
				if (scl != null) {
					double pscl = target.getNumericalParameterValue(scl);
					if (pscl != 0.) {
						ysc = pscl;
					}
				}
				String col = target.getStringParameterValue(r.getColor());
				 ret.addRecorder(target.getId(), path, tsc, ysc, col, cdisp.getId());
			 }
			 }
	}

	
	public RuntimeOutput getRuntimeOutput(IDataWriter dw, IComponent cpt) {
		RuntimeOutput ret = new RuntimeOutput();
		ret.setID(cpt.getId());
		String p = cpt.getStringParameterValue(dw.getPath());
		if (p != null) {
			ret.setPath(p);
		}  
		ret.setFileName(cpt.getStringParameterValue(dw.getFileName()));
		return ret;
	}
	
	 
public RuntimeDisplay getRuntimeDisplay(IDataDisplay dd, IComponent cpt) {
	RuntimeDisplay ret = new RuntimeDisplay();
	ret.setID(cpt.getId());
	ret.setTitle(cpt.getStringParameterValue(dd.getTitle()));
	
	String dataRegion = dd.getDataRegion();
	if (dataRegion != null) {
		String[] bits = dataRegion.split(",");
		if (bits.length == 4) {
			double[] box = new double[4];
			for (int i = 0; i < 4; i++) {
				box[i] = cpt.getNumericalParameterValue(bits[i]); 
			}
			ret.setBox(box);
			
		} else {
			E.warning("Expect 4 items in region, " + dataRegion);
		}
	}
	return ret;
	
}


private IComponent getInheritableLinkTarget(IComponent target, String destination) {
		IComponent ret = null;
	
		ret = target.getIChild(destination);
		
		if (ret == null) {
			List<? extends IComponent> ch = target.getIChildren(destination);
			if (ch != null && ch.size() > 0) {
				ret = ch.get(0);
				// TODO - ever relevant, what if more children?
			}
		}
		 
			
		if (ret == null) {
			IComponent p = target.getParent();
			if (p != null) {
				ret = getInheritableLinkTarget(p, destination);
			}
		}
		if (ret == null) {
			E.info("Inheritable - no ref " + destination + " in " + target);
		}  
		return ret;
}


	 
}
