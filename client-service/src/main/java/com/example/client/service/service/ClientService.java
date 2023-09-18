package com.example.client.service.service;

import com.example.client.service.service.dto.ClientDto;

import java.util.List;

public interface ClientService {

    List<ClientDto> findAll();
    ClientDto save(ClientDto clientDto);
    ClientDto findById(Long id);
    ClientDto update(ClientDto clientDto);

    boolean isExistClient(Long id);

}
