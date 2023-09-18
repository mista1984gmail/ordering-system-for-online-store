package com.example.client.service.service.impl;

import com.example.client.service.domain.entity.ClientOrders;
import com.example.client.service.domain.repository.ClientOrdersRepository;
import com.example.client.service.service.ClientOrdersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ClientOrdersServiceImpl implements ClientOrdersService {
    private final ClientOrdersRepository clientOrdersRepository;


    @Override
    public void save(ClientOrders clientOrders) {
        clientOrdersRepository.save(clientOrders);
    }
}
