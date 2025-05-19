package com.example.backend.services;

import com.example.backend.dtos.CreditDTO;
import com.example.backend.dtos.PersonalCreditDTO;
import com.example.backend.dtos.ProfessionalCreditDTO;
import com.example.backend.dtos.RealEstateCreditDTO;
import com.example.backend.enums.CreditStatus;

import java.util.List;

public interface CreditService {
    // Generic credit operations
    List<CreditDTO> getAllCredits();
    CreditDTO getCreditById(Long id);
    List<CreditDTO> getCreditsByClientId(Long clientId);
    List<CreditDTO> getCreditsByStatus(CreditStatus status);
    void deleteCredit(Long id);
    
    // Personal credit operations
    PersonalCreditDTO getPersonalCreditById(Long id);
    List<PersonalCreditDTO> getAllPersonalCredits();
    List<PersonalCreditDTO> getPersonalCreditsByPurpose(String purpose);
    List<PersonalCreditDTO> getPersonalCreditsByClientId(Long clientId);
    PersonalCreditDTO savePersonalCredit(PersonalCreditDTO personalCreditDTO);
    PersonalCreditDTO updatePersonalCredit(Long id, PersonalCreditDTO personalCreditDTO);
    
    // Real estate credit operations
    RealEstateCreditDTO getRealEstateCreditById(Long id);
    List<RealEstateCreditDTO> getAllRealEstateCredits();
    List<RealEstateCreditDTO> getRealEstateCreditsByPropertyType(String propertyType);
    List<RealEstateCreditDTO> getRealEstateCreditsByClientId(Long clientId);
    RealEstateCreditDTO saveRealEstateCredit(RealEstateCreditDTO realEstateCreditDTO);
    RealEstateCreditDTO updateRealEstateCredit(Long id, RealEstateCreditDTO realEstateCreditDTO);
    
    // Professional credit operations
    ProfessionalCreditDTO getProfessionalCreditById(Long id);
    List<ProfessionalCreditDTO> getAllProfessionalCredits();
    List<ProfessionalCreditDTO> getProfessionalCreditsByCompanyName(String companyName);
    List<ProfessionalCreditDTO> getProfessionalCreditsByPurpose(String purpose);
    List<ProfessionalCreditDTO> getProfessionalCreditsByClientId(Long clientId);
    ProfessionalCreditDTO saveProfessionalCredit(ProfessionalCreditDTO professionalCreditDTO);
    ProfessionalCreditDTO updateProfessionalCredit(Long id, ProfessionalCreditDTO professionalCreditDTO);
    
    // Credit status management
    CreditDTO updateCreditStatus(Long id, CreditStatus status);
    CreditDTO acceptCredit(Long id);
    CreditDTO rejectCredit(Long id);
}
