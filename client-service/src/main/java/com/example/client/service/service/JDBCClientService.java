package com.example.client.service.service;

import com.example.client.service.service.dto.ClientDto;

public interface JDBCClientService {

    Long save(ClientDto client);
}
