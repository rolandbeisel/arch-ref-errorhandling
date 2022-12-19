package com.beisel.architecture.reference.errorhandling.adapter.in.web.person;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.beisel.architecture.reference.errorhandling.adapter.in.web.GenericCommandMappingResult;
import com.beisel.architecture.reference.errorhandling.application.port.in.AddPersonPort.AddPersonCommand;
import org.junit.jupiter.api.Test;

class AddPersonCommandMapperTest {

  @Test
  void mapsStringToObject() {
    String xml = """
        <?xml version="1.0" encoding="UTF-8" ?>
        <person>
            <personId>9536ffde-c794-48f4-8902-463ecf1fb418</personId>
            <firstname>Roland</firstname>
            <lastname>Beisel</lastname>
            <department>IT Architecture</department>
        </person>""";

    GenericCommandMappingResult<AddPersonCommand> result = AddPersonCommandMapper.map(xml);

    assertThat(result.hasErrors()).isFalse();
    assertThat(result.getPayload().isEmpty()).isFalse();
    assertThat(result.getPayload().get()).isInstanceOf(AddPersonCommand.class);
  }

  @Test
  void collectsErrorsWhenStringIsInvalid() {
    String xml = """
        <?xml version="1.0" encoding="UTF-8" ?>
        <person>
            <invalid>Roland</invalid>
            <lastname>Beisel</lastname>
            <department>IT Architecture</department>
        </person>""";

    GenericCommandMappingResult<AddPersonCommand> result = AddPersonCommandMapper.map(xml);

    assertThat(result.getPayload()).isEmpty();
    assertThat(result.hasErrors()).isTrue();
    assertThat(result.getErrors().size()).isEqualTo(1);
  }
}
