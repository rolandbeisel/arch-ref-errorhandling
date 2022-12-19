package com.beisel.architecture.reference.errorhandling.adapter.out.persistence;

import static com.beisel.architecture.reference.errorhandling.adapter.out.persistence.PersonEntityMapper.map;

import com.beisel.architecture.reference.errorhandling.application.port.out.PersonPersistencePort;
import com.beisel.architecture.reference.errorhandling.domain.model.Person;
import com.beisel.architecture.reference.errorhandling.shared.ErrorAwareResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class PersonPersistenceAdapter implements PersonPersistencePort {

  private final PersonJpaRepository repository;

  @Override
  public ErrorAwareResult<Person> save(Person person) {
    try {
      return map(repository.save(map(person)));
    } catch (ObjectOptimisticLockingFailureException e) {
      log.warn("Version conflict detected for person with ID: " + person.getPersonId());
      return ErrorAwareResult.failed(List.of("Wrong Version number"));
    }
  }
}
