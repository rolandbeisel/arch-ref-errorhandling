package com.beisel.architecture.reference.errorhandling.adapter.in.web.validator;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class XsdSchemaValidatorResult {

  @Getter
  @Setter
  private List<String> errors = new ArrayList<>();

  public boolean hasErrors() {
    return errors.size() > 0;
  }

  public void addError(String errorMessage) {
    errors.add(errorMessage);
  }
}
