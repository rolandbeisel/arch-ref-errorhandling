package com.beisel.architecture.reference.errorhandling.adapter.out.persistence.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "person")
public class PersonEntity {

  @Id
  private String personId;

  @Version
  private Integer version;

  @NonNull
  private String firstname;

  @NonNull
  private String lastname;

  @NonNull
  private String department;
}
