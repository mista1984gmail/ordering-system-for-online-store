package com.example.order.service.service.impl;

import com.example.order.service.domain.entity.Order;
import com.example.order.service.domain.entity.OrderStatus;
import com.example.order.service.domain.repository.OrderRepository;
import com.example.order.service.exception.EntityNotFoundException;
import com.example.order.service.service.OrderService;
import com.example.order.service.service.convertor.OrderMapper;
import com.example.order.service.service.dto.OrderDto;
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

    @Override
    @Transactional
    public OrderDto updateStatus(Long id, String status) {
        log.debug("Update order with id : {} new status {}", id, status);
        var foundType = getById(id);
        foundType.setOrderStatus(OrderStatus.valueOf(status));
        return orderMapper.modelToDto(orderRepository.save(foundType));
    }

    @Override
    public List<OrderDto> findAllOrders(String status) {
        log.debug("Find all orders");
        if(status==null){
            return orderMapper.toListDto(orderRepository.findAll());
        }
        else {
           return  orderMapper.toListDto(orderRepository.findByOrderStatus(OrderStatus.valueOf(status)));
        }
    }

    @Override
    @Transactional
    public OrderDto updateClientId(Long orderId, Long clientId) {
        log.debug("Update order with id : {} new client id {}", orderId, clientId);
        var foundType = getById(orderId);
        foundType.setClientId(clientId);
        return orderMapper.modelToDto(orderRepository.save(foundType));
    }

    private Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Order with id: " + id + " not found"));
    }
}
