package com.example.order.service.web.dto.request;

import com.example.order.service.domain.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private String clientId;
    private String description;
    private OrderStatus orderStatus;

}
