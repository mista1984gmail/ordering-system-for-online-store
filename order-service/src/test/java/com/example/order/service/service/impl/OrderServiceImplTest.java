package com.example.order.service.service.impl;

import com.example.order.service.domain.entity.Order;
import com.example.order.service.domain.entity.OrderStatus;
import com.example.order.service.domain.repository.OrderRepository;
import com.example.order.service.exception.EntityNotFoundException;
import com.example.order.service.service.OrderService;
import com.example.order.service.service.convertor.OrderMapper;
import com.example.order.service.service.dto.OrderDto;
import com.example.order.util.FakeOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    public static final Long ORDER_ID = 1L;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;

    private OrderService orderService;
    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl(orderRepository, orderMapper);
    }

    @Test
    void save() {
        //given
        OrderDto orderDto = FakeOrder.getFirstOrderDto();
        Order order = FakeOrder.getFirstOrder();

        when(orderMapper.dtoToModel(orderDto)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);
        when(orderMapper.modelToDto(order)).thenReturn(orderDto);

        //when
        var savedOrder = orderService.save(orderDto);

        //then
        Assertions.assertNotNull(savedOrder.getId());
        Assertions.assertNotNull(savedOrder.getClientId());
        Assertions.assertNotNull(savedOrder.getDescription());
        Assertions.assertNotNull(savedOrder.getOrderStatus());


        Assertions.assertEquals(1L, savedOrder.getId());
        Assertions.assertEquals(1L, savedOrder.getClientId());
        Assertions.assertEquals("Apple MacBook Air 2022 г., 512GB SSD M2 8CPU 24GB Midnight", savedOrder.getDescription());
        Assertions.assertEquals(OrderStatus.IN_PROGRESS, savedOrder.getOrderStatus());


        verify(orderRepository, times(1)).save(orderMapper.dtoToModel(orderDto));
    }

    @Test
    void findById() {
        //given
        OrderDto orderDto = FakeOrder.getFirstOrderDto();
        Order order = FakeOrder.getFirstOrder();

        when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(order));
        when(orderMapper.modelToDto(order)).thenReturn(orderDto);

        //when
        var foundOrder = orderService.findById(ORDER_ID);

        //then
        Assertions.assertEquals(orderDto,foundOrder);
        verify(orderRepository, times(1)).findById(ORDER_ID);
    }

    @Test
    void findByIdFail() {
        //given
        given(orderRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));

        // when
        // then
        assertThatThrownBy(() -> orderService.findById(ORDER_ID))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Order with id: 1 not found");

        verify(orderRepository).findById(any());
    }

}