package com.example.order.service.service.impl;

import com.example.order.service.domain.entity.Order;
import com.example.order.service.domain.repository.OrderRepository;
import com.example.order.service.exception.EntityNotFoundException;
import com.example.order.service.service.OrderService;
import com.example.order.service.service.convertor.OrderMapper;
import com.example.order.service.service.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderDto save(OrderDto orderDto) {
        log.debug("Save order: {}", orderDto);
        return orderMapper.modelToDto(orderRepository.save(
                orderMapper.dtoToModel(orderDto)));
    }

    @Override
    public OrderDto findById(Long id) {
        log.debug("Find order with id: {}", id);
        return Optional.of(getById(id))
                .map(orderMapper::modelToDto)
                .get();
    }

    @Override
    @Transactional
    public OrderDto update(OrderDto orderDto) {
        log.debug("Update order: {}", orderDto);
        var foundType = getById(orderDto.getId());
        orderMapper.updateEntityToModel(foundType, orderDto);
        return orderMapper.modelToDto(orderRepository.save(foundType));
    }

    private Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Order with id: " + id + " not found"));
    }
}
