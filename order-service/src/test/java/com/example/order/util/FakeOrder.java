package com.example.order.util;

import com.example.order.service.domain.entity.Order;
import com.example.order.service.domain.entity.OrderStatus;
import com.example.order.service.service.dto.OrderDto;
import com.example.order.service.web.dto.request.OrderRequest;
import com.example.order.service.web.dto.response.OrderResponse;

public class FakeOrder {
    public static Order getFirstOrder(){
        return new Order(
                1L,
                1L,
                "Apple MacBook Air 2022 г., 512GB SSD M2 8CPU 24GB Midnight",
                OrderStatus.IN_PROGRESS);
    }

    public static OrderDto getFirstOrderDto(){
        return new OrderDto(
                1L,
                1L,
                "Apple MacBook Air 2022 г., 512GB SSD M2 8CPU 24GB Midnight",
                OrderStatus.IN_PROGRESS);
    }
    public static OrderResponse getFirstOrderResponse(){
        return new OrderResponse(
                1L,
                1L,
                "Apple MacBook Air 2022 г., 512GB SSD M2 8CPU 24GB Midnight",
                OrderStatus.IN_PROGRESS);
    }
    public static OrderRequest getFirstOrderRequest(){
        return new OrderRequest(
                1L,
                "Apple MacBook Air 2022 г., 512GB SSD M2 8CPU 24GB Midnight",
                OrderStatus.IN_PROGRESS);
    }
}
