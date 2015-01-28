package org.lemsml.jld.hsim;

import java.util.ArrayList;
import java.util.List;

import org.lemsml.jld.hrun.Builder;
import org.lemsml.jld.hrun.BuilderElement;
import org.lemsml.jld.hrun.EventConnectionBuilder;
import org.lemsml.jld.hrun.ForEachBuilder;
import org.lemsml.jld.hrun.MultiBuilder;
import org.lemsml.jld.hrun.RuntimeError;
import org.lemsml.jld.hrun.StateType;

import org.lemsml.jld.imodel.IComponent;
import org.lemsml.jld.imodel.structure.IEventConnection;
import org.lemsml.jld.imodel.structure.IForEach;
import org.lemsml.jld.imodel.structure.IMultiInstance;
import org.lemsml.jld.imodel.structure.IStructure;
import org.lemsml.jld.imodel.structure.IStructureBlock;
import org.lemsml.jld.io.E;

import org.lemsml.jld.path.PathEvaluator;


public class StructureBuilder {

	StateTypeBuilder stateTypeBuilder;
	
	public StructureBuilder(StateTypeBuilder sb) {
		stateTypeBuilder = sb;
	}
	
	
	
	
	
	
	public Builder makeStructureBuilder(IStructure str, IComponent target) {
		Builder b = new Builder();
		
	 	
		List<BuilderElement> abe = makeChildBuilders(str, target);
		for (BuilderElement be : abe) {
			b.add(be);
		}
		 
		return b;
	}

	private BuilderElement makeMultiInstanceBuilder(IMultiInstance mi, IComponent target) {
		MultiBuilder mb = null;
		
		StateType cb = null;
		if (target != null) {
			IComponent c = target.getIChild(mi.getComponent());
			if (c != null) {
				cb = stateTypeBuilder.getOrMakeStateType(c);
			}
		}
		if (cb == null) {
			IComponent c = stateTypeBuilder.getIComponent(mi.getComponent());
		}
		if (cb == null) {
			E.error("Can't locate component in multibuilder: " + mi.getComponent());
		
		} else {
		 
		int n = (int)Math.round(target.getNumericalParameterValue(mi.getNumber()));
		
		mb = new MultiBuilder(n, cb);
		
		// mb.setIndexVariable(mi.getIndexVariable());
		
		/* TODO
		for (Assign ass : assigns) {
			String cea = null;
			String ea = ass.getExposeAs();
			if (ea != null) {
				cea = cpt.getTextParam(ea);
			}
			// TODO - assigns don't know thier dimenison?
			E.missing("Dont' know dimension in assigns");
			String dim = "unknown";
			
			mb.addAssignment(ass.getProperty(), ass.getDoubleEvaluator(), cea, dim);
		}
		*/
	}
		return mb;	
	
	}
	
	
	
	public BuilderElement makeEventConnectionBuilder(IEventConnection ec, IComponent cpt) {

        //E.info("makeBuilder on "+cpt+" from: "+from+" ("+sourcePort+"), to: "+to+" ("+targetPort+") -> "+ receiver +", assigns: "+assigns);
		EventConnectionBuilder ret = new EventConnectionBuilder(ec.getFrom(), ec.getTo());
	
		E.info("********* makung ec builkder");
		
		String sourcePort = ec.getSourcePort();
		
		if (sourcePort != null)  {
			String s = cpt.getStringParameterValue(sourcePort);
			if (s != null) {
				ret.setSourcePortID(s);
			}
		}
		
		String targetPort = ec.getTargetPort();
		if (targetPort != null) {
			String s = cpt.getStringParameterValue(targetPort);
			if (s != null) {
				ret.setTargetPortID(s);
			}
		}
		
		String delay = ec.getDelay();
		if (delay != null) {
			double d = cpt.getNumericalParameterValue(delay);
			if (d != 0.) {
				ret.setDelay(d);
			}
		}
		//System.out.println("cpt: "+cpt+", atr: "+cpt.attributes);
              
		String receiver = ec.getReceiver();
		if (receiver != null) {
			
			// TODO maybe receiver is relative to us?
			IComponent receiverComponent = stateTypeBuilder.getIComponent(receiver);
			
			// IComponent receiverComponent = cpt.getRelativeComponent(receiver);
            //E.info("EventConnection, receiver: ["+receiver+"] resolved to: ["+receiverComponent+"]");
			StateType rst = stateTypeBuilder.getOrMakeStateType(receiverComponent);
			ret.setReceiverStateType(rst);

			/*  TODO reinstate
            for (Assign ass : assigns) {
                String ea = ass.getExposeAs();
                if (ea != null) {
                    E.warning("Expose as in EventConnection is not used");
                 }
                String dim = "unknown";
                E.missing("No dimensions on assigns");
                ret.addAssignment(ass.getProperty(), ass.getDoubleEvaluator(), dim);
            }
            */
		}

		String rc = ec.getReceiverContainer();
		if (rc != null) {
			String sv = cpt.getStringParameterValue(rc);
			if (sv != null) {
				ret.setReceiverContainer(sv);
			}
		}
	
		return ret;
	}
	
	
	public BuilderElement makeForEachBuilder(IForEach fe, IComponent cpt) {
			BuilderElement ret = null;
		
	     //E.info("ForEach makeBuilder on: " + cpt+", instances: "+instances+", as: "+ as);
			PathEvaluator pe = new PathEvaluator(null, cpt, fe.getInstances());
			
			try {
				String insval = pe.getStringValue();	
			
			
			// E.info("ForEeach instances value in builder: " + insval);
			
			String as = fe.getAs();
			ret = new ForEachBuilder(insval, as);
			
			ArrayList<BuilderElement> abe = makeChildBuilders(fe, cpt);
			for (BuilderElement be: abe) {
				ret.add(be);
			}
			} catch (RuntimeError e) {
				E.error("Cant make ForEach builder " + e);
			}
			return ret;
	}
	
	
	public ArrayList<BuilderElement> makeChildBuilders(IStructureBlock asb, IComponent target) {
		ArrayList<BuilderElement> ret = new ArrayList<BuilderElement>();
	
		for (IMultiInstance mi : asb.getIMultiInstances()) {
			BuilderElement bde = makeMultiInstanceBuilder(mi, target);
			ret.add(bde);
		}
		
		for (IForEach fe : asb.getIForEachs()) {
			BuilderElement bde = makeForEachBuilder(fe, target);
			ret.add(bde);
		}
		
		for (IEventConnection ec : asb.getIEventConnections()) {
			BuilderElement bde = makeEventConnectionBuilder(ec, target);
			ret.add(bde);
		}
		return ret;
	}
	
	
	
}
