package com.beisel.architecture.reference.errorhandling.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;

public class GenericCommandHandlerResult<T> {

  @Setter
  private T payload;

  @Getter
  @Setter
  private List<String> errors = new ArrayList<>();

  public boolean hasErrors() {
    return errors.size() > 0;
  }

  public Optional<T> getPayload() {
    return Optional.ofNullable(payload);
  }

  public static <E> GenericCommandHandlerResult<E> successful(E payload) {
    GenericCommandHandlerResult<E> mappingResult = new GenericCommandHandlerResult<>();
    mappingResult.setPayload(payload);

    return mappingResult;
  }

  public static <E> GenericCommandHandlerResult<E> failed(List<String> errors) {
    GenericCommandHandlerResult<E> mappingResult = new GenericCommandHandlerResult<>();
    mappingResult.setErrors(errors);

    return mappingResult;
  }
}
