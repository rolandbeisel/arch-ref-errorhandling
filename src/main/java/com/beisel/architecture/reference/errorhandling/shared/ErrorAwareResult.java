package com.beisel.architecture.reference.errorhandling.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;

public class ErrorAwareResult<T> {

  @Setter
  private T payload;

  @Getter
  @Setter
  private List<String> errors = new ArrayList<>();

  public boolean hasErrors() {
    return errors.size() > 0 || getPayload().isEmpty();
  }

  public Optional<T> getPayload() {
    return Optional.ofNullable(payload);
  }

  public static <E> ErrorAwareResult<E> successful(E payload) {
    ErrorAwareResult<E> result = new ErrorAwareResult<>();
    result.setPayload(payload);

    return result;
  }

  public static <E> ErrorAwareResult<E> failed(List<String> errors) {
    ErrorAwareResult<E> result = new ErrorAwareResult<>();
    result.setErrors(errors);

    return result;
  }
}
