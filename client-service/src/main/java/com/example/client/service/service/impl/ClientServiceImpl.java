package com.example.client.service.service.impl;

import com.example.client.service.domain.entity.Client;
import com.example.client.service.domain.repository.ClientRepository;
import com.example.client.service.exception.EntityNotFoundException;
import com.example.client.service.service.ClientService;
import com.example.client.service.service.convertor.ClientMapper;
import com.example.client.service.service.dto.ClientDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;

  private final ClientMapper clientMapper;

  @Override
  public List<ClientDto> findAll() {
    log.debug("Find all clients");
    return clientMapper.toListDto(clientRepository.findAll());
  }


  @Override
  @Transactional
  public ClientDto save(ClientDto clientDto) {
    log.debug("Save client: {}", clientDto);
    return clientMapper.modelToDto(clientRepository.save(
        clientMapper.dtoToModel(clientDto)));
  }


  @SuppressWarnings("java:S3655")
  @Override
  @Transactional
  public ClientDto update(ClientDto clientDto) {
    log.debug("Update client: {}", clientDto);
    var foundType = getById(clientDto.getId());
    clientMapper.updateEntityToModel(foundType, clientDto);
    return clientMapper.modelToDto(clientRepository.save(foundType));
  }

  @SuppressWarnings("java:S3655")
  @Override
  public ClientDto findById(Long id) {
    log.debug("Find client with id: {}", id);
    return Optional.of(getById(id))
        .map(clientMapper::modelToDto)
        .get();
  }


  private Client getById(Long id) {
    return clientRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(
            "Client with id: " + id + " not found"));
  }

}
