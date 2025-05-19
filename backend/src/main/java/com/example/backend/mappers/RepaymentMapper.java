package com.example.backend.mappers;

import com.example.backend.dtos.RepaymentDTO;
import com.example.backend.entities.Repayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RepaymentMapper {
    RepaymentMapper INSTANCE = Mappers.getMapper(RepaymentMapper.class);
    
    @Mapping(source = "credit.id", target = "creditId")
    RepaymentDTO toDTO(Repayment repayment);
    
    @Mapping(source = "creditId", target = "credit.id")
    Repayment toEntity(RepaymentDTO repaymentDTO);
    
    List<RepaymentDTO> toDTOList(List<Repayment> repayments);
}
