package com.beisel.architecture.reference.errorhandling.application.port.in;

import com.beisel.architecture.reference.errorhandling.shared.ErrorAwareResult;
import com.beisel.architecture.reference.errorhandling.domain.model.Person;
import lombok.NonNull;

public interface AddPersonPort {

  ErrorAwareResult<Person> add(AddPersonCommand command);

  record AddPersonCommand(@NonNull String personId,
                          Integer version,
                          @NonNull String firstname,
                          @NonNull String lastname,
                          @NonNull String department) {
  }
}
