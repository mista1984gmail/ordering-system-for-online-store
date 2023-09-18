package com.example.order.service.web.dto.response;

import com.example.order.service.domain.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private String clientId;
    private String description;
    private OrderStatus orderStatus;

}
