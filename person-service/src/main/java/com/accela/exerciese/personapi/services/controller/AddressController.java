package com.accela.exerciese.personapi.services.controller;

import com.accela.exerciese.personapi.services.exception.ResourceNotFoundException;
import com.accela.exerciese.personapi.services.model.Address;
import com.accela.exerciese.personapi.services.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/v1")
@EnableAutoConfiguration
@Slf4j
public class AddressController {

    @Autowired
    AddressService addressService;


    @PostMapping("/persons/{person_id}/address")
    private int saveAddress(@PathVariable("person_id") int id, @RequestBody Address address) throws ResourceNotFoundException {
        addressService.saveOrUpdate(id,address);
        return address.getId();
    }

    @GetMapping("/persons/{person_id}/address")
    private Page<Address> getAllPersonAddress(@PathVariable("person_id") int id, Pageable pageable) {
        return addressService.getAllAddress(id,pageable);
    }

    @GetMapping("/persons/{person_id}/address/{address_id}")
    private Address getAllPersonAddress(@PathVariable("person_id") int person_id, @PathVariable("address_id") int address_id) {
        return addressService.getAddressById(address_id);
    }
    @PutMapping("/persons/{person_id}/address/{address_id}")
    public Address updateAddress(@PathVariable (value = "person_id") int person_id,
                                 @PathVariable (value = "address_id") int address_id,
                                 @Valid @RequestBody Address address) throws ResourceNotFoundException {
        return addressService.updateAddress(person_id,address_id,address);
    }
    @DeleteMapping("/posts/{person_id}/comments/{address_id}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "person_id") int person_id,
                                           @PathVariable (value = "address_id") int address_id) throws ResourceNotFoundException {
        return addressService.deleteAddress(person_id,address_id);
    }
}
