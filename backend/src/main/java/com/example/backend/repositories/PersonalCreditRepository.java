package com.example.backend.repositories;

import com.example.backend.entities.PersonalCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalCreditRepository extends JpaRepository<PersonalCredit, Long> {
    List<PersonalCredit> findByPurpose(String purpose);
    List<PersonalCredit> findByClientId(Long clientId);
}
