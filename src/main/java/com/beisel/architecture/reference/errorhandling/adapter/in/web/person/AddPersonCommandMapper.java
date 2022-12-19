package com.beisel.architecture.reference.errorhandling.adapter.in.web.person;

import com.beisel.architecture.reference.errorhandling.application.port.in.AddPersonPort.AddPersonCommand;
import com.beisel.architecture.reference.errorhandling.shared.ErrorAwareResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.util.List;

public class AddPersonCommandMapper {

  public static ErrorAwareResult<AddPersonCommand> map(String xml) {
    XmlMapper xmlMapper = new XmlMapper();

    AddPersonCommand command;
    try {
      command = xmlMapper.readValue(xml, AddPersonCommand.class);
    } catch (JsonProcessingException e) {
      return ErrorAwareResult.failed(List.of(e.getMessage()));
    }

    return ErrorAwareResult.successful(command);
  }
}
