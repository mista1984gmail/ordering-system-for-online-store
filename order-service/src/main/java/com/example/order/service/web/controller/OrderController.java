package com.example.order.service.web.controller;

import com.example.order.service.service.OrderService;
import com.example.order.service.service.convertor.OrderMapper;
import com.example.order.service.service.dto.OrderDto;
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

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderDto save(
            @Valid @RequestBody OrderRequest orderRequest) {
        log.info("Save order {}", orderRequest);
        return orderService.save(
                orderMapper.requestToDto(orderRequest));
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
            @PathVariable @NotNull @Positive Integer id) {
        log.info("Update order: {}", id);
        return orderService.update(
                orderMapper.requestToDto(id, orderRequest));
    }

}
