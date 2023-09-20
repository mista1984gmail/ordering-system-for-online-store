package com.example.order.service.service;

import com.example.order.service.service.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto save(OrderDto orderDto);
    OrderDto findById(Long id);
    OrderDto update(OrderDto orderDto);
    OrderDto updateStatus(Long id, String status);

    List<OrderDto> findAllOrders(String status);

    OrderDto updateClientId(Long orderId, Long clientId);
}
