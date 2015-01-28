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
	
	 
	// IStructure and IForEach are blocks that can contain other elements including blocks
	// Here we make a tree of builders that corresponds to the Structure block in the XML
	// each builder has a 'build' method implementing its logic which also calls any child builders
	
	// the main distinction between the definitions and what goes in the StateType is that the 
	// structure definitions contain the names of the fields in their ComponentType for each of the 
	// properties. The builders that are created here are specific to a particular component, so for 
	// each property we get the field name from the Structure element, look it up in the target
	// component and put that in the builder.
	
	public Builder makeStructureBuilder(IStructure str, IComponent target) {
		Builder b = new Builder();
		
		List<BuilderElement> abe = makeChildBuilders(str, target);
		for (BuilderElement be : abe) {
			b.add(be);
		}
		 
		return b;
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
	
	
	
	
	private BuilderElement makeMultiInstanceBuilder(IMultiInstance mi, IComponent target) {
		MultiBuilder mb = null;
		
		StateType cb = null;
		// mi.getComponent returns the name of the field in the ComponentType that is set 
		// to the target component in the model
		if (target != null) {
			IComponent c = target.getIChild(mi.getComponent());
			if (c != null) {
				cb = stateTypeBuilder.getOrMakeStateType(c);
			}
		}
		if (cb == null) {
			IComponent c = stateTypeBuilder.getIComponent(mi.getComponent());
			cb = stateTypeBuilder.getOrMakeStateType(c);
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
			// the ForEach builder will look up each of the instances targeted by the instances
			// path and run the child builders on them
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
	
	
	
	
}
