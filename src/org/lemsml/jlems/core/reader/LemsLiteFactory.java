package org.lemsml.jlems.core.reader;

import org.lemsml.jlems.core.lite.model.*;
import org.lemsml.jlems.core.xml.XMLElement;
import org.lemsml.jlems.core.xml.XMLAttribute;
import org.lemsml.jlems.core.logging.E;
// NB this is generated code. Don't edit it. If there is a problem, fix the superclass,
// the generator - org.lemsml.jlems.schema.LemsFactoryGenerator, or the class being instantiated.

public class LemsLiteFactory extends AbstractLemsLiteFactory {


    public Object instantiateFromXMLElement(XMLElement xel) {
        Object ret = null;
       String tag = xel.getName();

        if (tag.equals("UNUSED")) {
        } else if (tag.equals("LemsLite")) {
            ret = buildLemsLite(xel);
        } else if (tag.equals("DiscreteUpdateComponent")) {
            ret = buildDiscreteUpdateComponent(xel);
        } else if (tag.equals("Interface")) {
            ret = buildInterface(xel);
        } else if (tag.equals("Parameter")) {
            ret = buildParameter(xel);
        } else if (tag.equals("InputEventPort")) {
            ret = buildInputEventPort(xel);
        } else if (tag.equals("OutputEventPort")) {
            ret = buildOutputEventPort(xel);
        } else if (tag.equals("Constant")) {
            ret = buildConstant(xel);
        } else if (tag.equals("RecordableVariable")) {
            ret = buildRecordableVariable(xel);
        } else if (tag.equals("State")) {
            ret = buildState(xel);
        } else if (tag.equals("StateVariable")) {
            ret = buildStateVariable(xel);
        } else if (tag.equals("Step")) {
            ret = buildStep(xel);
        } else if (tag.equals("Var")) {
            ret = buildVar(xel);
        } else if (tag.equals("Update")) {
            ret = buildUpdate(xel);
        } else if (tag.equals("ConditionCheck")) {
            ret = buildConditionCheck(xel);
        } else if (tag.equals("Output")) {
            ret = buildOutput(xel);
        } else if (tag.equals("OnEvent")) {
            ret = buildOnEvent(xel);
        } else if (tag.equals("OnCondition")) {
            ret = buildOnCondition(xel);
        } else if (tag.equals("Emit")) {
            ret = buildEmit(xel);
        } else if (tag.equals("IfCondition")) {
            ret = buildIfCondition(xel);
        } else if (tag.equals("ComponentArray")) {
            ret = buildComponentArray(xel);
        } else if (tag.equals("EventConnections")) {
            ret = buildEventConnections(xel);
        } else if (tag.equals("Let")) {
            ret = buildLet(xel);
        } else if (tag.equals("Initialize")) {
            ret = buildInitialize(xel);
        } else if (tag.equals("EventSource")) {
            ret = buildEventSource(xel);
        } else if (tag.equals("EventTarget")) {
            ret = buildEventTarget(xel);
        } else if (tag.equals("SourceTargetConnector")) {
            ret = buildSourceTargetConnector(xel);
        } else if (tag.equals("FromArrayConnector")) {
            ret = buildFromArrayConnector(xel);
        } else if (tag.equals("ConnectionProperties")) {
            ret = buildConnectionProperties(xel);
        } else if (tag.equals("Property")) {
            ret = buildProperty(xel);
        } else if (tag.equals("Delay")) {
            ret = buildDelay(xel);
        } else if (tag.equals("EventArguments")) {
            ret = buildEventArguments(xel);
        } else if (tag.equals("Arg")) {
            ret = buildArg(xel);
        } else if (tag.equals("EventTimes")) {
            ret = buildEventTimes(xel);
        } else if (tag.equals("TimedEvents")) {
            ret = buildTimedEvents(xel);
        } else if (tag.equals("TargetIndices")) {
            ret = buildTargetIndices(xel);
        } else if (tag.equals("DataSources")) {
            ret = buildDataSources(xel);
        } else if (tag.equals("ListSource")) {
            ret = buildListSource(xel);
        } else if (tag.equals("File")) {
            ret = buildFile(xel);
        } else if (tag.equals("Array")) {
            ret = buildArray(xel);
        } else if (tag.equals("FileSource")) {
            ret = buildFileSource(xel);
        } else if (tag.equals("Simulation")) {
            ret = buildSimulation(xel);
        } else if (tag.equals("OutputFiles")) {
            ret = buildOutputFiles(xel);
        } else if (tag.equals("Recording")) {
            ret = buildRecording(xel);
        } else if (tag.equals("VariableRecording")) {
            ret = buildVariableRecording(xel);
        } else if (tag.equals("InputEventRecording")) {
            ret = buildInputEventRecording(xel);
        } else if (tag.equals("OutputEventRecording")) {
            ret = buildOutputEventRecording(xel);
        } else if (tag.equals("Display")) {
            ret = buildDisplay(xel);
        } else if (tag.equals("VariableDisplay")) {
            ret = buildVariableDisplay(xel);
        } else {
            E.error("Unrecognized name " + tag);
        }
        return ret;
    }


    private LemsLite buildLemsLite(XMLElement xel) {
        LemsLite ret = new LemsLite();

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
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof DiscreteUpdateComponent) {
                ret.discreteUpdateComponents.add((DiscreteUpdateComponent)obj);
            } else if (obj instanceof DataSources) {
                ret.dataSourcess.add((DataSources)obj);
            } else if (obj instanceof ComponentArray) {
                ret.componentArrays.add((ComponentArray)obj);
            } else if (obj instanceof EventConnections) {
                ret.eventConnectionss.add((EventConnections)obj);
            } else if (obj instanceof TimedEvents) {
                ret.timedEventss.add((TimedEvents)obj);
            } else if (obj instanceof Simulation) {
                ret.simulations.add((Simulation)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private DiscreteUpdateComponent buildDiscreteUpdateComponent(XMLElement xel) {
        DiscreteUpdateComponent ret = new DiscreteUpdateComponent();

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
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Interface) {
                ret.interfaces.add((Interface)obj);
            } else if (obj instanceof State) {
                ret.states.add((State)obj);
            } else if (obj instanceof Step) {
                ret.steps.add((Step)obj);
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

    private Interface buildInterface(XMLElement xel) {
        Interface ret = new Interface();

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
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Parameter) {
                ret.parameters.add((Parameter)obj);
            } else if (obj instanceof Constant) {
                ret.constants.add((Constant)obj);
            } else if (obj instanceof InputVariable) {
                ret.inputVariables.add((InputVariable)obj);
            } else if (obj instanceof RecordableVariable) {
                ret.recordableVariables.add((RecordableVariable)obj);
            } else if (obj instanceof InputEventPort) {
                ret.inputEventPorts.add((InputEventPort)obj);
            } else if (obj instanceof OutputEventPort) {
                ret.outputEventPorts.add((OutputEventPort)obj);
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
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private InputEventPort buildInputEventPort(XMLElement xel) {
        InputEventPort ret = new InputEventPort();

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

    private OutputEventPort buildOutputEventPort(XMLElement xel) {
        OutputEventPort ret = new OutputEventPort();

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

    private Constant buildConstant(XMLElement xel) {
        Constant ret = new Constant();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("value")) {
                ret.value = parseDouble(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private RecordableVariable buildRecordableVariable(XMLElement xel) {
        RecordableVariable ret = new RecordableVariable();

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

    private State buildState(XMLElement xel) {
        State ret = new State();

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
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof StateVariable) {
                ret.stateVariables.add((StateVariable)obj);
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
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Step buildStep(XMLElement xel) {
        Step ret = new Step();

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
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Var) {
                ret.vars.add((Var)obj);
            } else if (obj instanceof Update) {
                ret.updates.add((Update)obj);
       
            } else if (obj instanceof Output) {
                ret.outputs.add((Output)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Var buildVar(XMLElement xel) {
        Var ret = new Var();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("value")) {
                ret.value = parseString(xv);
            } else if (xn.equals("p_rp")) {
                ret.p_rp = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Update buildUpdate(XMLElement xel) {
        Update ret = new Update();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("variable")) {
                ret.variable = parseString(xv);
            } else if (xn.equals("value")) {
                ret.value = parseString(xv);
            } else if (xn.equals("p_rp")) {
                ret.p_rp = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private ConditionCheck buildConditionCheck(XMLElement xel) {
        ConditionCheck ret = new ConditionCheck();

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

    private Output buildOutput(XMLElement xel) {
        Output ret = new Output();

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
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Var) {
                ret.vars.add((Var)obj);
            } else if (obj instanceof Update) {
                ret.updates.add((Update)obj);
            } else if (obj instanceof Emit) {
                ret.emits.add((Emit)obj);
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
            } else if (xn.equals("test")) {
                ret.test = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Var) {
                ret.vars.add((Var)obj);
            } else if (obj instanceof Update) {
                ret.updates.add((Update)obj);
            } else if (obj instanceof Emit) {
                ret.emits.add((Emit)obj);
            } else if (obj instanceof IfCondition) {
                ret.ifConditions.add((IfCondition)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Emit buildEmit(XMLElement xel) {
        Emit ret = new Emit();

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
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Var) {
                ret.vars.add((Var)obj);
            } else if (obj instanceof Update) {
                ret.updates.add((Update)obj);
            } else if (obj instanceof Emit) {
                ret.emits.add((Emit)obj);
            } else if (obj instanceof IfCondition) {
                ret.ifConditions.add((IfCondition)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private ComponentArray buildComponentArray(XMLElement xel) {
        ComponentArray ret = new ComponentArray();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("component")) {
                ret.component = parseString(xv);
            } else if (xn.equals("size")) {
                ret.size = parseInt(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Let) {
                ret.lets.add((Let)obj);
            } else if (obj instanceof Initialize) {
                ret.initializes.add((Initialize)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private EventConnections buildEventConnections(XMLElement xel) {
        EventConnections ret = new EventConnections();

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
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof EventSource) {
                ret.eventSources.add((EventSource)obj);
            } else if (obj instanceof EventTarget) {
                ret.eventTargets.add((EventTarget)obj);
            } else if (obj instanceof SourceTargetConnector) {
                ret.sourceTargetConnectors.add((SourceTargetConnector)obj);
            } else if (obj instanceof ConnectionProperties) {
                ret.connectionPropertiess.add((ConnectionProperties)obj);
            } else if (obj instanceof EventArguments) {
                ret.eventArgumentss.add((EventArguments)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Let buildLet(XMLElement xel) {
        Let ret = new Let();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("parameter")) {
                ret.parameter = parseString(xv);
            } else if (xn.equals("array")) {
                ret.array = parseString(xv);
            } else if (xn.equals("value")) {
                ret.value = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Initialize buildInitialize(XMLElement xel) {
        Initialize ret = new Initialize();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("stateVariable")) {
                ret.stateVariable = parseString(xv);
            } else if (xn.equals("array")) {
                ret.array = parseString(xv);
            } else if (xn.equals("value")) {
                ret.value = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private EventSource buildEventSource(XMLElement xel) {
        EventSource ret = new EventSource();

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

    private EventTarget buildEventTarget(XMLElement xel) {
        EventTarget ret = new EventTarget();

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

    private SourceTargetConnector buildSourceTargetConnector(XMLElement xel) {
        SourceTargetConnector ret = new SourceTargetConnector();

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
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof FromArrayConnector) {
                ret.fromArrayConnectors.add((FromArrayConnector)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private FromArrayConnector buildFromArrayConnector(XMLElement xel) {
        FromArrayConnector ret = new FromArrayConnector();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("pre")) {
                ret.pre = parseString(xv);
            } else if (xn.equals("post")) {
                ret.post = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private ConnectionProperties buildConnectionProperties(XMLElement xel) {
        ConnectionProperties ret = new ConnectionProperties();

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
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Property) {
                ret.propertys.add((Property)obj);
            } else if (obj instanceof Delay) {
                ret.delays.add((Delay)obj);
            } else {
                E.warning("unrecognized element " + cel);
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
            } else if (xn.equals("value")) {
                ret.value = parseString(xv);
            } else if (xn.equals("array")) {
                ret.array = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Delay buildDelay(XMLElement xel) {
        Delay ret = new Delay();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("value")) {
                ret.value = parseString(xv);
            } else if (xn.equals("array")) {
                ret.array = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private EventArguments buildEventArguments(XMLElement xel) {
        EventArguments ret = new EventArguments();

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
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof Arg) {
                ret.args.add((Arg)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Arg buildArg(XMLElement xel) {
        Arg ret = new Arg();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("value")) {
                ret.value = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private EventTimes buildEventTimes(XMLElement xel) {
        EventTimes ret = new EventTimes();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("array")) {
                ret.array = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private TimedEvents buildTimedEvents(XMLElement xel) {
        TimedEvents ret = new TimedEvents();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("to")) {
                ret.to = parseString(xv);
            } else if (xn.equals("times")) {
                ret.times = parseString(xv);
            } else if (xn.equals("targets")) {
                ret.targets = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof EventTarget) {
                ret.eventTargets.add((EventTarget)obj);
            } else if (obj instanceof ConnectionProperties) {
                ret.connectionPropertiess.add((ConnectionProperties)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private TargetIndices buildTargetIndices(XMLElement xel) {
        TargetIndices ret = new TargetIndices();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("array")) {
                ret.array = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private DataSources buildDataSources(XMLElement xel) {
        DataSources ret = new DataSources();

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
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof File) {
                ret.files.add((File)obj);
            } else if (obj instanceof Array) {
                ret.arrays.add((Array)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private ListSource buildListSource(XMLElement xel) {
        ListSource ret = new ListSource();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("values")) {
                ret.values = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private File buildFile(XMLElement xel) {
        File ret = new File();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("id")) {
                ret.id = parseString(xv);
            } else if (xn.equals("format")) {
                ret.format = parseString(xv);
            } else if (xn.equals("shape")) {
                ret.shape = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Array buildArray(XMLElement xel) {
        Array ret = new Array();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("integer")) {
                ret.integer = parseBoolean(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof FileSource) {
                ret.fileSources.add((FileSource)obj);
            } else if (obj instanceof ListSource) {
                ret.listSources.add((ListSource)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private FileSource buildFileSource(XMLElement xel) {
        FileSource ret = new FileSource();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("file")) {
                ret.file = parseString(xv);
            } else if (xn.equals("column")) {
                ret.column = parseInt(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
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
            } else if (xn.equals("name")) {
                ret.name = parseString(xv);
            } else if (xn.equals("dt")) {
                ret.dt = parseDouble(xv);
            } else if (xn.equals("endTime")) {
                ret.endTime = parseDouble(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof OutputFiles) {
                ret.outputFiless.add((OutputFiles)obj);
            } else if (obj instanceof Recording) {
                ret.recordings.add((Recording)obj);
            } else if (obj instanceof Display) {
                ret.displays.add((Display)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private OutputFiles buildOutputFiles(XMLElement xel) {
        OutputFiles ret = new OutputFiles();

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
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof File) {
                ret.files.add((File)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private Recording buildRecording(XMLElement xel) {
        Recording ret = new Recording();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("startTime")) {
                ret.startTime = parseDouble(xv);
            } else if (xn.equals("endTime")) {
                ret.endTime = parseDouble(xv);
            } else if (xn.equals("interval")) {
                ret.interval = parseDouble(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof VariableRecording) {
                ret.variableRecordings.add((VariableRecording)obj);
            } else if (obj instanceof InputEventRecording) {
                ret.inputEventRecordings.add((InputEventRecording)obj);
            } else if (obj instanceof OutputEventRecording) {
                ret.outputEventRecordings.add((OutputEventRecording)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private VariableRecording buildVariableRecording(XMLElement xel) {
        VariableRecording ret = new VariableRecording();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("variable")) {
                ret.variable = parseString(xv);
            } else if (xn.equals("file")) {
                ret.file = parseString(xv);
            } else if (xn.equals("componentArray")) {
                ret.componentArray = parseString(xv);
            } else if (xn.equals("indices")) {
                ret.indices = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private InputEventRecording buildInputEventRecording(XMLElement xel) {
        InputEventRecording ret = new InputEventRecording();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("port")) {
                ret.port = parseString(xv);
            } else if (xn.equals("file")) {
                ret.file = parseString(xv);
            } else if (xn.equals("componentArray")) {
                ret.componentArray = parseString(xv);
            } else if (xn.equals("indices")) {
                ret.indices = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private OutputEventRecording buildOutputEventRecording(XMLElement xel) {
        OutputEventRecording ret = new OutputEventRecording();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("port")) {
                ret.port = parseString(xv);
            } else if (xn.equals("file")) {
                ret.file = parseString(xv);
            } else if (xn.equals("componentArray")) {
                ret.componentArray = parseString(xv);
            } else if (xn.equals("indices")) {
                ret.indices = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

    private Display buildDisplay(XMLElement xel) {
        Display ret = new Display();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("id")) {
                ret.id = parseString(xv);
            } else if (xn.equals("interval")) {
                ret.interval = parseDouble(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            Object obj = instantiateFromXMLElement(cel);
            if (xn.equals("UNUSED")) {
            } else if (obj instanceof VariableDisplay) {
                ret.variableDisplays.add((VariableDisplay)obj);
            } else {
                E.warning("unrecognized element " + cel);
            }
        }


        return ret;
    }

    private VariableDisplay buildVariableDisplay(XMLElement xel) {
        VariableDisplay ret = new VariableDisplay();

        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED")) {
            } else if (xn.equals("componentArray")) {
                ret.componentArray = parseString(xv);
            } else if (xn.equals("indices")) {
                ret.indices = parseString(xv);
            } else if (xn.equals("variable")) {
                ret.variable = parseString(xv);
            } else if (xn.equals("color")) {
                ret.color = parseString(xv);
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
            }
        }


        return ret;
    }

}
