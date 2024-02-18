package com.besoftilys.benefitclaim.service;

import com.besoftilys.benefitclaim.model.Person;
import com.besoftilys.benefitclaim.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImp implements PersonService{
    @Autowired
    PersonRepository personRepository;
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Person getPersonDetails(String email) {
        return personRepository.findById(email).get();
    }

    @Override
    public Person addPerson(Person person)  {
        return personRepository.save(person);
    }


    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person updatePerson(Person person) {
        personRepository.updatePersonInfo(person.getEmail(), person.getFirstName(), person.getLastName());
        return personRepository.findById(person.getEmail()).get();
    }

    @Override
    public void deletePerson(String s) {

    }
}
