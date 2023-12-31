package com.example.order.service.web.controller;

import com.example.order.service.service.OrderService;
import com.example.order.service.service.convertor.OrderMapper;
import com.example.order.service.service.dto.OrderDto;
import com.example.order.service.service.messaging.producer.Producer;
import com.example.order.service.web.dto.request.OrderRequest;
import com.example.order.service.web.dto.response.OrderResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final Producer producer;
    private final OrderMapper orderMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> findAll(
            @RequestParam(value = "status", required = false) String status
    ) {
        log.info("Find all clients");
        return orderService.findAllOrders(status).stream()
                .map(orderMapper::dtoToResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderDto save(
            @Valid @RequestBody OrderRequest orderRequest) {
        log.info("Save order {}", orderRequest);
        var order = orderService.save(
                orderMapper.requestToDto(orderRequest));

        log.info("Send order {}", orderRequest);
        producer.sendEventOrderSave(order);
        return order;
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse findById(
            @PathVariable @NotNull @Positive Long id) {
        log.info("Find order with id: {}", id);
        return orderMapper.dtoToResponse(orderService.findById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto update(
            @Valid @RequestBody OrderRequest orderRequest,
            @PathVariable @NotNull @Positive Long id) {
        log.info("Update order: {}", id);
        return orderService.update(
                orderMapper.requestToDto(id, orderRequest));
    }
    @PutMapping("/{id}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto updateStatus(
            @PathVariable @NotNull @Positive Long id,
            @PathVariable @NotNull String status) {
        log.info("Update order: {} with new status {}", id, status);
        return orderService.updateStatus(id, status);
    }

    @PutMapping("/update-client-id/{orderId}/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto updateClientId(
            @PathVariable @NotNull @Positive Long orderId,
            @PathVariable @NotNull @Positive Long clientId) {
        log.info("Update order: {} with new client id {}", orderId, clientId);
        return orderService.updateClientId(orderId, clientId);
    }

}
