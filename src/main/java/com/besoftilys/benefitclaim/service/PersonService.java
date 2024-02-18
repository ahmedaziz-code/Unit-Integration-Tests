package com.besoftilys.benefitclaim.service;

import com.besoftilys.benefitclaim.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;



public interface PersonService {
    Person getPersonDetails(String email);

    Person addPerson(Person person);

    List<Person> getAllPersons();

    Person updatePerson(Person person);

    void deletePerson(String s);
}
