package com.example.backend.mappers;

import com.example.backend.dtos.CreditDTO;
import com.example.backend.dtos.PersonalCreditDTO;
import com.example.backend.dtos.ProfessionalCreditDTO;
import com.example.backend.dtos.RealEstateCreditDTO;
import com.example.backend.entities.Credit;
import com.example.backend.entities.PersonalCredit;
import com.example.backend.entities.ProfessionalCredit;
import com.example.backend.entities.RealEstateCredit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditMapper {
    CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);
    
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(target = "creditType", ignore = true)
    CreditDTO toDTO(Credit credit);
    
    @Mapping(source = "clientId", target = "client.id")
    Credit toEntity(CreditDTO creditDTO);
    
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(target = "creditType", constant = "PERSONAL")
    PersonalCreditDTO toPersonalCreditDTO(PersonalCredit personalCredit);
    
    @Mapping(source = "clientId", target = "client.id")
    PersonalCredit toPersonalCreditEntity(PersonalCreditDTO personalCreditDTO);
    
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(target = "creditType", constant = "REAL_ESTATE")
    RealEstateCreditDTO toRealEstateCreditDTO(RealEstateCredit realEstateCredit);
    
    @Mapping(source = "clientId", target = "client.id")
    RealEstateCredit toRealEstateCreditEntity(RealEstateCreditDTO realEstateCreditDTO);
    
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(target = "creditType", constant = "PROFESSIONAL")
    ProfessionalCreditDTO toProfessionalCreditDTO(ProfessionalCredit professionalCredit);
    
    @Mapping(source = "clientId", target = "client.id")
    ProfessionalCredit toProfessionalCreditEntity(ProfessionalCreditDTO professionalCreditDTO);
    
    List<CreditDTO> toDTOList(List<Credit> credits);
}
