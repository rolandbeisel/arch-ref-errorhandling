package com.beisel.architecture.reference.errorhandling.adapter.in.web.validator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class XsdSchemaValidatorTest {

  @Test
  void validationReturnsErrorsWhenXmlIsInvalid() {
    String xml = """
        <?xml version="1.0" encoding="UTF-8" ?>
        <person>
            <invalid>Roland</invalid>
            <lastname>Beisel</lastname>
            <department>IT Architecture</department>
        </person>""";

    XsdSchemaValidator xsdSchemaValidator = new XsdSchemaValidator("person.xsd");
    XsdSchemaValidatorResult result = xsdSchemaValidator.validate(xml);

    assertThat(result.hasErrors()).isTrue();
  }

  @Test
  void validationReturnsNoErrorsWhenXmlIsValid() {
    String xml = """
        <?xml version="1.0" encoding="UTF-8" ?>
        <person>
            <firstname>Roland</firstname>
            <lastname>Beisel</lastname>
            <department>IT Architecture</department>
        </person>""";

    XsdSchemaValidator xsdSchemaValidator = new XsdSchemaValidator("person.xsd");
    XsdSchemaValidatorResult result = xsdSchemaValidator.validate(xml);

    assertThat(result.hasErrors()).isFalse();
  }
}
