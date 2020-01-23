package com.accela.exerciese.personapi.services.service;

import com.accela.exerciese.personapi.services.exception.ResourceNotFoundException;
import com.accela.exerciese.personapi.services.model.Address;
import com.accela.exerciese.personapi.services.repository.AddressRepository;
import com.accela.exerciese.personapi.services.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PersonRepository personRepository;

    public Address saveOrUpdate(int personId, Address address) throws ResourceNotFoundException {
        return personRepository.findById(personId).map(person -> {
            address.setPerson(person);
            return addressRepository.save(address);
        }).orElseThrow(() -> new ResourceNotFoundException("Person " + personId + " not found"));
    }

    public Page<Address> getAllAddress(int personid, Pageable pageable) {
        return addressRepository.findByPersonId(personid, pageable);
    }

    public Address getAddressById(int id) {
        return addressRepository.findById(id).get();
    }

    public Address updateAddress(int person_id,
                                 int address_id,
                                 @Valid @RequestBody Address address) throws ResourceNotFoundException {
        if (!personRepository.existsById(person_id)) {
            throw new ResourceNotFoundException("Person  " + person_id + " not found");
        }

        return addressRepository.findById(address_id).map(address1 -> {
            address1.setCity(address.getCity());
            address1.setPostalCode(address.getPostalCode());
            address1.setState(address.getState());
            address1.setStreet(address.getStreet());
            return addressRepository.save(address1);
        }).orElseThrow(() -> new ResourceNotFoundException("Address  " + address_id + "not found"));

    }

    public ResponseEntity<?> deleteAddress(int person_id,
                                           int address_id) throws ResourceNotFoundException {
        return addressRepository.findByIdAndPersonId(address_id, person_id).map(address -> {
            addressRepository.delete(address);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + address_id + " and person ID " + person_id));
    }

}
