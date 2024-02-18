package com.besoftilys.benefitclaim.web.rest;

import com.besoftilys.benefitclaim.model.Person;
import com.besoftilys.benefitclaim.service.PersonService;
import com.besoftilys.benefitclaim.service.PersonServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class PersonController {
    @Autowired
    PersonService personService;
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    @GetMapping ("/persons/{email}")
    public ResponseEntity<Person> getPerson(@PathVariable String email){
        return ResponseEntity.ok(personService.getPersonDetails(email));
    }

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getPersons(){
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @PostMapping("/persons")
    public ResponseEntity<Person> addPerson(@RequestBody Person person)  {
        return ResponseEntity.ok(personService.addPerson(person));
    }

    @PutMapping("/persons")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person){
        return ResponseEntity.ok(personService.updatePerson(person));
    }

    @DeleteMapping("/persons/{email}")
    public void deletePerson(@PathVariable String email){
        personService.deletePerson(email);
    }
}
