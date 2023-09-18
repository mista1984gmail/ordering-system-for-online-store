package com.example.client.service.service;

import com.example.client.service.domain.util.ClientUtil;

public interface ClientOrdersService {

    ClientUtil getAllOrdersByClientIdAndStatus(Long id, String status);
}
