package org.lemsml.jlems.validation;

 
import java.io.File;
import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class FileSchemaValidator {

	
	
	public void checkXMLText(String txt) {

		try {
		
			File schemaFile = new File("schema/LemsLiteSchema.xsd");
			Source xmlFile = new StreamSource(new StringReader(txt));
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);
			System.out.println("Generated text IS valid LemsLite");
		
		} catch (Exception ex) {
			System.out.println("text is NOT valid against lems lite schema");
			System.out.println("Reason: " + ex.getLocalizedMessage());
		}
	}
	
	
	
	
	
}
