package com.example.order.service.web.dto.request;

import com.example.order.service.domain.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotNull
    private Long clientId;
    @NotNull
    private String description;
    @NotNull
    private OrderStatus orderStatus;

}
