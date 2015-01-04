package org.lemsml.jld.io.writer;

import org.lemsml.jld.io.xml.*;

import org.lemsml.jld.api.*;
import org.lemsml.jld.model.*;
import org.lemsml.jld.model.core.*;
import org.lemsml.jld.model.type.*;
import org.lemsml.jld.model.dynamics.*;
import org.lemsml.jld.model.structure.*;
import org.lemsml.jld.model.simulation.*;

// NB this is generated code. Don't edit it. If there is a problem, fix the superclass,
// the generator - org.lemsml.jld.generation.ModelFactoryGenerator, or the class being instantiated.

public class GeneratedModelWriter extends AbstractModelWriter {


    public XMLElement writeLemsToXML(Lems tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Lems");
       for (Target x : tgt.getTargets()) {
          ret.addXMLElement(writeTargetToXML(x));
       }
       for (Dimension x : tgt.getDimensions()) {
          ret.addXMLElement(writeDimensionToXML(x));
       }
       for (Unit x : tgt.getUnits()) {
          ret.addXMLElement(writeUnitToXML(x));
       }
       for (Constant x : tgt.getConstants()) {
          ret.addXMLElement(writeConstantToXML(x));
       }
       for (ComponentType x : tgt.getComponentTypes()) {
          ret.addXMLElement(writeComponentTypeToXML(x));
       }
      writeComponentsToXML(tgt, ret);
       return ret;
    }


    public XMLElement writeTargetToXML(Target tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Target");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
      String component = tgt.getComponent();
      if (component != null) {
          ret.addAttribute("component", component);
      }
       return ret;
    }


    public XMLElement writeConstantToXML(Constant tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Constant");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
      String dimension = tgt.getDimension();
      if (dimension != null) {
          ret.addAttribute("dimension", dimension);
      }
             addDoubleAttribute(ret, "value", tgt.getValue());
      String symbol = tgt.getSymbol();
      if (symbol != null) {
          ret.addAttribute("symbol", symbol);
      }
       return ret;
    }


    public XMLElement writeDimensionToXML(Dimension tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Dimension");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
             addIntAttribute(ret, "m", tgt.getM());
             addIntAttribute(ret, "l", tgt.getL());
             addIntAttribute(ret, "t", tgt.getT());
             addIntAttribute(ret, "i", tgt.getI());
             addIntAttribute(ret, "k", tgt.getK());
             addIntAttribute(ret, "n", tgt.getN());
             addIntAttribute(ret, "j", tgt.getJ());
       return ret;
    }


    public XMLElement writeUnitToXML(Unit tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Unit");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
      String dimension = tgt.getDimension();
      if (dimension != null) {
          ret.addAttribute("dimension", dimension);
      }
             addIntAttribute(ret, "power", tgt.getPower());
      String symbol = tgt.getSymbol();
      if (symbol != null) {
          ret.addAttribute("symbol", symbol);
      }
       return ret;
    }


    public XMLElement writeAssertionToXML(Assertion tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Assertion");
       return ret;
    }


    public XMLElement writeComponentTypeToXML(ComponentType tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("ComponentType");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
      String eXtends = tgt.getExtends();
      if (eXtends != null) {
          ret.addAttribute("extends", eXtends);
      }
       for (Parameter x : tgt.getParameters()) {
          ret.addXMLElement(writeParameterToXML(x));
       }
       for (Text x : tgt.getTexts()) {
          ret.addXMLElement(writeTextToXML(x));
       }
       for (Path x : tgt.getPaths()) {
          ret.addXMLElement(writePathToXML(x));
       }
       for (ReceivePort x : tgt.getReceivePorts()) {
          ret.addXMLElement(writeReceivePortToXML(x));
       }
       for (SendPort x : tgt.getSendPorts()) {
          ret.addXMLElement(writeSendPortToXML(x));
       }
       for (Fixed x : tgt.getFixeds()) {
          ret.addXMLElement(writeFixedToXML(x));
       }
       for (Requirement x : tgt.getRequirements()) {
          ret.addXMLElement(writeRequirementToXML(x));
       }
       for (Exposure x : tgt.getExposures()) {
          ret.addXMLElement(writeExposureToXML(x));
       }
       for (Child x : tgt.getChilds()) {
          ret.addXMLElement(writeChildToXML(x));
       }
       for (Children x : tgt.getChildrens()) {
          ret.addXMLElement(writeChildrenToXML(x));
       }
      Dynamics dynamics = tgt.getDynamics();
       if (dynamics != null) {
          ret.addXMLElement(writeDynamicsToXML(dynamics));
       }
      Structure structure = tgt.getStructure();
       if (structure != null) {
          ret.addXMLElement(writeStructureToXML(structure));
       }
      Simulation simulation = tgt.getSimulation();
       if (simulation != null) {
          ret.addXMLElement(writeSimulationToXML(simulation));
       }
       return ret;
    }


    public XMLElement writeParameterToXML(Parameter tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Parameter");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
      String dimension = tgt.getDimension();
      if (dimension != null) {
          ret.addAttribute("dimension", dimension);
      }
       return ret;
    }


    public XMLElement writeTextToXML(Text tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Text");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
       return ret;
    }


    public XMLElement writePathToXML(Path tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Path");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
       return ret;
    }


    public XMLElement writeFixedToXML(Fixed tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Fixed");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
      String dimension = tgt.getDimension();
      if (dimension != null) {
          ret.addAttribute("dimension", dimension);
      }
      String parameter = tgt.getParameter();
      if (parameter != null) {
          ret.addAttribute("parameter", parameter);
      }
      String value = tgt.getValue();
      if (value != null) {
          ret.addAttribute("value", value);
      }
       return ret;
    }


    public XMLElement writePropertyToXML(Property tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Property");
       return ret;
    }


    public XMLElement writeDerivedParameterToXML(DerivedParameter tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("DerivedParameter");
       return ret;
    }


    public XMLElement writeRequirementToXML(Requirement tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Requirement");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
      String dimension = tgt.getDimension();
      if (dimension != null) {
          ret.addAttribute("dimension", dimension);
      }
       return ret;
    }


    public XMLElement writeInstanceRequirementToXML(InstanceRequirement tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("InstanceRequirement");
       return ret;
    }


    public XMLElement writeExposureToXML(Exposure tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Exposure");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
      String dimension = tgt.getDimension();
      if (dimension != null) {
          ret.addAttribute("dimension", dimension);
      }
       return ret;
    }


    public XMLElement writeChildToXML(Child tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Child");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
      String type = tgt.getType();
      if (type != null) {
          ret.addAttribute("type", type);
      }
       return ret;
    }


    public XMLElement writeChildrenToXML(Children tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Children");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
      String type = tgt.getType();
      if (type != null) {
          ret.addAttribute("type", type);
      }
       return ret;
    }


    public XMLElement writeSendPortToXML(SendPort tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("SendPort");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
      String dimension = tgt.getDimension();
      if (dimension != null) {
          ret.addAttribute("dimension", dimension);
      }
       return ret;
    }


    public XMLElement writeReceivePortToXML(ReceivePort tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("ReceivePort");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
      String dimension = tgt.getDimension();
      if (dimension != null) {
          ret.addAttribute("dimension", dimension);
      }
       return ret;
    }


    public XMLElement writeAttachmentsToXML(Attachments tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Attachments");
       return ret;
    }


    public XMLElement writeInsertionToXML(Insertion tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Insertion");
       return ret;
    }


    public XMLElement writeAboutToXML(About tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("About");
       return ret;
    }


    public XMLElement writeDynamicsToXML(Dynamics tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Dynamics");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
       for (StateVariable x : tgt.getStateVariables()) {
          ret.addXMLElement(writeStateVariableToXML(x));
       }
       for (DerivedVariable x : tgt.getDerivedVariables()) {
          ret.addXMLElement(writeDerivedVariableToXML(x));
       }
       for (TimeDerivative x : tgt.getTimeDerivatives()) {
          ret.addXMLElement(writeTimeDerivativeToXML(x));
       }
      OnStart onStart = tgt.getOnStart();
       if (onStart != null) {
          ret.addXMLElement(writeOnStartToXML(onStart));
       }
       for (OnCondition x : tgt.getOnConditions()) {
          ret.addXMLElement(writeOnConditionToXML(x));
       }
       for (OnEvent x : tgt.getOnEvents()) {
          ret.addXMLElement(writeOnEventToXML(x));
       }
       return ret;
    }


    public XMLElement writeStateVariableToXML(StateVariable tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("StateVariable");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
      String exposure = tgt.getExposure();
      if (exposure != null) {
          ret.addAttribute("exposure", exposure);
      }
      String dimension = tgt.getDimension();
      if (dimension != null) {
          ret.addAttribute("dimension", dimension);
      }
       return ret;
    }


    public XMLElement writeStateAssignmentToXML(StateAssignment tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("StateAssignment");
      String value = tgt.getValue();
      if (value != null) {
          ret.addAttribute("value", value);
      }
      String variable = tgt.getVariable();
      if (variable != null) {
          ret.addAttribute("variable", variable);
      }
       return ret;
    }


    public XMLElement writeTimeDerivativeToXML(TimeDerivative tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("TimeDerivative");
      String variable = tgt.getVariable();
      if (variable != null) {
          ret.addAttribute("variable", variable);
      }
      String value = tgt.getValue();
      if (value != null) {
          ret.addAttribute("value", value);
      }
       return ret;
    }


    public XMLElement writeDerivedVariableToXML(DerivedVariable tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("DerivedVariable");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
      String dimension = tgt.getDimension();
      if (dimension != null) {
          ret.addAttribute("dimension", dimension);
      }
      String exposure = tgt.getExposure();
      if (exposure != null) {
          ret.addAttribute("exposure", exposure);
      }
      String value = tgt.getValue();
      if (value != null) {
          ret.addAttribute("value", value);
      }
      String select = tgt.getSelect();
      if (select != null) {
          ret.addAttribute("select", select);
      }
      String reduce = tgt.getReduce();
      if (reduce != null) {
          ret.addAttribute("reduce", reduce);
      }
       return ret;
    }


    public XMLElement writeOnStartToXML(OnStart tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("OnStart");
       for (StateAssignment x : tgt.getStateAssignments()) {
          ret.addXMLElement(writeStateAssignmentToXML(x));
       }
       for (EventOut x : tgt.getEventOuts()) {
          ret.addXMLElement(writeEventOutToXML(x));
       }
       return ret;
    }


    public XMLElement writeOnConditionToXML(OnCondition tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("OnCondition");
       for (StateAssignment x : tgt.getStateAssignments()) {
          ret.addXMLElement(writeStateAssignmentToXML(x));
       }
       for (EventOut x : tgt.getEventOuts()) {
          ret.addXMLElement(writeEventOutToXML(x));
       }
      String test = tgt.getTest();
      if (test != null) {
          ret.addAttribute("test", test);
      }
       return ret;
    }


    public XMLElement writeOnEventToXML(OnEvent tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("OnEvent");
       for (StateAssignment x : tgt.getStateAssignments()) {
          ret.addXMLElement(writeStateAssignmentToXML(x));
       }
       for (EventOut x : tgt.getEventOuts()) {
          ret.addXMLElement(writeEventOutToXML(x));
       }
      String port = tgt.getPort();
      if (port != null) {
          ret.addAttribute("port", port);
      }
       return ret;
    }


    public XMLElement writeEventOutToXML(EventOut tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("EventOut");
      String port = tgt.getPort();
      if (port != null) {
          ret.addAttribute("port", port);
      }
       return ret;
    }


    public XMLElement writeKineticSchemeToXML(KineticScheme tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("KineticScheme");
       return ret;
    }


    public XMLElement writeRegimeToXML(Regime tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Regime");
       return ret;
    }


    public XMLElement writeOnEntryToXML(OnEntry tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("OnEntry");
       return ret;
    }


    public XMLElement writeTransitionToXML(Transition tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Transition");
       return ret;
    }


    public XMLElement writeSuperToXML(Super tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Super");
       return ret;
    }


    public XMLElement writeConditionalDerivedVariableToXML(ConditionalDerivedVariable tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("ConditionalDerivedVariable");
       return ret;
    }


    public XMLElement writeStructureToXML(Structure tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Structure");
       for (MultiInstance x : tgt.getMultiInstances()) {
          ret.addXMLElement(writeMultiInstanceToXML(x));
       }
       for (ForEach x : tgt.getForEachs()) {
          ret.addXMLElement(writeForEachToXML(x));
       }
       for (EventConnection x : tgt.getEventConnections()) {
          ret.addXMLElement(writeEventConnectionToXML(x));
       }
       return ret;
    }


    public XMLElement writeInstanceToXML(Instance tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Instance");
       return ret;
    }


    public XMLElement writeMultiInstanceToXML(MultiInstance tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("MultiInstance");
      String number = tgt.getNumber();
      if (number != null) {
          ret.addAttribute("number", number);
      }
      String component = tgt.getComponent();
      if (component != null) {
          ret.addAttribute("component", component);
      }
       return ret;
    }


    public XMLElement writeForEachToXML(ForEach tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("ForEach");
       for (MultiInstance x : tgt.getMultiInstances()) {
          ret.addXMLElement(writeMultiInstanceToXML(x));
       }
       for (ForEach x : tgt.getForEachs()) {
          ret.addXMLElement(writeForEachToXML(x));
       }
       for (EventConnection x : tgt.getEventConnections()) {
          ret.addXMLElement(writeEventConnectionToXML(x));
       }
      String instances = tgt.getInstances();
      if (instances != null) {
          ret.addAttribute("instances", instances);
      }
      String as = tgt.getAs();
      if (as != null) {
          ret.addAttribute("as", as);
      }
       return ret;
    }


    public XMLElement writeEventConnectionToXML(EventConnection tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("EventConnection");
      String from = tgt.getFrom();
      if (from != null) {
          ret.addAttribute("from", from);
      }
      String to = tgt.getTo();
      if (to != null) {
          ret.addAttribute("to", to);
      }
       return ret;
    }


    public XMLElement writeTunnelToXML(Tunnel tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Tunnel");
       return ret;
    }


    public XMLElement writeWithToXML(With tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("With");
       return ret;
    }


    public XMLElement writeSimulationToXML(Simulation tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Simulation");
      String name = tgt.getName();
      if (name != null) {
          ret.addAttribute("name", name);
      }
       for (DataDisplay x : tgt.getDataDisplays()) {
          ret.addXMLElement(writeDataDisplayToXML(x));
       }
       for (DataWriter x : tgt.getDataWriters()) {
          ret.addXMLElement(writeDataWriterToXML(x));
       }
       for (Recording x : tgt.getRecordings()) {
          ret.addXMLElement(writeRecordingToXML(x));
       }
       for (Run x : tgt.getRuns()) {
          ret.addXMLElement(writeRunToXML(x));
       }
       return ret;
    }


    public XMLElement writeRecordingToXML(Recording tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Recording");
      String quantity = tgt.getQuantity();
      if (quantity != null) {
          ret.addAttribute("quantity", quantity);
      }
      String timeScale = tgt.getTimeScale();
      if (timeScale != null) {
          ret.addAttribute("timeScale", timeScale);
      }
      String scale = tgt.getScale();
      if (scale != null) {
          ret.addAttribute("scale", scale);
      }
      String color = tgt.getColor();
      if (color != null) {
          ret.addAttribute("color", color);
      }
      String destination = tgt.getDestination();
      if (destination != null) {
          ret.addAttribute("destination", destination);
      }
       return ret;
    }


    public XMLElement writeDataDisplayToXML(DataDisplay tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("DataDisplay");
      String title = tgt.getTitle();
      if (title != null) {
          ret.addAttribute("title", title);
      }
      String dataRegion = tgt.getDataRegion();
      if (dataRegion != null) {
          ret.addAttribute("dataRegion", dataRegion);
      }
       return ret;
    }


    public XMLElement writeDataWriterToXML(DataWriter tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("DataWriter");
      String path = tgt.getPath();
      if (path != null) {
          ret.addAttribute("path", path);
      }
      String fileName = tgt.getFileName();
      if (fileName != null) {
          ret.addAttribute("fileName", fileName);
      }
       return ret;
    }


    public XMLElement writeRunToXML(Run tgt) throws APIException, ModelException {
       XMLElement ret = new XMLElement("Run");
      String component = tgt.getComponent();
      if (component != null) {
          ret.addAttribute("component", component);
      }
      String increment = tgt.getIncrement();
      if (increment != null) {
          ret.addAttribute("increment", increment);
      }
      String total = tgt.getTotal();
      if (total != null) {
          ret.addAttribute("total", total);
      }
      String variable = tgt.getVariable();
      if (variable != null) {
          ret.addAttribute("variable", variable);
      }
       return ret;
    }


}
