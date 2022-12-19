package com.beisel.architecture.reference.errorhandling.application.service;

import com.beisel.architecture.reference.errorhandling.shared.ErrorAwareResult;
import com.beisel.architecture.reference.errorhandling.application.port.in.AddPersonPort;
import com.beisel.architecture.reference.errorhandling.application.port.out.PersonPersistencePort;
import com.beisel.architecture.reference.errorhandling.domain.model.Person;
import com.beisel.architecture.reference.errorhandling.domain.model.PersonId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddPersonCommandHandler implements AddPersonPort {

  private final PersonPersistencePort personPersistencePort;

  @Override
  public ErrorAwareResult<Person> add(AddPersonCommand command) {
    ErrorAwareResult<Person> persistenceResult = personPersistencePort.save(map(command));
    if (persistenceResult.hasErrors()) {
      return ErrorAwareResult.failed(persistenceResult.getErrors());
    }

    return ErrorAwareResult.successful(persistenceResult.getPayload().get());
  }

  private Person map(AddPersonCommand command) {
    return Person.builder()
        .personId(PersonId.fromString(command.personId()))
        .version(command.version())
        .firstname(command.firstname())
        .lastname(command.lastname())
        .department(command.department())
        .build();
  }
}
