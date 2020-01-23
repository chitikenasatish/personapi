package com.accela.exerciese.personapi.services.repository;

import com.accela.exerciese.personapi.services.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {}