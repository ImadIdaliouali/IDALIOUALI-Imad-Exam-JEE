package com.example.backend.services.impl;

import lombok.RequiredArgsConstructor;
import com.example.backend.dtos.ClientDTO;
import com.example.backend.entities.Client;
import com.example.backend.exceptions.ResourceNotFoundException;
import com.example.backend.mappers.ClientMapper;
import com.example.backend.repositories.ClientRepository;
import com.example.backend.services.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDTO> getAllClients() {
        return clientMapper.toDTOList(clientRepository.findAll());
    }

    @Override
    public ClientDTO getClientById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
    }

    @Override
    public ClientDTO getClientByEmail(String email) {
        return clientRepository.findByEmail(email)
                .map(clientMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "email", email));
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        return clientRepository.findById(id)
                .map(existingClient -> {
                    clientDTO.setId(id);
                    return clientMapper.toDTO(clientRepository.save(clientMapper.toEntity(clientDTO)));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
        clientRepository.deleteById(id);
    }
}
