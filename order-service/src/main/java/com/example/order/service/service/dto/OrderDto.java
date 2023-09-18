package com.example.order.service.service.dto;

import com.example.order.service.domain.entity.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private String clientId;
    private String description;
    private OrderStatus orderStatus;

}
