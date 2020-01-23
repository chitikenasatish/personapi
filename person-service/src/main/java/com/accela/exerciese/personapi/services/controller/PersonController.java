package com.accela.exerciese.personapi.services.controller;

import java.util.List;

import com.accela.exerciese.personapi.services.model.Person;
import com.accela.exerciese.personapi.services.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/v1")
@EnableAutoConfiguration
@Slf4j
public class PersonController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	PersonService personService;

	@RequestMapping(value = "/persons", method = RequestMethod.GET,
			produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE }, consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
	private List<Person> getAllPersons() {
		return personService.getAllPersons();
	}

	@RequestMapping(value = "/persons/{id}", method = RequestMethod.GET,
			produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE }, consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
	private Person getPerson(@PathVariable("id") int id) {
		return personService.getPersonById(id);
	}


	@DeleteMapping("/persons/{id}")
	private void deletePerson(@PathVariable("id") int id) {
		personService.delete(id);
	}

	@RequestMapping(value = "/persons", method = RequestMethod.POST,
			produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE }, consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
	private ResponseEntity<?> savePerson(@RequestBody Person person) {
		personService.saveOrUpdate(person);
		return new ResponseEntity<Integer>(person.getId(), HttpStatus.OK);
	}
	@RequestMapping(value = "/persons/{id}", method = RequestMethod.PUT,
			produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE }, consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
	private  ResponseEntity<?> savePerson(@PathVariable("id") int id , @RequestBody Person person) {
		person.setId(id);
		personService.saveOrUpdate(person);
		return new ResponseEntity<Integer>(person.getId(), HttpStatus.OK);
	}

	@RequestMapping(value="/person/size",method = RequestMethod.GET)
	public  ResponseEntity<?> getPersonCount() {
		return new ResponseEntity<Integer>( personService.getAllPersons().size(), HttpStatus.OK);
	}

}
