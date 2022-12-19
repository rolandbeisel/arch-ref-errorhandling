package com.beisel.architecture.reference.errorhandling.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class Person {

  @NonNull
  private final PersonId personId;

  private final Integer version;

  @NonNull
  private final String firstname;

  @NonNull
  private final String lastname;

  @NonNull
  private final String department;
}
