package com.beisel.architecture.reference.errorhandling.application.port.out;

import com.beisel.architecture.reference.errorhandling.shared.ErrorAwareResult;
import com.beisel.architecture.reference.errorhandling.domain.model.Person;

public interface PersonPersistencePort {

  ErrorAwareResult<Person> save(Person person);
}
