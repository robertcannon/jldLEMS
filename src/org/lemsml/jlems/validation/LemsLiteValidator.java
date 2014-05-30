package org.lemsml.jlems.validation;

 
import java.io.File;
import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import org.lemsml.jlems.core.logging.E;
import org.lemsml.jlems.io.util.JUtil;
import org.lemsml.jlems.schema.SchemaRoot;

public class LemsLiteValidator {

	String schemaFileName = "LemsLiteSchema.xsd";
	
	public boolean checkXMLText(String txt) {
		boolean ret = false;
		try {
		
			String schemaSrc = JUtil.getRelativeResource(SchemaRoot.class, schemaFileName);	
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new StreamSource(new StringReader(schemaSrc)));
			Validator validator = schema.newValidator();
			
			Source xmlFile = new StreamSource(new StringReader(txt));
						
			validator.validate(xmlFile);
			ret = true;
			
		} catch (Exception ex) {
			E.info("text is NOT valid against lems lite schema");
			E.info("Reason: " + ex.getLocalizedMessage());
		}
		return ret;
	}
	
	
	
	
	
}
