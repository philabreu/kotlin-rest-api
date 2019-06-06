package com.ingresse.ingresse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingresse.ingresse.model.Pessoa;

@Repository
public interface PersonRepository extends JpaRepository<Pessoa, Long> {

}
