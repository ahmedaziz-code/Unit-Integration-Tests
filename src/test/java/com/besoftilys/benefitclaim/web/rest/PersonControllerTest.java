package com.besoftilys.benefitclaim.web.rest;

import com.besoftilys.benefitclaim.model.Person;
import com.besoftilys.benefitclaim.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    public static String asJsonString(final Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getPerson_returnPersonDetails() throws  Exception{
        given(personService.getPersonDetails(anyString())).willReturn(new Person("flen@softilys.tn","Flen","Ben Foulen"));
        mockMvc.perform(MockMvcRequestBuilders.get("/persons/flen@softilys.tn")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("firstName").value("Flen"))
                .andExpect(jsonPath("lastName").value("Ben Foulen"))
                .andExpect(jsonPath("email").value("flen@softilys.tn"));
    }
    @Test
    void getAllPersons_returnListOfPersons() throws Exception{
        List<Person> persons = Arrays.asList(
                new Person("flen@softilys.tn","Flen","Ben Foulen"),
                new Person("flen2@softilys.tn","Flen2","Ben Foulen2")
        );
        given(personService.getAllPersons()).willReturn(persons);
        mockMvc.perform(MockMvcRequestBuilders.get("/persons")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$[0].email").value("flen@softilys.tn"));
    }
    @Test
    void addPerson_returnPerson() throws Exception{
        Person person = new Person("flen@softilys.tn","Flen","Ben Foulen");
        given(personService.addPerson(any(Person.class))).willReturn(new Person("flen@softilys.tn","Flen","Ben Foulen"));
        mockMvc.perform(MockMvcRequestBuilders.post("/persons")
                .content(asJsonString(person))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print())
//                .andReturn();
//        String responseContent = result.getResponse().getContentAsString();
//        System.out.println("Response JSON: " + responseContent);
                .andExpect(jsonPath("email").value("flen@softilys.tn"))
                .andExpect(jsonPath("firstName").value("Flen"))
                .andExpect(jsonPath("lastName").value("Ben Foulen"));

    }
    @Test
    void updatePerson_returnPersonWithNewDetails() throws Exception{
        Person person = new Person("flen@softilys.tn","Foulen","Ben Foulen");
        given(personService.updatePerson(any(Person.class))).willReturn(person);
        mockMvc.perform(MockMvcRequestBuilders.put("/persons")
                        .content(asJsonString(person))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("email").value("flen@softilys.tn"))
                .andExpect(jsonPath("firstName").value("Foulen"))
                .andExpect(jsonPath("lastName").value("Ben Foulen"));
    }
    @Test
    void deletePerson_() throws Exception {
        willDoNothing().given(personService).deletePerson(anyString());
        mockMvc.perform(MockMvcRequestBuilders.delete("/persons/flen@softilys.tn"))
                .andExpect(status().isOk());
    }
}
