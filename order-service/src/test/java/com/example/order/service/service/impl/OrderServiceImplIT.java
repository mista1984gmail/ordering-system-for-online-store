package com.example.order.service.service.impl;

import com.example.order.service.domain.entity.Order;
import com.example.order.service.domain.repository.OrderRepository;
import com.example.order.service.service.OrderService;
import com.example.order.service.service.convertor.OrderMapper;
import com.example.order.service.service.dto.OrderDto;
import com.example.order.util.FakeOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(properties = {"spring.main.allow-bean-definition-overriding=true",
        "spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER"})
public class OrderServiceImplIT {

    public static final Long ORDER_ID = 1L;
    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:12")
            .withUsername("username")
            .withPassword("password")
            .withExposedPorts(5432)
            .withReuse(true);

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", postgreSQLContainer::getDriverClassName);
    }
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @Test
    void saveOrder() {
        //given
        OrderDto orderDto = FakeOrder.getFirstOrderDto();
        Order order = FakeOrder.getFirstOrder();

        //when
        orderService.save(orderDto);

        //then
        Order orderFromDB = orderRepository.findById(ORDER_ID).get();
        assertEquals(orderFromDB.getId(), order.getId());
        assertEquals(orderFromDB.getClientId(), order.getClientId());
        assertEquals(orderFromDB.getDescription(), order.getDescription());
        assertEquals(orderFromDB.getOrderStatus(), order.getOrderStatus());

    }

}
