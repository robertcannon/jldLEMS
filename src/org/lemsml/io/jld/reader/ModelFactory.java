package org.lemsml.io.jld.reader;

import org.lemsml.io.jld.E;
import org.lemsml.io.jld.xml.AbstractModelFactory;
import org.lemsml.io.jld.xml.XMLAttribute;
import org.lemsml.io.jld.xml.XMLElement;
import org.lemsml.io.jldreader.xml.*;

import org.lemsml.api.*;
import org.lemsml.model.*;
import org.lemsml.model.dynamics.*;
import org.lemsml.model.structure.*;
import org.lemsml.model.simulation.*;

// NB this is generated code. Don't edit it. If there is a problem, fix the superclass,
// the generator - org.lemsml.jlems.schema.LemsFactoryGenerator, or the class being instantiated.

public class ModelFactory extends AbstractModelFactory {


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

    public void populateComponentFromXMLElement(Component tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading Component: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateComponentTypeFromXMLElement(ComponentType tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
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
                Dynamics obj = tgt.addDynamics();
                populateDynamicsFromXMLElement(obj, cel);
            } else if (xn.equals("Structure")) {
                Structure obj = tgt.addStructure();
                populateStructureFromXMLElement(obj, cel);
            } else if (xn.equals("Simulation")) {
                Simulation obj = tgt.addSimulation();
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

    public void populateLocalParametersFromXMLElement(LocalParameters tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading LocalParameters: unrecognized attribute " + xa + " " + xv);
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

    public void populateComponentReferenceFromXMLElement(ComponentReference tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading ComponentReference: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateSendPortFromXMLElement(SendPort tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
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

    public void populateIntegerParameterFromXMLElement(IntegerParameter tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading IntegerParameter: unrecognized attribute " + xa + " " + xv);
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


    }

    public void populateStateVariableFromXMLElement(StateVariable tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
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


    }

    public void populateOnConditionFromXMLElement(OnCondition tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading OnCondition: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateOnEventFromXMLElement(OnEvent tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading OnEvent: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateEventOutFromXMLElement(EventOut tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
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


    }

    public void populateMultiInstantiateFromXMLElement(MultiInstantiate tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading MultiInstantiate: unrecognized attribute " + xa + " " + xv);
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
            } else {
                E.warning("reading MultiInstance: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateChildInstanceFromXMLElement(ChildInstance tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading ChildInstance: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateEventConnectionFromXMLElement(EventConnection tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
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


    }

    public void populateRecordFromXMLElement(Record tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
            } else {
                E.warning("reading Record: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

    public void populateDataDisplayFromXMLElement(DataDisplay tgt, XMLElement xel) throws APIException, ModelException {
        for (XMLAttribute xa : xel.getAttributes()) {
            String xn = internalFieldName(xa.getName());
            String xv = xa.getValue();

            if (xn.equals("UNUSED") || xn.equals("name")) {
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
            } else {
                E.warning("reading Run: unrecognized attribute " + xa + " " + xv);
            }
        }


    }

}
