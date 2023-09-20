package com.example.order.service.web.controller;

import com.example.order.service.domain.entity.OrderStatus;
import com.example.order.service.service.OrderService;
import com.example.order.service.service.convertor.OrderMapper;
import com.example.order.service.service.dto.OrderDto;
import com.example.order.service.service.messaging.producer.Producer;
import com.example.order.service.web.dto.request.OrderRequest;
import com.example.order.service.web.dto.response.OrderResponse;
import com.example.order.util.FakeOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    private final String RESOURCE_URL = "/api/v1/order";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @MockBean
    private Producer producer;

    @MockBean
    private OrderMapper orderMapper;

    @Test
    void save() throws Exception {
        OrderDto orderDto = FakeOrder.getFirstOrderDto();
        OrderResponse orderResponse = FakeOrder.getFirstOrderResponse();
        OrderRequest orderRequest = FakeOrder.getFirstOrderRequest();

        when(orderService.save(any(OrderDto.class))).thenReturn(orderDto);
        when(orderMapper.dtoToResponse(orderDto)).thenReturn(orderResponse);
        when(orderMapper.requestToDto(orderRequest)).thenReturn(orderDto);

        mockMvc.perform(MockMvcRequestBuilders.post(RESOURCE_URL)
                        .content(objectMapper.findAndRegisterModules().writeValueAsString(orderResponse))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Apple MacBook Air 2022 г., 512GB SSD M2 8CPU 24GB Midnight"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderStatus").value(OrderStatus.IN_PROGRESS.name()));


        verify(orderService, times(1)).save(
                orderMapper.requestToDto(orderRequest));
    }

}
