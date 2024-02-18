package com.besoftilys.benefitclaim.repository;

import com.besoftilys.benefitclaim.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void getPerson_returnPersonDetails() throws Exception{
        Person person = personRepository.findById("flen@softilys.tn").get();
        assertThat(person.getEmail()).isEqualTo("flen@softilys.tn");
        assertThat(person.getFirstName()).isEqualTo("Flen");
        assertThat(person.getLastName()).isEqualTo("Ben Foulen");
    }
    @Test void getAllPerson_returnListOfPersons() throws Exception{
        List<Person> persons = personRepository.findAll();
        assertThat(persons.size()==2);
        assertThat(persons.get(0).getEmail()).isEqualTo("flen@softilys.tn");
    }
    @Test
    void addPerson_returnPerson() throws Exception{
        Person person = personRepository.save(new Person("flen@softilys.tn","Flen","Ben Foulen"));
        assertThat(person.getEmail()).isEqualTo("flen@softilys.tn");
    }
    @Test
    void updatePerson_returnPersonWithNewDetails() throws Exception{
        Person person = new Person("flen@softilys.tn","Foulen","Ben Foulen");
        personRepository.updatePersonInfo(person.getEmail(),person.getFirstName(), person.getLastName());
        Person updatePerson = personRepository.findById(person.getEmail()).get();
        assertThat(updatePerson.getFirstName()).isEqualTo("Foulen");
    }
}
