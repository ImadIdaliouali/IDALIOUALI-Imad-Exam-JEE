package com.example.backend.services.impl;

import com.example.backend.entities.*;
import com.example.backend.enums.CreditStatus;
import com.example.backend.enums.PropertyType;
import lombok.RequiredArgsConstructor;
import com.example.backend.dtos.CreditDTO;
import com.example.backend.dtos.PersonalCreditDTO;
import com.example.backend.dtos.ProfessionalCreditDTO;
import com.example.backend.dtos.RealEstateCreditDTO;
import com.example.backend.entities.*;
import com.example.backend.mappers.CreditMapper;
import com.example.backend.repositories.CreditRepository;
import com.example.backend.repositories.PersonalCreditRepository;
import com.example.backend.repositories.ProfessionalCreditRepository;
import com.example.backend.repositories.RealEstateCreditRepository;
import com.example.backend.services.CreditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;
    private final PersonalCreditRepository personalCreditRepository;
    private final RealEstateCreditRepository realEstateCreditRepository;
    private final ProfessionalCreditRepository professionalCreditRepository;
    private final CreditMapper creditMapper;

    @Override
    public List<CreditDTO> getAllCredits() {
        return creditMapper.toDTOList(creditRepository.findAll());
    }

    @Override
    public CreditDTO getCreditById(Long id) {
        return creditRepository.findById(id)
                .map(creditMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + id));
    }

    @Override
    public List<CreditDTO> getCreditsByClientId(Long clientId) {
        return creditMapper.toDTOList(creditRepository.findByClientId(clientId));
    }

    @Override
    public List<CreditDTO> getCreditsByStatus(CreditStatus status) {
        return creditMapper.toDTOList(creditRepository.findByStatus(status));
    }

    @Override
    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }

    @Override
    public PersonalCreditDTO getPersonalCreditById(Long id) {
        return personalCreditRepository.findById(id)
                .map(creditMapper::toPersonalCreditDTO)
                .orElseThrow(() -> new RuntimeException("Personal credit not found with id: " + id));
    }

    @Override
    public List<PersonalCreditDTO> getAllPersonalCredits() {
        return personalCreditRepository.findAll().stream()
                .map(creditMapper::toPersonalCreditDTO)
                .toList();
    }

    @Override
    public List<PersonalCreditDTO> getPersonalCreditsByPurpose(String purpose) {
        return personalCreditRepository.findByPurpose(purpose).stream()
                .map(creditMapper::toPersonalCreditDTO)
                .toList();
    }

    @Override
    public List<PersonalCreditDTO> getPersonalCreditsByClientId(Long clientId) {
        return personalCreditRepository.findByClientId(clientId).stream()
                .map(creditMapper::toPersonalCreditDTO)
                .toList();
    }

    @Override
    public PersonalCreditDTO savePersonalCredit(PersonalCreditDTO personalCreditDTO) {
        PersonalCredit personalCredit = creditMapper.toPersonalCreditEntity(personalCreditDTO);
        if (personalCredit.getRequestDate() == null) {
            personalCredit.setRequestDate(new Date());
        }
        if (personalCredit.getStatus() == null) {
            personalCredit.setStatus(CreditStatus.PENDING);
        }
        return creditMapper.toPersonalCreditDTO(personalCreditRepository.save(personalCredit));
    }

    @Override
    public PersonalCreditDTO updatePersonalCredit(Long id, PersonalCreditDTO personalCreditDTO) {
        return personalCreditRepository.findById(id)
                .map(existingCredit -> {
                    personalCreditDTO.setId(id);
                    return creditMapper.toPersonalCreditDTO(personalCreditRepository.save(
                            creditMapper.toPersonalCreditEntity(personalCreditDTO)));
                })
                .orElseThrow(() -> new RuntimeException("Personal credit not found with id: " + id));
    }

    @Override
    public RealEstateCreditDTO getRealEstateCreditById(Long id) {
        return realEstateCreditRepository.findById(id)
                .map(creditMapper::toRealEstateCreditDTO)
                .orElseThrow(() -> new RuntimeException("Real estate credit not found with id: " + id));
    }

    @Override
    public List<RealEstateCreditDTO> getAllRealEstateCredits() {
        return realEstateCreditRepository.findAll().stream()
                .map(creditMapper::toRealEstateCreditDTO)
                .toList();
    }

    @Override
    public List<RealEstateCreditDTO> getRealEstateCreditsByPropertyType(String propertyTypeStr) {
        PropertyType propertyType = PropertyType.valueOf(propertyTypeStr);
        return realEstateCreditRepository.findByPropertyType(propertyType).stream()
                .map(creditMapper::toRealEstateCreditDTO)
                .toList();
    }

    @Override
    public List<RealEstateCreditDTO> getRealEstateCreditsByClientId(Long clientId) {
        return realEstateCreditRepository.findByClientId(clientId).stream()
                .map(creditMapper::toRealEstateCreditDTO)
                .toList();
    }

    @Override
    public RealEstateCreditDTO saveRealEstateCredit(RealEstateCreditDTO realEstateCreditDTO) {
        RealEstateCredit realEstateCredit = creditMapper.toRealEstateCreditEntity(realEstateCreditDTO);
        if (realEstateCredit.getRequestDate() == null) {
            realEstateCredit.setRequestDate(new Date());
        }
        if (realEstateCredit.getStatus() == null) {
            realEstateCredit.setStatus(CreditStatus.PENDING);
        }
        return creditMapper.toRealEstateCreditDTO(realEstateCreditRepository.save(realEstateCredit));
    }

    @Override
    public RealEstateCreditDTO updateRealEstateCredit(Long id, RealEstateCreditDTO realEstateCreditDTO) {
        return realEstateCreditRepository.findById(id)
                .map(existingCredit -> {
                    realEstateCreditDTO.setId(id);
                    return creditMapper.toRealEstateCreditDTO(realEstateCreditRepository.save(
                            creditMapper.toRealEstateCreditEntity(realEstateCreditDTO)));
                })
                .orElseThrow(() -> new RuntimeException("Real estate credit not found with id: " + id));
    }

    @Override
    public ProfessionalCreditDTO getProfessionalCreditById(Long id) {
        return professionalCreditRepository.findById(id)
                .map(creditMapper::toProfessionalCreditDTO)
                .orElseThrow(() -> new RuntimeException("Professional credit not found with id: " + id));
    }

    @Override
    public List<ProfessionalCreditDTO> getAllProfessionalCredits() {
        return professionalCreditRepository.findAll().stream()
                .map(creditMapper::toProfessionalCreditDTO)
                .toList();
    }

    @Override
    public List<ProfessionalCreditDTO> getProfessionalCreditsByCompanyName(String companyName) {
        return professionalCreditRepository.findByCompanyName(companyName).stream()
                .map(creditMapper::toProfessionalCreditDTO)
                .toList();
    }

    @Override
    public List<ProfessionalCreditDTO> getProfessionalCreditsByPurpose(String purpose) {
        return professionalCreditRepository.findByPurpose(purpose).stream()
                .map(creditMapper::toProfessionalCreditDTO)
                .toList();
    }

    @Override
    public List<ProfessionalCreditDTO> getProfessionalCreditsByClientId(Long clientId) {
        return professionalCreditRepository.findByClientId(clientId).stream()
                .map(creditMapper::toProfessionalCreditDTO)
                .toList();
    }

    @Override
    public ProfessionalCreditDTO saveProfessionalCredit(ProfessionalCreditDTO professionalCreditDTO) {
        ProfessionalCredit professionalCredit = creditMapper.toProfessionalCreditEntity(professionalCreditDTO);
        if (professionalCredit.getRequestDate() == null) {
            professionalCredit.setRequestDate(new Date());
        }
        if (professionalCredit.getStatus() == null) {
            professionalCredit.setStatus(CreditStatus.PENDING);
        }
        return creditMapper.toProfessionalCreditDTO(professionalCreditRepository.save(professionalCredit));
    }

    @Override
    public ProfessionalCreditDTO updateProfessionalCredit(Long id, ProfessionalCreditDTO professionalCreditDTO) {
        return professionalCreditRepository.findById(id)
                .map(existingCredit -> {
                    professionalCreditDTO.setId(id);
                    return creditMapper.toProfessionalCreditDTO(professionalCreditRepository.save(
                            creditMapper.toProfessionalCreditEntity(professionalCreditDTO)));
                })
                .orElseThrow(() -> new RuntimeException("Professional credit not found with id: " + id));
    }

    @Override
    public CreditDTO updateCreditStatus(Long id, CreditStatus status) {
        return creditRepository.findById(id)
                .map(credit -> {
                    credit.setStatus(status);
                    if (status == CreditStatus.ACCEPTED) {
                        credit.setAcceptanceDate(new Date());
                    }
                    return creditMapper.toDTO(creditRepository.save(credit));
                })
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + id));
    }

    @Override
    public CreditDTO acceptCredit(Long id) {
        return updateCreditStatus(id, CreditStatus.ACCEPTED);
    }

    @Override
    public CreditDTO rejectCredit(Long id) {
        return updateCreditStatus(id, CreditStatus.REJECTED);
    }
}
