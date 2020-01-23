package com.accela.exerciese.personapi.services.repository;

import com.accela.exerciese.personapi.services.model.Address;
import com.accela.exerciese.personapi.services.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Integer> {

    Page<Address> findByPersonId(int person_id, Pageable pageable);
    Optional<Address> findByIdAndPersonId(int address_id, int person_id);
}


