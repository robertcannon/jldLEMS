package org.lemsml.io.jldreader;

import org.lemsml.model.*;
import org.lemsml.model.dynamics.*;
import org.lemsml.model.structure.*;
import org.lemsml.model.simulation.*;

import org.lemsml.io.jldreader.xml.XMLElement;
import org.lemsml.io.jldreader.xml.XMLAttribute;
import org.lemsml.io.jldreader.E;
// NB this is generated code. Don't edit it. If there is a problem, fix the superclass,
// the generator - org.lemsml.jlems.schema.LemsFactoryGenerator, or the class being instantiated.

public class ModelFactory extends AbstractModelFactory {


    public void populateFromXMLElement(Lems lems, XMLElement xel) {
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
        } else if (tag.equals("LocalParameters")) {
            ret = buildLocalParameters(xel);
        } else if (tag.equals("Property")) {
            ret = buildProperty(xel);
        } else if (tag.equals("DerivedParameter")) {
            ret = buildDerivedParameter(xel);
        } else if (tag.equals("Requirement")) {
            ret = buildRequirement(xel);
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
        } else if (tag.equals("SendPort")) {
            ret = buildSendPort(xel);
        } else if (tag.equals("ReceivePort")) {
            ret = buildReceivePort(xel);
        } else if (tag.equals("Text")) {
            ret = buildText(xel);
        } else if (tag.equals("Attachments")) {
            ret = buildAttachments(xel);
        } else if (tag.equals("Insertion")) {
            ret = buildInsertion(xel);
        } else if (tag.equals("IntegerParameter")) {
            ret = buildIntegerParameter(xel);
        } else if (tag.equals("About")) {
            ret = buildAbout(xel);
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
        } else if (tag.equals("Super")) {
            ret = buildSuper(xel);
        } else if (tag.equals("ConditionalDerivedVariable")) {
            ret = buildConditionalDerivedVariable(xel);
        } else if (tag.equals("Structure")) {
            ret = buildStructure(xel);
        } else if (tag.equals("MultiInstantiate")) {
            ret = buildMultiInstantiate(xel);
        } else if (tag.equals("Instance")) {
            ret = buildInstance(xel);
        } else if (tag.equals("MultiInstance")) {
            ret = buildMultiInstance(xel);
        } else if (tag.equals("ChildInstance")) {
            ret = buildChildInstance(xel);
        } else if (tag.equals("EventConnection")) {
            ret = buildEventConnection(xel);
        } else if (tag.equals("Tunnel")) {
            ret = buildTunnel(xel);
        } else if (tag.equals("With")) {
            ret = buildWith(xel);
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
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
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
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
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
            } else {
                E.warning("unrecognized attribute " + xa + " " + xv);
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

    private Dynamics buildDynamics(XMLElement xel) {
        Dynamics ret = new Dynamics();

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

    private StateVariable buildStateVariable(XMLElement xel) {
        StateVariable ret = new StateVariable();

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

    private StateAssignment buildStateAssignment(XMLElement xel) {
        StateAssignment ret = new StateAssignment();

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

    private TimeDerivative buildTimeDerivative(XMLElement xel) {
        TimeDerivative ret = new TimeDerivative();

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

    private DerivedVariable buildDerivedVariable(XMLElement xel) {
        DerivedVariable ret = new DerivedVariable();

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


        return ret;
    }

    private OnCondition buildOnCondition(XMLElement xel) {
        OnCondition ret = new OnCondition();

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

    private OnEvent buildOnEvent(XMLElement xel) {
        OnEvent ret = new OnEvent();

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

    private EventOut buildEventOut(XMLElement xel) {
        EventOut ret = new EventOut();

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

    private KineticScheme buildKineticScheme(XMLElement xel) {
        KineticScheme ret = new KineticScheme();

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

    private Regime buildRegime(XMLElement xel) {
        Regime ret = new Regime();

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


        return ret;
    }

    private Transition buildTransition(XMLElement xel) {
        Transition ret = new Transition();

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


        return ret;
    }

    private MultiInstantiate buildMultiInstantiate(XMLElement xel) {
        MultiInstantiate ret = new MultiInstantiate();

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

    private Instance buildInstance(XMLElement xel) {
        Instance ret = new Instance();

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

    private MultiInstance buildMultiInstance(XMLElement xel) {
        MultiInstance ret = new MultiInstance();

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

    private ChildInstance buildChildInstance(XMLElement xel) {
        ChildInstance ret = new ChildInstance();

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

    private EventConnection buildEventConnection(XMLElement xel) {
        EventConnection ret = new EventConnection();

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

    private Tunnel buildTunnel(XMLElement xel) {
        Tunnel ret = new Tunnel();

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

    private With buildWith(XMLElement xel) {
        With ret = new With();

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


        return ret;
    }

    private Record buildRecord(XMLElement xel) {
        Record ret = new Record();

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

    private DataDisplay buildDataDisplay(XMLElement xel) {
        DataDisplay ret = new DataDisplay();

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

    private DataWriter buildDataWriter(XMLElement xel) {
        DataWriter ret = new DataWriter();

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

    private Run buildRun(XMLElement xel) {
        Run ret = new Run();

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

}
