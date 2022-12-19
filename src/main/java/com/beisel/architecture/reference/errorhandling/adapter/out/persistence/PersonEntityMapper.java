package com.beisel.architecture.reference.errorhandling.adapter.out.persistence;

import com.beisel.architecture.reference.errorhandling.adapter.out.persistence.model.PersonEntity;
import com.beisel.architecture.reference.errorhandling.domain.model.Person;
import com.beisel.architecture.reference.errorhandling.domain.model.PersonId;
import com.beisel.architecture.reference.errorhandling.shared.ErrorAwareResult;

public class PersonEntityMapper {

  static PersonEntity map(Person person) {
    return PersonEntity.builder()
        .personId(person.getPersonId().toString())
        .version(person.getVersion())
        .firstname(person.getFirstname())
        .lastname(person.getLastname())
        .department(person.getDepartment())
        .build();
  }

  static ErrorAwareResult<Person> map(PersonEntity personEntity) {
    Person person = Person.builder()
        .personId(PersonId.fromString(personEntity.getPersonId()))
        .version(personEntity.getVersion())
        .firstname(personEntity.getFirstname())
        .lastname(personEntity.getLastname())
        .department(personEntity.getDepartment())
        .build();

    return ErrorAwareResult.successful(person);
  }
}
