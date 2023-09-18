package com.example.client.service.domain.util;

import lombok.Data;

@Data
public class OrderUtil {
    private String description;
    private OrderStatus orderStatus;
}
