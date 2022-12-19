package com.beisel.architecture.reference.errorhandling.adapter.in.web.validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.springframework.util.ResourceUtils;
import org.xml.sax.SAXException;

public class XsdSchemaValidator {

  private final Validator validator;

  public XsdSchemaValidator(String xsdFile) {
    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

    try {
      Source schemaFile = new StreamSource(getFile(xsdFile));
      this.validator = factory.newSchema(schemaFile).newValidator();
    } catch (FileNotFoundException | SAXException e) {
      throw new XsdSchemaValidatorInitializationException(e.getMessage());
    }
  }

  public XsdSchemaValidatorResult validate(String inputXml) {
    XsdSchemaValidatorResult result = new XsdSchemaValidatorResult();

    try {
      validator.validate(new StreamSource(new StringReader(inputXml)));
    } catch (SAXException | IOException e) {
      result.addError(e.getMessage());
    }

    return result;
  }

  private File getFile(String location) throws FileNotFoundException {
    return ResourceUtils.getFile("classpath:xsd/" + location);
  }
}
