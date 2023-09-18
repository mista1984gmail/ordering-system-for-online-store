package com.example.order.service.service;

import com.example.order.service.service.dto.OrderDto;

public interface OrderService {

    OrderDto save(OrderDto orderDto);
    OrderDto findById(Long id);
    OrderDto update(OrderDto orderDto);

}
