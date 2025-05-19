package com.example.backend.mappers;

import com.example.backend.dtos.ClientDTO;
import com.example.backend.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    
    ClientDTO toDTO(Client client);
    Client toEntity(ClientDTO clientDTO);
    List<ClientDTO> toDTOList(List<Client> clients);
}
