package com.beisel.architecture.reference.errorhandling.adapter.out.persistence;

import com.beisel.architecture.reference.errorhandling.adapter.out.persistence.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

interface PersonJpaRepository extends JpaRepository<PersonEntity, String> {

}
