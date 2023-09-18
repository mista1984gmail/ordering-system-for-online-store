package com.example.client.service.web.controller;

import com.example.client.service.domain.util.ClientUtil;
import com.example.client.service.service.ClientOrdersService;
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
@RequestMapping("/api/v1/client-orders")
public class ClientOrdersController {
    private final ClientOrdersService clientOrdersService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientUtil findByIdAndStatus(
            @PathVariable @NotNull @Positive Long id,
            @RequestParam(value = "status", required = false) String status) {
        log.info("Find all orders with {} of client with id: {}", status, id);
        return clientOrdersService.getAllOrdersByClientIdAndStatus(id, status);
    }
}
