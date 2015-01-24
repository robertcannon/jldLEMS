package org.lemsml.jld.io.reader;

import org.lemsml.jld.api.APIException;
import org.lemsml.jld.io.E;
import org.lemsml.jld.io.xml.XMLAttribute;
import org.lemsml.jld.io.xml.XMLElement;
import org.lemsml.jld.model.Assertion;
import org.lemsml.jld.model.Constant;
import org.lemsml.jld.model.Dimension;
import org.lemsml.jld.model.Insertion;
import org.lemsml.jld.model.Lems;
import org.lemsml.jld.model.Target;
import org.lemsml.jld.model.Unit;
import org.lemsml.jld.model.core.ModelException;
import org.lemsml.jld.model.dynamics.ConditionalDerivedVariable;
import org.lemsml.jld.model.dynamics.DerivedParameter;
import org.lemsml.jld.model.dynamics.DerivedVariable;
import org.lemsml.jld.model.dynamics.Dynamics;
import org.lemsml.jld.model.dynamics.EventOut;
import org.lemsml.jld.model.dynamics.KineticScheme;
import org.lemsml.jld.model.dynamics.OnCondition;
import org.lemsml.jld.model.dynamics.OnEntry;
import org.lemsml.jld.model.dynamics.OnEvent;
import org.lemsml.jld.model.dynamics.OnStart;
import org.lemsml.jld.model.dynamics.Regime;
import org.lemsml.jld.model.dynamics.StateAssignment;
import org.lemsml.jld.model.dynamics.StateVariable;
import org.lemsml.jld.model.dynamics.Super;
import org.lemsml.jld.model.dynamics.TimeDerivative;
import org.lemsml.jld.model.dynamics.Transition;
import org.lemsml.jld.model.simulation.DataDisplay;
import org.lemsml.jld.model.simulation.DataWriter;
import org.lemsml.jld.model.simulation.Recording;
import org.lemsml.jld.model.simulation.Run;
import org.lemsml.jld.model.simulation.Simulation;
import org.lemsml.jld.model.structure.EventConnection;
import org.lemsml.jld.model.structure.ForEach;
import org.lemsml.jld.model.structure.Instance;
import org.lemsml.jld.model.structure.MultiInstance;
import org.lemsml.jld.model.structure.Structure;
import org.lemsml.jld.model.structure.Tunnel;
import org.lemsml.jld.model.structure.With;
import org.lemsml.jld.model.type.About;
import org.lemsml.jld.model.type.Attachments;
import org.lemsml.jld.model.type.Child;
import org.lemsml.jld.model.type.Children;
import org.lemsml.jld.model.type.ComponentType;
import org.lemsml.jld.model.type.Exposure;
import org.lemsml.jld.model.type.Fixed;
import org.lemsml.jld.model.type.InstanceRequirement;
import org.lemsml.jld.model.type.Parameter;
import org.lemsml.jld.model.type.Path;
import org.lemsml.jld.model.type.Property;
import org.lemsml.jld.model.type.ReceivePort;
import org.lemsml.jld.model.type.Requirement;
import org.lemsml.jld.model.type.SendPort;
import org.lemsml.jld.model.type.Text;

// NB this is generated code. Don't edit it. If there is a problem, fix the superclass,
// the generator - org.lemsml.jld.generation.ModelFactoryGenerator, or the class being instantiated.

public class GeneratedModelReader extends AbstractModelReader {


    public void populateLemsFromXMLElement(Lems tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading Lems: unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            if (xn.equals("UNUSED")) {

            } else if (xn.equals("Target")) {
                String eltname = getNameAttribute(cel);
                Target obj = tgt.addTarget(eltname);
                populateTargetFromXMLElement(obj, cel);

            } else if (xn.equals("Dimension")) {
                String eltname = getNameAttribute(cel);
                Dimension obj = tgt.addDimension(eltname);
                populateDimensionFromXMLElement(obj, cel);

            } else if (xn.equals("Unit")) {
                String eltname = getNameAttribute(cel);
                Unit obj = tgt.addUnit(eltname);
                populateUnitFromXMLElement(obj, cel);

            } else if (xn.equals("Constant")) {
                String eltname = getNameAttribute(cel);
                Constant obj = tgt.addConstant(eltname);
                populateConstantFromXMLElement(obj, cel);

            } else if (xn.equals("ComponentType")) {
                String eltname = getNameAttribute(cel);
                ComponentType obj = tgt.addComponentType(eltname);
                populateComponentTypeFromXMLElement(obj, cel);
            } else {
                readComponentFromXMLElement(tgt, cel);
            }
        }


    }

    public void populateTargetFromXMLElement(Target tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("component")) {
                tgt.setComponent(parseString(xv));
            } else {
                E.warning("reading Target: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateConstantFromXMLElement(Constant tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("dimension")) {
                tgt.setDimension(parseString(xv));
            } else if (xn.equals("value")) {
                tgt.setValue(parseDouble(xv));
            } else if (xn.equals("symbol")) {
                tgt.setSymbol(parseString(xv));
            } else {
                E.warning("reading Constant: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateDimensionFromXMLElement(Dimension tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("m")) {
                tgt.setM(parseInt(xv));
            } else if (xn.equals("l")) {
                tgt.setL(parseInt(xv));
            } else if (xn.equals("t")) {
                tgt.setT(parseInt(xv));
            } else if (xn.equals("i")) {
                tgt.setI(parseInt(xv));
            } else if (xn.equals("k")) {
                tgt.setK(parseInt(xv));
            } else if (xn.equals("n")) {
                tgt.setN(parseInt(xv));
            } else if (xn.equals("j")) {
                tgt.setJ(parseInt(xv));
            } else {
                E.warning("reading Dimension: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateUnitFromXMLElement(Unit tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("dimension")) {
                tgt.setDimension(parseString(xv));
            } else if (xn.equals("power")) {
                tgt.setPower(parseInt(xv));
            } else if (xn.equals("symbol")) {
                tgt.setSymbol(parseString(xv));
            } else {
                E.warning("reading Unit: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateAssertionFromXMLElement(Assertion tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading Assertion: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateComponentTypeFromXMLElement(ComponentType tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("eXtends")) {
                tgt.setExtends(parseString(xv));
            } else {
                E.warning("reading ComponentType: unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            if (xn.equals("UNUSED")) {

            } else if (xn.equals("Parameter")) {
                String eltname = getNameAttribute(cel);
                Parameter obj = tgt.addParameter(eltname);
                populateParameterFromXMLElement(obj, cel);

            } else if (xn.equals("Text")) {
                String eltname = getNameAttribute(cel);
                Text obj = tgt.addText(eltname);
                populateTextFromXMLElement(obj, cel);

            } else if (xn.equals("Path")) {
                String eltname = getNameAttribute(cel);
                Path obj = tgt.addPath(eltname);
                populatePathFromXMLElement(obj, cel);

            } else if (xn.equals("ReceivePort")) {
                String eltname = getNameAttribute(cel);
                ReceivePort obj = tgt.addReceivePort(eltname);
                populateReceivePortFromXMLElement(obj, cel);

            } else if (xn.equals("SendPort")) {
                String eltname = getNameAttribute(cel);
                SendPort obj = tgt.addSendPort(eltname);
                populateSendPortFromXMLElement(obj, cel);

            } else if (xn.equals("Fixed")) {
                String eltname = getNameAttribute(cel);
                Fixed obj = tgt.addFixed(eltname);
                populateFixedFromXMLElement(obj, cel);

            } else if (xn.equals("Requirement")) {
                String eltname = getNameAttribute(cel);
                Requirement obj = tgt.addRequirement(eltname);
                populateRequirementFromXMLElement(obj, cel);

            } else if (xn.equals("Exposure")) {
                String eltname = getNameAttribute(cel);
                Exposure obj = tgt.addExposure(eltname);
                populateExposureFromXMLElement(obj, cel);

            } else if (xn.equals("Child")) {
                String eltname = getNameAttribute(cel);
                Child obj = tgt.addChild(eltname);
                populateChildFromXMLElement(obj, cel);

            } else if (xn.equals("Children")) {
                String eltname = getNameAttribute(cel);
                Children obj = tgt.addChildren(eltname);
                populateChildrenFromXMLElement(obj, cel);

            } else if (xn.equals("Dynamics")) {
                Dynamics obj = tgt.createDynamics();
                populateDynamicsFromXMLElement(obj, cel);

            } else if (xn.equals("Structure")) {
                Structure obj = tgt.createStructure();
                populateStructureFromXMLElement(obj, cel);

            } else if (xn.equals("Simulation")) {
                Simulation obj = tgt.createSimulation();
                populateSimulationFromXMLElement(obj, cel);
            } else {
                E.warning("reading ComponentType: unrecognized element " + cel);
            }
        }


    }

    public void populateParameterFromXMLElement(Parameter tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("dimension")) {
                tgt.setDimension(parseString(xv));
            } else {
                E.warning("reading Parameter: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateTextFromXMLElement(Text tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading Text: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populatePathFromXMLElement(Path tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading Path: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateFixedFromXMLElement(Fixed tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("dimension")) {
                tgt.setDimension(parseString(xv));
            } else if (xn.equals("parameter")) {
                tgt.setParameter(parseString(xv));
            } else if (xn.equals("value")) {
                tgt.setValue(parseString(xv));
            } else {
                E.warning("reading Fixed: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populatePropertyFromXMLElement(Property tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading Property: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateDerivedParameterFromXMLElement(DerivedParameter tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading DerivedParameter: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateRequirementFromXMLElement(Requirement tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("dimension")) {
                tgt.setDimension(parseString(xv));
            } else {
                E.warning("reading Requirement: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateInstanceRequirementFromXMLElement(InstanceRequirement tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading InstanceRequirement: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateExposureFromXMLElement(Exposure tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("dimension")) {
                tgt.setDimension(parseString(xv));
            } else {
                E.warning("reading Exposure: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateChildFromXMLElement(Child tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("type")) {
                tgt.setType(parseString(xv));
            } else {
                E.warning("reading Child: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateChildrenFromXMLElement(Children tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("type")) {
                tgt.setType(parseString(xv));
            } else {
                E.warning("reading Children: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateSendPortFromXMLElement(SendPort tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("dimension")) {
                tgt.setDimension(parseString(xv));
            } else {
                E.warning("reading SendPort: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateReceivePortFromXMLElement(ReceivePort tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("dimension")) {
                tgt.setDimension(parseString(xv));
            } else {
                E.warning("reading ReceivePort: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateAttachmentsFromXMLElement(Attachments tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading Attachments: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateInsertionFromXMLElement(Insertion tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading Insertion: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateAboutFromXMLElement(About tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading About: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateDynamicsFromXMLElement(Dynamics tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading Dynamics: unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            if (xn.equals("UNUSED")) {

            } else if (xn.equals("StateVariable")) {
                String eltname = getNameAttribute(cel);
                StateVariable obj = tgt.addStateVariable(eltname);
                populateStateVariableFromXMLElement(obj, cel);

            } else if (xn.equals("DerivedVariable")) {
                String eltname = getNameAttribute(cel);
                DerivedVariable obj = tgt.addDerivedVariable(eltname);
                populateDerivedVariableFromXMLElement(obj, cel);

            } else if (xn.equals("TimeDerivative")) {
                String eltname = getNameAttribute(cel);
                TimeDerivative obj = tgt.addTimeDerivative(eltname);
                populateTimeDerivativeFromXMLElement(obj, cel);

            } else if (xn.equals("OnStart")) {
                OnStart obj = tgt.createOnStart();
                populateOnStartFromXMLElement(obj, cel);

            } else if (xn.equals("OnCondition")) {
                String eltname = getNameAttribute(cel);
                OnCondition obj = tgt.addOnCondition(eltname);
                populateOnConditionFromXMLElement(obj, cel);

            } else if (xn.equals("OnEvent")) {
                String eltname = getNameAttribute(cel);
                OnEvent obj = tgt.addOnEvent(eltname);
                populateOnEventFromXMLElement(obj, cel);
            } else {
                E.warning("reading Dynamics: unrecognized element " + cel);
            }
        }


    }

    public void populateStateVariableFromXMLElement(StateVariable tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("exposure")) {
                tgt.setExposure(parseString(xv));
            } else if (xn.equals("dimension")) {
                tgt.setDimension(parseString(xv));
            } else {
                E.warning("reading StateVariable: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateStateAssignmentFromXMLElement(StateAssignment tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("value")) {
                tgt.setValue(parseString(xv));
            } else if (xn.equals("variable")) {
                tgt.setVariable(parseString(xv));
            } else {
                E.warning("reading StateAssignment: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateTimeDerivativeFromXMLElement(TimeDerivative tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("variable")) {
                tgt.setVariable(parseString(xv));
            } else if (xn.equals("value")) {
                tgt.setValue(parseString(xv));
            } else {
                E.warning("reading TimeDerivative: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateDerivedVariableFromXMLElement(DerivedVariable tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("dimension")) {
                tgt.setDimension(parseString(xv));
            } else if (xn.equals("exposure")) {
                tgt.setExposure(parseString(xv));
            } else if (xn.equals("value")) {
                tgt.setValue(parseString(xv));
            } else if (xn.equals("select")) {
                tgt.setSelect(parseString(xv));
            } else if (xn.equals("reduce")) {
                tgt.setReduce(parseString(xv));
            } else {
                E.warning("reading DerivedVariable: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateOnStartFromXMLElement(OnStart tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading OnStart: unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            if (xn.equals("UNUSED")) {

            } else if (xn.equals("StateAssignment")) {
                String eltname = getNameAttribute(cel);
                StateAssignment obj = tgt.addStateAssignment(eltname);
                populateStateAssignmentFromXMLElement(obj, cel);

            } else if (xn.equals("EventOut")) {
                String eltname = getNameAttribute(cel);
                EventOut obj = tgt.addEventOut(eltname);
                populateEventOutFromXMLElement(obj, cel);
            } else {
                E.warning("reading OnStart: unrecognized element " + cel);
            }
        }


    }

    public void populateOnConditionFromXMLElement(OnCondition tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("test")) {
                tgt.setTest(parseString(xv));
            } else {
                E.warning("reading OnCondition: unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            if (xn.equals("UNUSED")) {

            } else if (xn.equals("StateAssignment")) {
                String eltname = getNameAttribute(cel);
                StateAssignment obj = tgt.addStateAssignment(eltname);
                populateStateAssignmentFromXMLElement(obj, cel);

            } else if (xn.equals("EventOut")) {
                String eltname = getNameAttribute(cel);
                EventOut obj = tgt.addEventOut(eltname);
                populateEventOutFromXMLElement(obj, cel);
            } else {
                E.warning("reading OnCondition: unrecognized element " + cel);
            }
        }


    }

    public void populateOnEventFromXMLElement(OnEvent tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("port")) {
                tgt.setPort(parseString(xv));
            } else {
                E.warning("reading OnEvent: unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            if (xn.equals("UNUSED")) {

            } else if (xn.equals("StateAssignment")) {
                String eltname = getNameAttribute(cel);
                StateAssignment obj = tgt.addStateAssignment(eltname);
                populateStateAssignmentFromXMLElement(obj, cel);

            } else if (xn.equals("EventOut")) {
                String eltname = getNameAttribute(cel);
                EventOut obj = tgt.addEventOut(eltname);
                populateEventOutFromXMLElement(obj, cel);
            } else {
                E.warning("reading OnEvent: unrecognized element " + cel);
            }
        }


    }

    public void populateEventOutFromXMLElement(EventOut tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("port")) {
                tgt.setPort(parseString(xv));
            } else {
                E.warning("reading EventOut: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateKineticSchemeFromXMLElement(KineticScheme tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading KineticScheme: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateRegimeFromXMLElement(Regime tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading Regime: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateOnEntryFromXMLElement(OnEntry tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading OnEntry: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateTransitionFromXMLElement(Transition tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading Transition: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateSuperFromXMLElement(Super tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading Super: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateConditionalDerivedVariableFromXMLElement(ConditionalDerivedVariable tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading ConditionalDerivedVariable: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateStructureFromXMLElement(Structure tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading Structure: unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            if (xn.equals("UNUSED")) {

            } else if (xn.equals("MultiInstance")) {
                String eltname = getNameAttribute(cel);
                MultiInstance obj = tgt.addMultiInstance(eltname);
                populateMultiInstanceFromXMLElement(obj, cel);

            } else if (xn.equals("ForEach")) {
                String eltname = getNameAttribute(cel);
                ForEach obj = tgt.addForEach(eltname);
                populateForEachFromXMLElement(obj, cel);

            } else if (xn.equals("EventConnection")) {
                String eltname = getNameAttribute(cel);
                EventConnection obj = tgt.addEventConnection(eltname);
                populateEventConnectionFromXMLElement(obj, cel);
            } else {
                E.warning("reading Structure: unrecognized element " + cel);
            }
        }


    }

    public void populateInstanceFromXMLElement(Instance tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading Instance: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateMultiInstanceFromXMLElement(MultiInstance tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("number")) {
                tgt.setNumber(parseString(xv));
            } else if (xn.equals("component")) {
                tgt.setComponent(parseString(xv));
            } else {
                E.warning("reading MultiInstance: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateForEachFromXMLElement(ForEach tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("instances")) {
                tgt.setInstances(parseString(xv));
            } else if (xn.equals("as")) {
                tgt.setAs(parseString(xv));
            } else {
                E.warning("reading ForEach: unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            if (xn.equals("UNUSED")) {

            } else if (xn.equals("MultiInstance")) {
                String eltname = getNameAttribute(cel);
                MultiInstance obj = tgt.addMultiInstance(eltname);
                populateMultiInstanceFromXMLElement(obj, cel);

            } else if (xn.equals("ForEach")) {
                String eltname = getNameAttribute(cel);
                ForEach obj = tgt.addForEach(eltname);
                populateForEachFromXMLElement(obj, cel);

            } else if (xn.equals("EventConnection")) {
                String eltname = getNameAttribute(cel);
                EventConnection obj = tgt.addEventConnection(eltname);
                populateEventConnectionFromXMLElement(obj, cel);
            } else {
                E.warning("reading ForEach: unrecognized element " + cel);
            }
        }


    }

    public void populateEventConnectionFromXMLElement(EventConnection tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("from")) {
                tgt.setFrom(parseString(xv));
            } else if (xn.equals("to")) {
                tgt.setTo(parseString(xv));
            } else {
                E.warning("reading EventConnection: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateTunnelFromXMLElement(Tunnel tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading Tunnel: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateWithFromXMLElement(With tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading With: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateSimulationFromXMLElement(Simulation tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading Simulation: unrecognized attribute " + xa + " " + xv);
            }
        }


        for (XMLElement cel : xel.getXMLElements()) {
            String xn = cel.getTag();

            if (xn.equals("UNUSED")) {

            } else if (xn.equals("DataDisplay")) {
                String eltname = getNameAttribute(cel);
                DataDisplay obj = tgt.addDataDisplay(eltname);
                populateDataDisplayFromXMLElement(obj, cel);

            } else if (xn.equals("DataWriter")) {
                String eltname = getNameAttribute(cel);
                DataWriter obj = tgt.addDataWriter(eltname);
                populateDataWriterFromXMLElement(obj, cel);

            } else if (xn.equals("Recording")) {
                String eltname = getNameAttribute(cel);
                Recording obj = tgt.addRecording(eltname);
                populateRecordingFromXMLElement(obj, cel);

            } else if (xn.equals("Run")) {
                String eltname = getNameAttribute(cel);
                Run obj = tgt.addRun(eltname);
                populateRunFromXMLElement(obj, cel);
            } else {
                E.warning("reading Simulation: unrecognized element " + cel);
            }
        }


    }

    public void populateRecordingFromXMLElement(Recording tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("quantity")) {
                tgt.setQuantity(parseString(xv));
            } else if (xn.equals("timeScale")) {
                tgt.setTimeScale(parseString(xv));
            } else if (xn.equals("scale")) {
                tgt.setScale(parseString(xv));
            } else if (xn.equals("color")) {
                tgt.setColor(parseString(xv));
            } else if (xn.equals("destination")) {
                tgt.setDestination(parseString(xv));
            } else {
                E.warning("reading Recording: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateDataDisplayFromXMLElement(DataDisplay tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("title")) {
                tgt.setTitle(parseString(xv));
            } else if (xn.equals("dataRegion")) {
                tgt.setDataRegion(parseString(xv));
            } else {
                E.warning("reading DataDisplay: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateDataWriterFromXMLElement(DataWriter tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("path")) {
                tgt.setPath(parseString(xv));
            } else if (xn.equals("fileName")) {
                tgt.setFileName(parseString(xv));
            } else {
                E.warning("reading DataWriter: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateRunFromXMLElement(Run tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else if (xn.equals("component")) {
                tgt.setComponent(parseString(xv));
            } else if (xn.equals("increment")) {
                tgt.setIncrement(parseString(xv));
            } else if (xn.equals("total")) {
                tgt.setTotal(parseString(xv));
            } else if (xn.equals("variable")) {
                tgt.setVariable(parseString(xv));
            } else {
                E.warning("reading Run: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

}
