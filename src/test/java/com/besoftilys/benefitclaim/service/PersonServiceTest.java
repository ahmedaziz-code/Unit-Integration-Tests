package com.besoftilys.benefitclaim.service;

import com.besoftilys.benefitclaim.model.Person;
import com.besoftilys.benefitclaim.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

//we don't need to load all the context, so we use mockito test
//@WebMvcTest({PersonService.class, PersonRepository.class})
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @InjectMocks
    PersonServiceImp personService;

    //@MockBean
    @Mock
    private PersonRepository personRepository;

    List<Person> persons = Arrays.asList(
            new Person("flen@softilys.tn","Flen","Ben Foulen"),
            new Person("flen2@softilys.tn","Flen2","Ben Foulen2")
    );
    public static String asJsonString(final Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void getPerson_returnPersonDetails() throws Exception{
        when(personRepository.findById("flen@softilys.tn"))
                .thenReturn(Optional.of(new Person("flen@softilys.tn","Flen","Ben Foulen")));
        assertThat(personService.getPersonDetails("flen@softilys.tn"))
                .isEqualTo(personRepository.findById("flen@softilys.tn").get());
    }
    @Test
    void getAllPerson_returnListOfPersons() throws Exception{
        when(personRepository.findAll())
                .thenReturn(persons);
        assertThat(personService.getAllPersons()).isEqualTo(persons);
    }
    @Test
    void addPerson_returnPerson() throws Exception{
        Person person = new Person("flen@softilys.tn","Flen","Ben Foulen");
        when(personRepository.save(any(Person.class)))
                .thenReturn(new Person("flen@softilys.tn","Flen","Ben Foulen"));
        assertThat(personService.addPerson(person).getEmail()).isEqualTo("flen@softilys.tn");
    }
    @Test
    void updatePerson_returnPersonWithNewDetails() throws Exception{
        Person person = new Person("flen@softilys.tn","Foulen","Ben Foulen");
        when(personRepository.findById(person.getEmail()))
                .thenReturn(Optional.of(person));
        assertThat(personService.updatePerson(person).getFirstName()).isEqualTo("Foulen");
    }
    @Test
    void deletePerson_() throws Exception{
        doNothing().when(personRepository).deleteById(anyString());
    }
}
