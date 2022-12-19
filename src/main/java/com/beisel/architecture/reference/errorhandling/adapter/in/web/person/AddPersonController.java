package com.beisel.architecture.reference.errorhandling.adapter.in.web.person;

import com.beisel.architecture.reference.errorhandling.shared.ErrorAwareResult;
import com.beisel.architecture.reference.errorhandling.adapter.in.web.validator.XsdSchemaValidator;
import com.beisel.architecture.reference.errorhandling.adapter.in.web.validator.XsdSchemaValidatorResult;
import com.beisel.architecture.reference.errorhandling.application.port.in.AddPersonPort;
import com.beisel.architecture.reference.errorhandling.application.port.in.AddPersonPort.AddPersonCommand;
import com.beisel.architecture.reference.errorhandling.domain.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AddPersonController {

  private static final String PERSON_XSD_FILE = "person.xsd";

  private final AddPersonPort addPersonPort;

  @PostMapping(value = "/person", consumes = "application/xml", produces = "application/xml")
  public ResponseEntity<ErrorAwareResult<Person>> addPerson(@RequestBody String personXml) {
    XsdSchemaValidatorResult validationResult = new XsdSchemaValidator(PERSON_XSD_FILE).validate(personXml);
    if (validationResult.hasErrors()) {
      return ResponseEntity
          .status(400)
          .body(ErrorAwareResult.failed(validationResult.getErrors()));
    }

    ErrorAwareResult<AddPersonCommand> mappingResult = AddPersonCommandMapper.map(personXml);
    if (mappingResult.hasErrors()) {
      return ResponseEntity
          .status(400)
          .body(ErrorAwareResult.failed(mappingResult.getErrors()));
    }

    ErrorAwareResult<Person> result = addPersonPort.add(mappingResult.getPayload().get());
    if (result.hasErrors()) {
      return ResponseEntity
          .status(400)
          .body(ErrorAwareResult.failed(result.getErrors()));
    }

    return ResponseEntity.ok(ErrorAwareResult.successful(result.getPayload().get()));
  }
}
