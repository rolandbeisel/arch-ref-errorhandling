package com.beisel.architecture.reference.errorhandling.domain.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class PersonId {

  private final UUID value;

  @Override
  public String toString() {
    return value.toString();
  }

  public static PersonId fromString(String uuid) {
    return PersonId.of(UUID.fromString(uuid));
  }
}
