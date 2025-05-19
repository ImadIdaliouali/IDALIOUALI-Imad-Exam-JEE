package com.example.backend.repositories;

import com.example.backend.entities.ProfessionalCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalCreditRepository extends JpaRepository<ProfessionalCredit, Long> {
    List<ProfessionalCredit> findByCompanyName(String companyName);
    List<ProfessionalCredit> findByPurpose(String purpose);
    List<ProfessionalCredit> findByClientId(Long clientId);
}
