package com.besoftilys.benefitclaim.web.rest;

import com.besoftilys.benefitclaim.BenefitClaimApplication;
import com.besoftilys.benefitclaim.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = BenefitClaimApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonResourceIntTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getPerson_returnPersonDetails(){
        //Given

        //when
        ResponseEntity<Person> response = restTemplate.getForEntity("/persons/flen@softilys.tn", Person.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getEmail()).isEqualTo("flen@softilys.tn");
        assertThat(response.getBody().getLastName()).isEqualTo("Ben Foulen");
        assertThat(response.getBody().getFirstName()).isEqualTo("Flen");
    }
    @Test
    public void getAllPersons_returnListOfPersons(){
        ResponseEntity<Person[]> response = restTemplate.getForEntity("/persons", Person[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().length>=0);
        assertThat(Arrays.stream(response.getBody()).findFirst().get().getEmail()).isEqualTo("flen@softilys.tn");
    }

    @Test
    public void addPerson_returnPerson(){
        Person person = new Person("flen@softilys.tn","Flen","Ben Foulen");
        ResponseEntity<Person> response = restTemplate.postForEntity("/persons", person, Person.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getEmail()).isEqualTo("flen@softilys.tn");
        assertThat(response.getBody().getLastName()).isEqualTo("Ben Foulen");
        assertThat(response.getBody().getFirstName()).isEqualTo("Flen");
    }
    @Test
    public void updatePerson_returnPersonWithNewDetails(){
        Person person = new Person("flen@softilys.tn","Foulen","Ben Foulen");
        HttpEntity<Person> personHttpEntity = new HttpEntity<>(person);
        ResponseEntity<Person> response = restTemplate.exchange("/persons", HttpMethod.PUT,
                personHttpEntity, Person.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getEmail()).isEqualTo("flen@softilys.tn");
        assertThat(response.getBody().getFirstName()).isEqualTo("Foulen");
        assertThat(response.getBody().getLastName()).isEqualTo("Ben Foulen");
    }
    @Test
    public void deletePerson_(){
        ResponseEntity<Void> response = restTemplate.exchange("/persons/flen@softilys.tn"
        ,HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
