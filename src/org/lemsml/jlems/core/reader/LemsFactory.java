package org.lemsml.jlems.core.reader;

import org.lemsml.jlems.core.logging.E;
// NB this is generated code. Don't edit it. If there is a problem, fix the superclass,
// the generator - org.lemsml.jlems.schema.LemsFactoryGenerator, or the class being instantiated.
import org.lemsml.jlems.core.numerics.Gradient;
import org.lemsml.jlems.core.numerics.GradientStateIncrement;
import org.lemsml.jlems.core.numerics.IntegrationScheme;
import org.lemsml.jlems.core.numerics.IntegrationStep;
import org.lemsml.jlems.core.numerics.MeanGradient;
import org.lemsml.jlems.core.numerics.MeanGradientComponent;
import org.lemsml.jlems.core.numerics.WorkState;
import org.lemsml.jlems.core.type.About;
import org.lemsml.jlems.core.type.Assertion;
import org.lemsml.jlems.core.type.Attachments;
import org.lemsml.jlems.core.type.CableCell;
import org.lemsml.jlems.core.type.Child;
import org.lemsml.jlems.core.type.Children;
import org.lemsml.jlems.core.type.Collection;
import org.lemsml.jlems.core.type.Component;
import org.lemsml.jlems.core.type.ComponentReference;
import org.lemsml.jlems.core.type.ComponentRequirement;
import org.lemsml.jlems.core.type.ComponentType;
import org.lemsml.jlems.core.type.ComponentTypeReference;
import org.lemsml.jlems.core.type.Constant;
import org.lemsml.jlems.core.type.DeprecatedElement;
import org.lemsml.jlems.core.type.DerivedParameter;
import org.lemsml.jlems.core.type.Dimension;
import org.lemsml.jlems.core.type.EventPort;
import org.lemsml.jlems.core.type.Exposure;
import org.lemsml.jlems.core.type.Fixed;
import org.lemsml.jlems.core.type.IndexParameter;
import org.lemsml.jlems.core.type.InputSource;
import org.lemsml.jlems.core.type.Insertion;
import org.lemsml.jlems.core.type.InstanceRequirement;
import org.lemsml.jlems.core.type.IntegerParameter;
import org.lemsml.jlems.core.type.Lems;
import org.lemsml.jlems.core.type.Link;
import org.lemsml.jlems.core.type.LocalParameters;
import org.lemsml.jlems.core.type.Location;
import org.lemsml.jlems.core.type.Meta;
import org.lemsml.jlems.core.type.PairCollection;
import org.lemsml.jlems.core.type.Parameter;
import org.lemsml.jlems.core.type.Path;
import org.lemsml.jlems.core.type.PathParameter;
import org.lemsml.jlems.core.type.Property;
import org.lemsml.jlems.core.type.ReceivePort;
import org.lemsml.jlems.core.type.Requirement;
import org.lemsml.jlems.core.type.SendPort;
import org.lemsml.jlems.core.type.SimulatorShortcut;
import org.lemsml.jlems.core.type.Target;
import org.lemsml.jlems.core.type.Text;
import org.lemsml.jlems.core.type.Unit;
import org.lemsml.jlems.core.type.dynamics.Case;
import org.lemsml.jlems.core.type.dynamics.ConditionalDerivedVariable;
import org.lemsml.jlems.core.type.dynamics.DerivedPunctateField;
import org.lemsml.jlems.core.type.dynamics.DerivedScalarField;
import org.lemsml.jlems.core.type.dynamics.DerivedVariable;
import org.lemsml.jlems.core.type.dynamics.Dynamics;
import org.lemsml.jlems.core.type.dynamics.Equilibrium;
import org.lemsml.jlems.core.type.dynamics.EventOut;
import org.lemsml.jlems.core.type.dynamics.IfCondition;
import org.lemsml.jlems.core.type.dynamics.KineticScheme;
import org.lemsml.jlems.core.type.dynamics.OnCondition;
import org.lemsml.jlems.core.type.dynamics.OnEntry;
import org.lemsml.jlems.core.type.dynamics.OnEvent;
import org.lemsml.jlems.core.type.dynamics.OnStart;
import org.lemsml.jlems.core.type.dynamics.Regime;
import org.lemsml.jlems.core.type.dynamics.StateAssignment;
import org.lemsml.jlems.core.type.dynamics.StateScalarField;
import org.lemsml.jlems.core.type.dynamics.StateVariable;
import org.lemsml.jlems.core.type.dynamics.Super;
import org.lemsml.jlems.core.type.dynamics.TimeDerivative;
import org.lemsml.jlems.core.type.dynamics.Transition;
import org.lemsml.jlems.core.type.geometry.Frustum;
import org.lemsml.jlems.core.type.geometry.Geometry;
import org.lemsml.jlems.core.type.geometry.ScalarField;
import org.lemsml.jlems.core.type.geometry.Skeleton;
import org.lemsml.jlems.core.type.geometry.Solid;
import org.lemsml.jlems.core.type.procedure.AbstractStatement;
import org.lemsml.jlems.core.type.procedure.Equilibrate;
import org.lemsml.jlems.core.type.procedure.ForEachComponent;
import org.lemsml.jlems.core.type.procedure.Print;
import org.lemsml.jlems.core.type.procedure.Procedure;
import org.lemsml.jlems.core.type.simulation.DataDisplay;
import org.lemsml.jlems.core.type.simulation.DataWriter;
import org.lemsml.jlems.core.type.simulation.Record;
import org.lemsml.jlems.core.type.simulation.Run;
import org.lemsml.jlems.core.type.simulation.Simulation;
import org.lemsml.jlems.core.type.structure.Apply;
import org.lemsml.jlems.core.type.structure.Assign;
import org.lemsml.jlems.core.type.structure.BuildElement;
import org.lemsml.jlems.core.type.structure.ChildInstance;
import org.lemsml.jlems.core.type.structure.Choose;
import org.lemsml.jlems.core.type.structure.CoInstantiate;
import org.lemsml.jlems.core.type.structure.EventConnection;
import org.lemsml.jlems.core.type.structure.ForEach;
import org.lemsml.jlems.core.type.structure.Gather;
import org.lemsml.jlems.core.type.structure.GatherPairs;
import org.lemsml.jlems.core.type.structure.If;
import org.lemsml.jlems.core.type.structure.IncludePair;
import org.lemsml.jlems.core.type.structure.Instance;
import org.lemsml.jlems.core.type.structure.MultiInstance;
import org.lemsml.jlems.core.type.structure.MultiInstantiate;
import org.lemsml.jlems.core.type.structure.PairFilter;
import org.lemsml.jlems.core.type.structure.PairsEventConnection;
import org.lemsml.jlems.core.type.structure.Structure;
import org.lemsml.jlems.core.type.structure.Tunnel;
import org.lemsml.jlems.core.type.structure.With;
import org.lemsml.jlems.core.type.visualization.Circle;
import org.lemsml.jlems.core.type.visualization.DrawingConnector;
import org.lemsml.jlems.core.type.visualization.DrawingElement;
import org.lemsml.jlems.core.type.visualization.LinkSourceConnector;
import org.lemsml.jlems.core.type.visualization.LinkTargetConnector;
import org.lemsml.jlems.core.type.visualization.Oval;
import org.lemsml.jlems.core.type.visualization.PolyFill;
import org.lemsml.jlems.core.type.visualization.PolyLine;
import org.lemsml.jlems.core.type.visualization.Rectangle;
import org.lemsml.jlems.core.type.visualization.Visualization;
import org.lemsml.jlems.core.xml.XMLAttribute;
import org.lemsml.jlems.core.xml.XMLElement;

public class LemsFactory extends AbstractLemsFactory {


    public Object instantiateFromXMLElement(XMLElement xel) {
        Object ret = null;
       String tag = xel.getName();

        if (tag.equals("UNUSED")) {
        } else if (tag.equals("Lems")) {
            ret = buildLems(xel);
        } else if (tag.equals("Target")) {
            ret = buildTarget(xel);
        } else if (tag.equals("Constant")) {
            ret = buildConstant(xel);
        } else if (tag.equals("Dimension")) {
            ret = buildDimension(xel);
        } else if (tag.equals("Unit")) {
            ret = buildUnit(xel);
        } else if (tag.equals("Assertion")) {
            ret = buildAssertion(xel);
        } else if (tag.equals("Component")) {
            ret = buildComponent(xel);
        } else if (tag.equals("ComponentType")) {
            ret = buildComponentType(xel);
        } else if (tag.equals("Parameter")) {
            ret = buildParameter(xel);
        } else if (tag.equals("PathParameter")) {
            ret = buildPathParameter(xel);
        } else if (tag.equals("LocalParameters")) {
            ret = buildLocalParameters(xel);
        } else if (tag.equals("Property")) {
            ret = buildProperty(xel);
        } else if (tag.equals("DerivedParameter")) {
            ret = buildDerivedParameter(xel);
        } else if (tag.equals("Fixed")) {
            ret = buildFixed(xel);
        } else if (tag.equals("Requirement")) {
            ret = buildRequirement(xel);
        } else if (tag.equals("ComponentRequirement")) {
            ret = buildComponentRequirement(xel);
        } else if (tag.equals("InstanceRequirement")) {
            ret = buildInstanceRequirement(xel);
        } else if (tag.equals("Exposure")) {
            ret = buildExposure(xel);
        } else if (tag.equals("Child")) {
            ret = buildChild(xel);
        } else if (tag.equals("Children")) {
            ret = buildChildren(xel);
        } else if (tag.equals("ComponentReference")) {
            ret = buildComponentReference(xel);
        } else if (tag.equals("Link")) {
            ret = buildLink(xel);
        } else if (tag.equals("ComponentTypeReference")) {
            ret = buildComponentTypeReference(xel);
        } else if (tag.equals("Collection")) {
            ret = buildCollection(xel);
        } else if (tag.equals("PairCollection")) {
            ret = buildPairCollection(xel);
        } else if (tag.equals("SendPort")) {
            ret = buildSendPort(xel);
        } else if (tag.equals("ReceivePort")) {
            ret = buildReceivePort(xel);
        } else if (tag.equals("EventPort")) {
            ret = buildEventPort(xel);
        } else if (tag.equals("Text")) {
            ret = buildText(xel);
        } else if (tag.equals("Path")) {
            ret = buildPath(xel);
        } else if (tag.equals("Attachments")) {
            ret = buildAttachments(xel);
        } else if (tag.equals("Insertion")) {
            ret = buildInsertion(xel);
        } else if (tag.equals("IntegerParameter")) {
            ret = buildIntegerParameter(xel);
        } else if (tag.equals("IndexParameter")) {
            ret = buildIndexParameter(xel);
        } else if (tag.equals("About")) {
            ret = buildAbout(xel);
        } else if (tag.equals("Meta")) {
            ret = buildMeta(xel);
        } else if (tag.equals("SimulatorShortcut")) {
            ret = buildSimulatorShortcut(xel);
        } else if (tag.equals("CableCell")) {
            ret = buildCableCell(xel);
        } else if (tag.equals("InputSource")) {
            ret = buildInputSource(xel);
        } else if (tag.equals("Dynamics")) {
            ret = buildDynamics(xel);
        } else if (tag.equals("StateVariable")) {
            ret = buildStateVariable(xel);
        } else if (tag.equals("StateAssignment")) {
            ret = buildStateAssignment(xel);
        } else if (tag.equals("TimeDerivative")) {
            ret = buildTimeDerivative(xel);
        } else if (tag.equals("DerivedVariable")) {
            ret = buildDerivedVariable(xel);
        } else if (tag.equals("OnStart")) {
            ret = buildOnStart(xel);
        } else if (tag.equals("OnCondition")) {
            ret = buildOnCondition(xel);
        } else if (tag.equals("OnEvent")) {
            ret = buildOnEvent(xel);
        } else if (tag.equals("EventOut")) {
            ret = buildEventOut(xel);
        } else if (tag.equals("KineticScheme")) {
            ret = buildKineticScheme(xel);
        } else if (tag.equals("Regime")) {
            ret = buildRegime(xel);
        } else if (tag.equals("OnEntry")) {
            ret = buildOnEntry(xel);
        } else if (tag.equals("Transition")) {
            ret = buildTransition(xel);
        } else if (tag.equals("IfCondition")) {
            ret = buildIfCondition(xel);
        } else if (tag.equals("Super")) {
            ret = buildSuper(xel);
        } else if (tag.equals("ConditionalDerivedVariable")) {
            ret = buildConditionalDerivedVariable(xel);
        } else if (tag.equals("Case")) {
            ret = buildCase(xel);
        } else if (tag.equals("Equilibrium")) {
            ret = buildEquilibrium(xel);
        } else if (tag.equals("StateScalarField")) {
            ret = buildStateScalarField(xel);
        } else if (tag.equals("DerivedScalarField")) {
            ret = buildDerivedScalarField(xel);
        } else if (tag.equals("DerivedPunctateField")) {
            ret = buildDerivedPunctateField(xel);
        } else if (tag.equals("Structure")) {
            ret = buildStructure(xel);
        } else if (tag.equals("MultiInstantiate")) {
            ret = buildMultiInstantiate(xel);
        } else if (tag.equals("CoInstantiate")) {
            ret = buildCoInstantiate(xel);
        } else if (tag.equals("Instance")) {
            ret = buildInstance(xel);
        } else if (tag.equals("MultiInstance")) {
            ret = buildMultiInstance(xel);
        } else if (tag.equals("Assign")) {
            ret = buildAssign(xel);
        } else if (tag.equals("Choose")) {
            ret = buildChoose(xel);
        } else if (tag.equals("ChildInstance")) {
            ret = buildChildInstance(xel);
        } else if (tag.equals("ForEach")) {
            ret = buildForEach(xel);
        } else if (tag.equals("EventConnection")) {
            ret = buildEventConnection(xel);
        } else if (tag.equals("Tunnel")) {
            ret = buildTunnel(xel);
        } else if (tag.equals("PairsEventConnection")) {
            ret = buildPairsEventConnection(xel);
        } else if (tag.equals("PairFilter")) {
            ret = buildPairFilter(xel);
        } else if (tag.equals("IncludePair")) {
            ret = buildIncludePair(xel);
        } else if (tag.equals("With")) {
            ret = buildWith(xel);
        } else if (tag.equals("If")) {
            ret = buildIf(xel);
        } else if (tag.equals("Apply")) {
            ret = buildApply(xel);
        } else if (tag.equals("Gather")) {
            ret = buildGather(xel);
        } else if (tag.equals("GatherPairs")) {
            ret = buildGatherPairs(xel);
        } else if (tag.equals("Simulation")) {
            ret = buildSimulation(xel);
        } else if (tag.equals("Record")) {
            ret = buildRecord(xel);
        } else if (tag.equals("DataDisplay")) {
            ret = buildDataDisplay(xel);
        } else if (tag.equals("DataWriter")) {
            ret = buildDataWriter(xel);
        } else if (tag.equals("Run")) {
            ret = buildRun(xel);
        } else if (tag.equals("Procedure")) {
            ret = buildProcedure(xel);
        } else if (tag.equals("Equilibrate")) {
            ret = buildEquilibrate(xel);
        } else if (tag.equals("ForEachComponent")) {
            ret = buildForEachComponent(xel);
        } else if (tag.equals("Print")) {
            ret = buildPrint(xel);
        } else if (tag.equals("Geometry")) {
            ret = buildGeometry(xel);
        } else if (tag.equals("Frustum")) {
            ret = buildFrustum(xel);
        } else if (tag.equals("Solid")) {
            ret = buildSolid(xel);
        } else if (tag.equals("Location")) {
            ret = buildLocation(xel);
        } else if (tag.equals("Skeleton")) {
            ret = buildSkeleton(xel);
        } else if (tag.equals("ScalarField")) {
            ret = buildScalarField(xel);
        } else if (tag.equals("Visualization")) {
            ret = buildVisualization(xel);
        } else if (tag.equals("LinkSourceConnector")) {
            ret = buildLinkSourceConnector(xel);
        } else if (tag.equals("LinkTargetConnector")) {
            ret = buildLinkTargetConnector(xel);
        } else if (tag.equals("Circle")) {
            ret = buildCircle(xel);
        } else if (tag.equals("Oval")) {
            ret = buildOval(xel);
        } else if (tag.equals("Rectangle")) {
            ret = buildRectangle(xel);
        } else if (tag.equals("PolyFill")) {
            ret = buildPolyFill(xel);
        } else if (tag.equals("PolyLine")) {
            ret = buildPolyLine(xel);
        } else if (tag.equals("IntegrationScheme")) {
            ret = buildIntegrationScheme(xel);
        } else if (tag.equals("IntegrationStep")) {
            ret = buildIntegrationStep(xel);
        } else if (tag.equals("GradientStateIncrement")) {
            ret = buildGradientStateIncrement(xel);
        } else if (tag.equals("Gradient")) {
            ret = buildGradient(xel);
        } else if (tag.equals("MeanGradient")) {
            ret = buildMeanGradient(xel);
        } else if (tag.equals("MeanGradientComponent")) {
            ret = buildMeanGradientComponent(xel);
        } else if (tag.equals("WorkState")) {
            ret = buildWorkState(xel);
        } else {
            E.error("Unrecognized name " + tag);
        }
        return ret;
    }


    private Lems buildLems(XMLElement xel) {
        Lems ret = new Lems();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("description")) {
                ret.description = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Dimension) {
                ret.dimensions.add((Dimension)obj);
            } else if (obj instanceof Constant) {
                ret.constants.add((Constant)obj);
            } else if (obj instanceof Unit) {
                ret.units.add((Unit)obj);
            } else if (obj instanceof Assertion) {
                ret.assertions.add((Assertion)obj);
            } else if (obj instanceof ComponentType) {
                ret.componentTypes.add((ComponentType)obj);
            } else if (obj instanceof Component) {
                ret.components.add((Component)obj);
            } else if (obj instanceof Target) {
                ret.targets.add((Target)obj);
            } else if (obj instanceof IntegrationScheme) {
                ret.integrationSchemes.add((IntegrationScheme)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Target buildTarget(XMLElement xel) {
        Target ret = new Target();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("component")) {
                ret.component = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Constant buildConstant(XMLElement xel) {
        Constant ret = new Constant();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("description")) {
                ret.description = parseString(xv);
            } else if (xn.equals("symbol")) {
                ret.symbol = parseString(xv);
            } else if (xn.equals("value")) {
                ret.value = parseString(xv);
            } else if (xn.equals("dimension")) {
                ret.dimension = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Dimension buildDimension(XMLElement xel) {
        Dimension ret = new Dimension();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("m")) {
                ret.m = parseInt(xv);
            } else if (xn.equals("l")) {
                ret.l = parseInt(xv);
            } else if (xn.equals("t")) {
                ret.t = parseInt(xv);
            } else if (xn.equals("i")) {
                ret.i = parseInt(xv);
            } else if (xn.equals("k")) {
                ret.k = parseInt(xv);
            } else if (xn.equals("n")) {
                ret.n = parseInt(xv);
            } else if (xn.equals("j")) {
                ret.j = parseInt(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Unit buildUnit(XMLElement xel) {
        Unit ret = new Unit();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("symbol")) {
                ret.symbol = parseString(xv);
            } else if (xn.equals("dimension")) {
                ret.dimension = parseString(xv);
            } else if (xn.equals("power")) {
                ret.power = parseInt(xv);
            } else if (xn.equals("scale")) {
                ret.scale = parseDouble(xv);
            } else if (xn.equals("offset")) {
                ret.offset = parseDouble(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Assertion buildAssertion(XMLElement xel) {
        Assertion ret = new Assertion();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("dimension")) {
                ret.dimension = parseString(xv);
            } else if (xn.equals("matches")) {
                ret.matches = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Component buildComponent(XMLElement xel) {
        Component ret = new Component();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("id")) {
                ret.id = parseString(xv);
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("declaredType")) {
                ret.declaredType = parseString(xv);
            } else if (xn.equals("type")) {
                ret.type = parseString(xv);
            } else if (xn.equals("eXtends")) {
                ret.eXtends = parseString(xv);
            } else if (xn.equals("xPosition")) {
                ret.xPosition = parseDouble(xv);
            } else if (xn.equals("yPosition")) {
                ret.yPosition = parseDouble(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Insertion) {
                ret.insertions.add((Insertion)obj);
            } else if (obj instanceof Component) {
                ret.components.add((Component)obj);
            } else if (obj instanceof About) {
                ret.abouts.add((About)obj);
            } else if (obj instanceof Meta) {
                ret.metas.add((Meta)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private ComponentType buildComponentType(XMLElement xel) {
        ComponentType ret = new ComponentType();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("eXtends")) {
                ret.eXtends = parseString(xv);
            } else if (xn.equals("standalone")) {
                ret.standalone = parseBoolean(xv);
            } else if (xn.equals("id")) {
                ret.id = parseString(xv);
            } else if (xn.equals("description")) {
                ret.description = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Parameter) {
                ret.parameters.add((Parameter)obj);
            } else if (obj instanceof IndexParameter) {
                ret.indexParameters.add((IndexParameter)obj);
            } else if (obj instanceof DerivedParameter) {
                ret.derivedParameters.add((DerivedParameter)obj);
            } else if (obj instanceof PathParameter) {
                ret.pathParameters.add((PathParameter)obj);
            } else if (obj instanceof LocalParameters) {
                ret.localParameterss.add((LocalParameters)obj);
            } else if (obj instanceof Requirement) {
                ret.requirements.add((Requirement)obj);
            } else if (obj instanceof ComponentRequirement) {
                ret.componentRequirements.add((ComponentRequirement)obj);
            } else if (obj instanceof InstanceRequirement) {
                ret.instanceRequirements.add((InstanceRequirement)obj);
            } else if (obj instanceof Exposure) {
                ret.exposures.add((Exposure)obj);
            } else if (obj instanceof Child) {
                ret.childs.add((Child)obj);
            } else if (obj instanceof Children) {
                ret.childrens.add((Children)obj);
            } else if (obj instanceof ComponentReference) {
                ret.componentReferences.add((ComponentReference)obj);
            } else if (obj instanceof ComponentTypeReference) {
                ret.componentTypeReferences.add((ComponentTypeReference)obj);
            } else if (obj instanceof Location) {
                ret.locations.add((Location)obj);
            } else if (obj instanceof Property) {
                ret.propertys.add((Property)obj);
            } else if (obj instanceof Dynamics) {
                ret.dynamicses.add((Dynamics)obj);
            } else if (obj instanceof Structure) {
                ret.structures.add((Structure)obj);
            } else if (obj instanceof Simulation) {
                ret.simulations.add((Simulation)obj);
            } else if (obj instanceof Equilibrium) {
                ret.equilibriums.add((Equilibrium)obj);
            } else if (obj instanceof Procedure) {
                ret.procedures.add((Procedure)obj);
            } else if (obj instanceof Geometry) {
                ret.geometrys.add((Geometry)obj);
            } else if (obj instanceof Visualization) {
                ret.visualizations.add((Visualization)obj);
            } else if (obj instanceof Fixed) {
                ret.fixeds.add((Fixed)obj);
            } else if (obj instanceof Constant) {
                ret.constants.add((Constant)obj);
            } else if (obj instanceof Attachments) {
                ret.attachmentses.add((Attachments)obj);
            } else if (obj instanceof ReceivePort) {
                ret.receivePorts.add((ReceivePort)obj);
            } else if (obj instanceof SendPort) {
                ret.sendPorts.add((SendPort)obj);
            } else if (obj instanceof Path) {
                ret.paths.add((Path)obj);
            } else if (obj instanceof Text) {
                ret.texts.add((Text)obj);
            } else if (obj instanceof Collection) {
                ret.collections.add((Collection)obj);
            } else if (obj instanceof PairCollection) {
                ret.pairCollections.add((PairCollection)obj);
            } else if (obj instanceof SimulatorShortcut) {
                ret.simulatorShortcuts.add((SimulatorShortcut)obj);
            } else if (obj instanceof About) {
                ret.abouts.add((About)obj);
            } else if (obj instanceof Meta) {
                ret.metas.add((Meta)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Parameter buildParameter(XMLElement xel) {
        Parameter ret = new Parameter();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("dimension")) {
                ret.dimension = parseString(xv);
            } else if (xn.equals("description")) {
                ret.description = parseString(xv);
            } else if (xn.equals("exposure")) {
                ret.exposure = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private PathParameter buildPathParameter(XMLElement xel) {
        PathParameter ret = new PathParameter();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private LocalParameters buildLocalParameters(XMLElement xel) {
        LocalParameters ret = new LocalParameters();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Property buildProperty(XMLElement xel) {
        Property ret = new Property();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("dimension")) {
                ret.dimension = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private DerivedParameter buildDerivedParameter(XMLElement xel) {
        DerivedParameter ret = new DerivedParameter();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("dimension")) {
                ret.dimension = parseString(xv);
            } else if (xn.equals("select")) {
                ret.select = parseString(xv);
            } else if (xn.equals("value")) {
                ret.value = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Fixed buildFixed(XMLElement xel) {
        Fixed ret = new Fixed();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("parameter")) {
                ret.parameter = parseString(xv);
            } else if (xn.equals("value")) {
                ret.value = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Requirement buildRequirement(XMLElement xel) {
        Requirement ret = new Requirement();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("dimension")) {
                ret.dimension = parseString(xv);
            } else if (xn.equals("description")) {
                ret.description = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private ComponentRequirement buildComponentRequirement(XMLElement xel) {
        ComponentRequirement ret = new ComponentRequirement();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("description")) {
                ret.description = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private InstanceRequirement buildInstanceRequirement(XMLElement xel) {
        InstanceRequirement ret = new InstanceRequirement();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("type")) {
                ret.type = parseString(xv);
            } else if (xn.equals("description")) {
                ret.description = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Exposure buildExposure(XMLElement xel) {
        Exposure ret = new Exposure();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("dimension")) {
                ret.dimension = parseString(xv);
            } else if (xn.equals("description")) {
                ret.description = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Child buildChild(XMLElement xel) {
        Child ret = new Child();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("type")) {
                ret.type = parseString(xv);
            } else if (xn.equals("substitute")) {
                ret.substitute = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Children buildChildren(XMLElement xel) {
        Children ret = new Children();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("type")) {
                ret.type = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private ComponentReference buildComponentReference(XMLElement xel) {
        ComponentReference ret = new ComponentReference();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("type")) {
                ret.type = parseString(xv);
            } else if (xn.equals("root")) {
                ret.root = parseString(xv);
            } else if (xn.equals("isAny")) {
                ret.isAny = parseBoolean(xv);
            } else if (xn.equals("local")) {
                ret.local = parseBoolean(xv);
            } else if (xn.equals("required")) {
                ret.required = parseBoolean(xv);
            } else if (xn.equals("defaultComponent")) {
                ret.defaultComponent = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Link buildLink(XMLElement xel) {
        Link ret = new Link();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("type")) {
                ret.type = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private ComponentTypeReference buildComponentTypeReference(XMLElement xel) {
        ComponentTypeReference ret = new ComponentTypeReference();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Collection buildCollection(XMLElement xel) {
        Collection ret = new Collection();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private PairCollection buildPairCollection(XMLElement xel) {
        PairCollection ret = new PairCollection();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private SendPort buildSendPort(XMLElement xel) {
        SendPort ret = new SendPort();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("description")) {
                ret.description = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private ReceivePort buildReceivePort(XMLElement xel) {
        ReceivePort ret = new ReceivePort();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("description")) {
                ret.description = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private EventPort buildEventPort(XMLElement xel) {
        EventPort ret = new EventPort();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("description")) {
                ret.description = parseString(xv);
            } else if (xn.equals("direction")) {
                ret.direction = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Text buildText(XMLElement xel) {
        Text ret = new Text();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Path buildPath(XMLElement xel) {
        Path ret = new Path();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Attachments buildAttachments(XMLElement xel) {
        Attachments ret = new Attachments();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("type")) {
                ret.type = parseString(xv);
            } else if (xn.equals("compClass")) {
                ret.compClass = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Insertion buildInsertion(XMLElement xel) {
        Insertion ret = new Insertion();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("component")) {
                ret.component = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private IntegerParameter buildIntegerParameter(XMLElement xel) {
        IntegerParameter ret = new IntegerParameter();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("dimension")) {
                ret.dimension = parseString(xv);
            } else if (xn.equals("description")) {
                ret.description = parseString(xv);
            } else if (xn.equals("exposure")) {
                ret.exposure = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private IndexParameter buildIndexParameter(XMLElement xel) {
        IndexParameter ret = new IndexParameter();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("dimension")) {
                ret.dimension = parseString(xv);
            } else if (xn.equals("description")) {
                ret.description = parseString(xv);
            } else if (xn.equals("exposure")) {
                ret.exposure = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private About buildAbout(XMLElement xel) {
        About ret = new About();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Meta buildMeta(XMLElement xel) {
        Meta ret = new Meta();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("context")) {
                ret.context = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private SimulatorShortcut buildSimulatorShortcut(XMLElement xel) {
        SimulatorShortcut ret = new SimulatorShortcut();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("x_for")) {
                ret.x_for = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof CableCell) {
                ret.cableCells.add((CableCell)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private CableCell buildCableCell(XMLElement xel) {
        CableCell ret = new CableCell();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("conductivityField")) {
                ret.conductivityField = parseString(xv);
            } else if (xn.equals("capacitanceField")) {
                ret.capacitanceField = parseString(xv);
            } else if (xn.equals("morphologyField")) {
                ret.morphologyField = parseString(xv);
            } else if (xn.equals("morphologyFormat")) {
                ret.morphologyFormat = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof InputSource) {
                ret.inputSources.add((InputSource)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private InputSource buildInputSource(XMLElement xel) {
        InputSource ret = new InputSource();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("field")) {
                ret.field = parseString(xv);
            } else if (xn.equals("format")) {
                ret.format = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Dynamics buildDynamics(XMLElement xel) {
        Dynamics ret = new Dynamics();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("simultaneous")) {
                ret.simultaneous = parseBoolean(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Super) {
                ret.supers.add((Super)obj);
            } else if (obj instanceof DerivedVariable) {
                ret.derivedVariables.add((DerivedVariable)obj);
            } else if (obj instanceof ConditionalDerivedVariable) {
                ret.conditionalDerivedVariables.add((ConditionalDerivedVariable)obj);
            } else if (obj instanceof StateVariable) {
                ret.stateVariables.add((StateVariable)obj);
            } else if (obj instanceof TimeDerivative) {
                ret.timeDerivatives.add((TimeDerivative)obj);
            } else if (obj instanceof KineticScheme) {
                ret.kineticSchemes.add((KineticScheme)obj);
            } else if (obj instanceof OnStart) {
                ret.onStarts.add((OnStart)obj);
            } else if (obj instanceof OnEvent) {
                ret.onEvents.add((OnEvent)obj);
            } else if (obj instanceof OnCondition) {
                ret.onConditions.add((OnCondition)obj);
            } else if (obj instanceof StateScalarField) {
                ret.stateScalarFields.add((StateScalarField)obj);
            } else if (obj instanceof DerivedScalarField) {
                ret.derivedScalarFields.add((DerivedScalarField)obj);
            } else if (obj instanceof DerivedPunctateField) {
                ret.derivedPunctateFields.add((DerivedPunctateField)obj);
            } else if (obj instanceof Regime) {
                ret.regimes.add((Regime)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private StateVariable buildStateVariable(XMLElement xel) {
        StateVariable ret = new StateVariable();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("dimension")) {
                ret.dimension = parseString(xv);
            } else if (xn.equals("exposure")) {
                ret.exposure = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private StateAssignment buildStateAssignment(XMLElement xel) {
        StateAssignment ret = new StateAssignment();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("variable")) {
                ret.variable = parseString(xv);
            } else if (xn.equals("value")) {
                ret.value = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private TimeDerivative buildTimeDerivative(XMLElement xel) {
        TimeDerivative ret = new TimeDerivative();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("variable")) {
                ret.variable = parseString(xv);
            } else if (xn.equals("value")) {
                ret.value = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private DerivedVariable buildDerivedVariable(XMLElement xel) {
        DerivedVariable ret = new DerivedVariable();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("select")) {
                ret.select = parseString(xv);
            } else if (xn.equals("over")) {
                ret.over = parseString(xv);
            } else if (xn.equals("dimension")) {
                ret.dimension = parseString(xv);
            } else if (xn.equals("reduce")) {
                ret.reduce = parseString(xv);
            } else if (xn.equals("exposure")) {
                ret.exposure = parseString(xv);
            } else if (xn.equals("required")) {
                ret.required = parseBoolean(xv);
            } else if (xn.equals("value")) {
                ret.value = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private OnStart buildOnStart(XMLElement xel) {
        OnStart ret = new OnStart();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof StateAssignment) {
                ret.stateAssignments.add((StateAssignment)obj);
            } else if (obj instanceof EventOut) {
                ret.eventOuts.add((EventOut)obj);
            } else if (obj instanceof Transition) {
                ret.transitions.add((Transition)obj);
            } else if (obj instanceof IfCondition) {
                ret.ifConditions.add((IfCondition)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private OnCondition buildOnCondition(XMLElement xel) {
        OnCondition ret = new OnCondition();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("test")) {
                ret.test = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof StateAssignment) {
                ret.stateAssignments.add((StateAssignment)obj);
            } else if (obj instanceof EventOut) {
                ret.eventOuts.add((EventOut)obj);
            } else if (obj instanceof Transition) {
                ret.transitions.add((Transition)obj);
            } else if (obj instanceof IfCondition) {
                ret.ifConditions.add((IfCondition)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private OnEvent buildOnEvent(XMLElement xel) {
        OnEvent ret = new OnEvent();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("port")) {
                ret.port = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof StateAssignment) {
                ret.stateAssignments.add((StateAssignment)obj);
            } else if (obj instanceof EventOut) {
                ret.eventOuts.add((EventOut)obj);
            } else if (obj instanceof Transition) {
                ret.transitions.add((Transition)obj);
            } else if (obj instanceof IfCondition) {
                ret.ifConditions.add((IfCondition)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private EventOut buildEventOut(XMLElement xel) {
        EventOut ret = new EventOut();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("port")) {
                ret.port = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private KineticScheme buildKineticScheme(XMLElement xel) {
        KineticScheme ret = new KineticScheme();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("nodes")) {
                ret.nodes = parseString(xv);
            } else if (xn.equals("edges")) {
                ret.edges = parseString(xv);
            } else if (xn.equals("stateVariable")) {
                ret.stateVariable = parseString(xv);
            } else if (xn.equals("edgeSource")) {
                ret.edgeSource = parseString(xv);
            } else if (xn.equals("edgeTarget")) {
                ret.edgeTarget = parseString(xv);
            } else if (xn.equals("forwardRate")) {
                ret.forwardRate = parseString(xv);
            } else if (xn.equals("reverseRate")) {
                ret.reverseRate = parseString(xv);
            } else if (xn.equals("dependency")) {
                ret.dependency = parseString(xv);
            } else if (xn.equals("step")) {
                ret.step = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Regime buildRegime(XMLElement xel) {
        Regime ret = new Regime();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("initial")) {
                ret.initial = parseString(xv);
            } else if (xn.equals("b_initial")) {
                ret.b_initial = parseBoolean(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof DerivedVariable) {
                ret.derivedVariables.add((DerivedVariable)obj);
            } else if (obj instanceof StateVariable) {
                ret.stateVariables.add((StateVariable)obj);
            } else if (obj instanceof TimeDerivative) {
                ret.timeDerivatives.add((TimeDerivative)obj);
            } else if (obj instanceof OnStart) {
                ret.onStarts.add((OnStart)obj);
            } else if (obj instanceof OnEntry) {
                ret.onEntrys.add((OnEntry)obj);
            } else if (obj instanceof OnEvent) {
                ret.onEvents.add((OnEvent)obj);
            } else if (obj instanceof OnCondition) {
                ret.onConditions.add((OnCondition)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private OnEntry buildOnEntry(XMLElement xel) {
        OnEntry ret = new OnEntry();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof StateAssignment) {
                ret.stateAssignments.add((StateAssignment)obj);
            } else if (obj instanceof EventOut) {
                ret.eventOuts.add((EventOut)obj);
            } else if (obj instanceof Transition) {
                ret.transitions.add((Transition)obj);
            } else if (obj instanceof IfCondition) {
                ret.ifConditions.add((IfCondition)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Transition buildTransition(XMLElement xel) {
        Transition ret = new Transition();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("regime")) {
                ret.regime = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private IfCondition buildIfCondition(XMLElement xel) {
        IfCondition ret = new IfCondition();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("test")) {
                ret.test = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof StateAssignment) {
                ret.stateAssignments.add((StateAssignment)obj);
            } else if (obj instanceof EventOut) {
                ret.eventOuts.add((EventOut)obj);
            } else if (obj instanceof Transition) {
                ret.transitions.add((Transition)obj);
            } else if (obj instanceof IfCondition) {
                ret.ifConditions.add((IfCondition)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Super buildSuper(XMLElement xel) {
        Super ret = new Super();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private ConditionalDerivedVariable buildConditionalDerivedVariable(XMLElement xel) {
        ConditionalDerivedVariable ret = new ConditionalDerivedVariable();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("dimension")) {
                ret.dimension = parseString(xv);
            } else if (xn.equals("exposure")) {
                ret.exposure = parseString(xv);
            } else if (xn.equals("required")) {
                ret.required = parseBoolean(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Case) {
                ret.cases.add((Case)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Case buildCase(XMLElement xel) {
        Case ret = new Case();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("condition")) {
                ret.condition = parseString(xv);
            } else if (xn.equals("value")) {
                ret.value = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Equilibrium buildEquilibrium(XMLElement xel) {
        Equilibrium ret = new Equilibrium();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof DerivedVariable) {
                ret.derivedVariables.add((DerivedVariable)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private StateScalarField buildStateScalarField(XMLElement xel) {
        StateScalarField ret = new StateScalarField();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("dimension")) {
                ret.dimension = parseString(xv);
            } else if (xn.equals("region")) {
                ret.region = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private DerivedScalarField buildDerivedScalarField(XMLElement xel) {
        DerivedScalarField ret = new DerivedScalarField();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("dimension")) {
                ret.dimension = parseString(xv);
            } else if (xn.equals("region")) {
                ret.region = parseString(xv);
            } else if (xn.equals("value")) {
                ret.value = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private DerivedPunctateField buildDerivedPunctateField(XMLElement xel) {
        DerivedPunctateField ret = new DerivedPunctateField();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("dimension")) {
                ret.dimension = parseString(xv);
            } else if (xn.equals("region")) {
                ret.region = parseString(xv);
            } else if (xn.equals("select")) {
                ret.select = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Structure buildStructure(XMLElement xel) {
        Structure ret = new Structure();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private MultiInstantiate buildMultiInstantiate(XMLElement xel) {
        MultiInstantiate ret = new MultiInstantiate();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("number")) {
                ret.number = parseString(xv);
            } else if (xn.equals("indexVariable")) {
                ret.indexVariable = parseString(xv);
            } else if (xn.equals("component")) {
                ret.component = parseString(xv);
            } else if (xn.equals("componentType")) {
                ret.componentType = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Assign) {
                ret.assigns.add((Assign)obj);
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private CoInstantiate buildCoInstantiate(XMLElement xel) {
        CoInstantiate ret = new CoInstantiate();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("at")) {
                ret.at = parseString(xv);
            } else if (xn.equals("component")) {
                ret.component = parseString(xv);
            } else if (xn.equals("componentType")) {
                ret.componentType = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Assign) {
                ret.assigns.add((Assign)obj);
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Instance buildInstance(XMLElement xel) {
        Instance ret = new Instance();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("localParameters")) {
                ret.localParameters = parseString(xv);
            } else if (xn.equals("componentReference")) {
                ret.componentReference = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Assign) {
                ret.assigns.add((Assign)obj);
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private MultiInstance buildMultiInstance(XMLElement xel) {
        MultiInstance ret = new MultiInstance();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("children")) {
                ret.children = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Assign buildAssign(XMLElement xel) {
        Assign ret = new Assign();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("property")) {
                ret.property = parseString(xv);
            } else if (xn.equals("value")) {
                ret.value = parseString(xv);
            } else if (xn.equals("exposeAs")) {
                ret.exposeAs = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Choose buildChoose(XMLElement xel) {
        Choose ret = new Choose();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private ChildInstance buildChildInstance(XMLElement xel) {
        ChildInstance ret = new ChildInstance();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("component")) {
                ret.component = parseString(xv);
            } else if (xn.equals("componentType")) {
                ret.componentType = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Assign) {
                ret.assigns.add((Assign)obj);
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private ForEach buildForEach(XMLElement xel) {
        ForEach ret = new ForEach();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("instances")) {
                ret.instances = parseString(xv);
            } else if (xn.equals("as")) {
                ret.as = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private EventConnection buildEventConnection(XMLElement xel) {
        EventConnection ret = new EventConnection();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("from")) {
                ret.from = parseString(xv);
            } else if (xn.equals("to")) {
                ret.to = parseString(xv);
            } else if (xn.equals("delay")) {
                ret.delay = parseString(xv);
            } else if (xn.equals("receiver")) {
                ret.receiver = parseString(xv);
            } else if (xn.equals("receiverContainer")) {
                ret.receiverContainer = parseString(xv);
            } else if (xn.equals("sourcePort")) {
                ret.sourcePort = parseString(xv);
            } else if (xn.equals("targetPort")) {
                ret.targetPort = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Assign) {
                ret.assigns.add((Assign)obj);
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Tunnel buildTunnel(XMLElement xel) {
        Tunnel ret = new Tunnel();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("from")) {
                ret.from = parseString(xv);
            } else if (xn.equals("to")) {
                ret.to = parseString(xv);
            } else if (xn.equals("expose")) {
                ret.expose = parseString(xv);
            } else if (xn.equals("as")) {
                ret.as = parseString(xv);
            } else if (xn.equals("component")) {
                ret.component = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private PairsEventConnection buildPairsEventConnection(XMLElement xel) {
        PairsEventConnection ret = new PairsEventConnection();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("pairs")) {
                ret.pairs = parseString(xv);
            } else if (xn.equals("receiver")) {
                ret.receiver = parseString(xv);
            } else if (xn.equals("receiverContainer")) {
                ret.receiverContainer = parseString(xv);
            } else if (xn.equals("sourcePort")) {
                ret.sourcePort = parseString(xv);
            } else if (xn.equals("targetPort")) {
                ret.targetPort = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private PairFilter buildPairFilter(XMLElement xel) {
        PairFilter ret = new PairFilter();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("select")) {
                ret.select = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private IncludePair buildIncludePair(XMLElement xel) {
        IncludePair ret = new IncludePair();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("collection")) {
                ret.collection = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private With buildWith(XMLElement xel) {
        With ret = new With();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("instance")) {
                ret.instance = parseString(xv);
            } else if (xn.equals("list")) {
                ret.list = parseString(xv);
            } else if (xn.equals("index")) {
                ret.index = parseString(xv);
            } else if (xn.equals("as")) {
                ret.as = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private If buildIf(XMLElement xel) {
        If ret = new If();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("test")) {
                ret.test = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Apply buildApply(XMLElement xel) {
        Apply ret = new Apply();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("components")) {
                ret.components = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Gather buildGather(XMLElement xel) {
        Gather ret = new Gather();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("pathParameter")) {
                ret.pathParameter = parseString(xv);
            } else if (xn.equals("collection")) {
                ret.collection = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private GatherPairs buildGatherPairs(XMLElement xel) {
        GatherPairs ret = new GatherPairs();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("id")) {
                ret.id = parseString(xv);
            } else if (xn.equals("pFrom")) {
                ret.pFrom = parseString(xv);
            } else if (xn.equals("qFrom")) {
                ret.qFrom = parseString(xv);
            } else if (xn.equals("collection")) {
                ret.collection = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof BuildElement) {
                ret.buildElements.add((BuildElement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Simulation buildSimulation(XMLElement xel) {
        Simulation ret = new Simulation();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Record) {
                ret.records.add((Record)obj);
            } else if (obj instanceof Run) {
                ret.runs.add((Run)obj);
            } else if (obj instanceof DataDisplay) {
                ret.dataDisplays.add((DataDisplay)obj);
            } else if (obj instanceof DataWriter) {
                ret.dataWriters.add((DataWriter)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Record buildRecord(XMLElement xel) {
        Record ret = new Record();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("quantity")) {
                ret.quantity = parseString(xv);
            } else if (xn.equals("timeScale")) {
                ret.timeScale = parseString(xv);
            } else if (xn.equals("scale")) {
                ret.scale = parseString(xv);
            } else if (xn.equals("color")) {
                ret.color = parseString(xv);
            } else if (xn.equals("destination")) {
                ret.destination = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private DataDisplay buildDataDisplay(XMLElement xel) {
        DataDisplay ret = new DataDisplay();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("title")) {
                ret.title = parseString(xv);
            } else if (xn.equals("dataRegion")) {
                ret.dataRegion = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private DataWriter buildDataWriter(XMLElement xel) {
        DataWriter ret = new DataWriter();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("id")) {
                ret.id = parseString(xv);
            } else if (xn.equals("path")) {
                ret.path = parseString(xv);
            } else if (xn.equals("fileName")) {
                ret.fileName = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Run buildRun(XMLElement xel) {
        Run ret = new Run();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("component")) {
                ret.component = parseString(xv);
            } else if (xn.equals("variable")) {
                ret.variable = parseString(xv);
            } else if (xn.equals("increment")) {
                ret.increment = parseString(xv);
            } else if (xn.equals("total")) {
                ret.total = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Procedure buildProcedure(XMLElement xel) {
        Procedure ret = new Procedure();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Print) {
                ret.prints.add((Print)obj);
            } else if (obj instanceof Equilibrate) {
                ret.equilibrates.add((Equilibrate)obj);
            } else if (obj instanceof ForEachComponent) {
                ret.forEachComponents.add((ForEachComponent)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Equilibrate buildEquilibrate(XMLElement xel) {
        Equilibrate ret = new Equilibrate();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private ForEachComponent buildForEachComponent(XMLElement xel) {
        ForEachComponent ret = new ForEachComponent();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("select")) {
                ret.select = parseString(xv);
            } else if (xn.equals("as")) {
                ret.as = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof AbstractStatement) {
                ret.abstractStatements.add((AbstractStatement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Print buildPrint(XMLElement xel) {
        Print ret = new Print();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("template")) {
                ret.template = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Geometry buildGeometry(XMLElement xel) {
        Geometry ret = new Geometry();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Frustum) {
                ret.frustums.add((Frustum)obj);
            } else if (obj instanceof Solid) {
                ret.solids.add((Solid)obj);
            } else if (obj instanceof Skeleton) {
                ret.skeletons.add((Skeleton)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Frustum buildFrustum(XMLElement xel) {
        Frustum ret = new Frustum();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("xa")) {
                ret.xa = parseString(xv);
            } else if (xn.equals("ya")) {
                ret.ya = parseString(xv);
            } else if (xn.equals("za")) {
                ret.za = parseString(xv);
            } else if (xn.equals("ra")) {
                ret.ra = parseString(xv);
            } else if (xn.equals("xb")) {
                ret.xb = parseString(xv);
            } else if (xn.equals("yb")) {
                ret.yb = parseString(xv);
            } else if (xn.equals("zb")) {
                ret.zb = parseString(xv);
            } else if (xn.equals("rb")) {
                ret.rb = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Solid buildSolid(XMLElement xel) {
        Solid ret = new Solid();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("elements")) {
                ret.elements = parseString(xv);
            } else if (xn.equals("topologyConstraint")) {
                ret.topologyConstraint = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Location buildLocation(XMLElement xel) {
        Location ret = new Location();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("path")) {
                ret.path = parseString(xv);
            } else if (xn.equals("on")) {
                ret.on = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Skeleton buildSkeleton(XMLElement xel) {
        Skeleton ret = new Skeleton();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("solid")) {
                ret.solid = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof ScalarField) {
                ret.scalarFields.add((ScalarField)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private ScalarField buildScalarField(XMLElement xel) {
        ScalarField ret = new ScalarField();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("dimension")) {
                ret.dimension = parseString(xv);
            } else if (xn.equals("value")) {
                ret.value = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Visualization buildVisualization(XMLElement xel) {
        Visualization ret = new Visualization();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof DrawingElement) {
                ret.drawingElements.add((DrawingElement)obj);
            } else if (obj instanceof DrawingConnector) {
                ret.drawingConnectors.add((DrawingConnector)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private LinkSourceConnector buildLinkSourceConnector(XMLElement xel) {
        LinkSourceConnector ret = new LinkSourceConnector();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("x")) {
                ret.x = parseDouble(xv);
            } else if (xn.equals("y")) {
                ret.y = parseDouble(xv);
            } else if (xn.equals("visible")) {
                ret.visible = parseBoolean(xv);
            } else if (xn.equals("componentReference")) {
                ret.componentReference = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private LinkTargetConnector buildLinkTargetConnector(XMLElement xel) {
        LinkTargetConnector ret = new LinkTargetConnector();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("x")) {
                ret.x = parseDouble(xv);
            } else if (xn.equals("y")) {
                ret.y = parseDouble(xv);
            } else if (xn.equals("visible")) {
                ret.visible = parseBoolean(xv);
            } else if (xn.equals("componentReference")) {
                ret.componentReference = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Circle buildCircle(XMLElement xel) {
        Circle ret = new Circle();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("diameter")) {
                ret.diameter = parseDouble(xv);
            } else if (xn.equals("x")) {
                ret.x = parseDouble(xv);
            } else if (xn.equals("y")) {
                ret.y = parseDouble(xv);
            } else if (xn.equals("xpts")) {
                ret.xpts = parseString(xv);
            } else if (xn.equals("ypts")) {
                ret.ypts = parseString(xv);
            } else if (xn.equals("lineWidth")) {
                ret.lineWidth = parseDouble(xv);
            } else if (xn.equals("lineColor")) {
                ret.lineColor = parseString(xv);
            } else if (xn.equals("fillColor")) {
                ret.fillColor = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Oval buildOval(XMLElement xel) {
        Oval ret = new Oval();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("length")) {
                ret.length = parseDouble(xv);
            } else if (xn.equals("breadth")) {
                ret.breadth = parseDouble(xv);
            } else if (xn.equals("rotation")) {
                ret.rotation = parseDouble(xv);
            } else if (xn.equals("x")) {
                ret.x = parseDouble(xv);
            } else if (xn.equals("y")) {
                ret.y = parseDouble(xv);
            } else if (xn.equals("xpts")) {
                ret.xpts = parseString(xv);
            } else if (xn.equals("ypts")) {
                ret.ypts = parseString(xv);
            } else if (xn.equals("lineWidth")) {
                ret.lineWidth = parseDouble(xv);
            } else if (xn.equals("lineColor")) {
                ret.lineColor = parseString(xv);
            } else if (xn.equals("fillColor")) {
                ret.fillColor = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Rectangle buildRectangle(XMLElement xel) {
        Rectangle ret = new Rectangle();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("width")) {
                ret.width = parseDouble(xv);
            } else if (xn.equals("height")) {
                ret.height = parseDouble(xv);
            } else if (xn.equals("cornerRadius")) {
                ret.cornerRadius = parseDouble(xv);
            } else if (xn.equals("x")) {
                ret.x = parseDouble(xv);
            } else if (xn.equals("y")) {
                ret.y = parseDouble(xv);
            } else if (xn.equals("xpts")) {
                ret.xpts = parseString(xv);
            } else if (xn.equals("ypts")) {
                ret.ypts = parseString(xv);
            } else if (xn.equals("lineWidth")) {
                ret.lineWidth = parseDouble(xv);
            } else if (xn.equals("lineColor")) {
                ret.lineColor = parseString(xv);
            } else if (xn.equals("fillColor")) {
                ret.fillColor = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private PolyFill buildPolyFill(XMLElement xel) {
        PolyFill ret = new PolyFill();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("x")) {
                ret.x = parseDouble(xv);
            } else if (xn.equals("y")) {
                ret.y = parseDouble(xv);
            } else if (xn.equals("xpts")) {
                ret.xpts = parseString(xv);
            } else if (xn.equals("ypts")) {
                ret.ypts = parseString(xv);
            } else if (xn.equals("lineWidth")) {
                ret.lineWidth = parseDouble(xv);
            } else if (xn.equals("lineColor")) {
                ret.lineColor = parseString(xv);
            } else if (xn.equals("fillColor")) {
                ret.fillColor = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private PolyLine buildPolyLine(XMLElement xel) {
        PolyLine ret = new PolyLine();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("x")) {
                ret.x = parseDouble(xv);
            } else if (xn.equals("y")) {
                ret.y = parseDouble(xv);
            } else if (xn.equals("xpts")) {
                ret.xpts = parseString(xv);
            } else if (xn.equals("ypts")) {
                ret.ypts = parseString(xv);
            } else if (xn.equals("lineWidth")) {
                ret.lineWidth = parseDouble(xv);
            } else if (xn.equals("lineColor")) {
                ret.lineColor = parseString(xv);
            } else if (xn.equals("fillColor")) {
                ret.fillColor = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private IntegrationScheme buildIntegrationScheme(XMLElement xel) {
        IntegrationScheme ret = new IntegrationScheme();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof WorkState) {
                ret.workStates.add((WorkState)obj);
            } else if (obj instanceof IntegrationStep) {
                ret.integrationSteps.add((IntegrationStep)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private IntegrationStep buildIntegrationStep(XMLElement xel) {
        IntegrationStep ret = new IntegrationStep();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof GradientStateIncrement) {
                ret.gradientStateIncrements.add((GradientStateIncrement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private GradientStateIncrement buildGradientStateIncrement(XMLElement xel) {
        GradientStateIncrement ret = new GradientStateIncrement();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Gradient buildGradient(XMLElement xel) {
        Gradient ret = new Gradient();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("at")) {
                ret.at = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private MeanGradient buildMeanGradient(XMLElement xel) {
        MeanGradient ret = new MeanGradient();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof MeanGradientComponent) {
                ret.meanGradientComponents.add((MeanGradientComponent)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private MeanGradientComponent buildMeanGradientComponent(XMLElement xel) {
        MeanGradientComponent ret = new MeanGradientComponent();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("weight")) {
                ret.weight = parseDouble(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private WorkState buildWorkState(XMLElement xel) {
        WorkState ret = new WorkState();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("timeFactor")) {
                ret.timeFactor = parseDouble(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (obj != null && obj instanceof DeprecatedElement) {
                obj = ((DeprecatedElement)obj).getReplacement();
            }
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof GradientStateIncrement) {
                ret.gradientStateIncrements.add((GradientStateIncrement)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

}
