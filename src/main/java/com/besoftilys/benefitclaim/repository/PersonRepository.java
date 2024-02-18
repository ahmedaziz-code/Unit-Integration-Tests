package com.besoftilys.benefitclaim.repository;

import com.besoftilys.benefitclaim.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PersonRepository  extends JpaRepository<Person, String> {
    @Modifying
    @Query("UPDATE Person p SET p.firstName = :firstName, p.lastName = :lastName WHERE p.email = :personId")
    void updatePersonInfo(String personId, String firstName, String lastName);
}
