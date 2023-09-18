package com.example.order.service.service.convertor;

import com.example.order.service.domain.entity.Order;
import com.example.order.service.service.dto.OrderDto;
import com.example.order.service.web.dto.request.OrderRequest;
import com.example.order.service.web.dto.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface OrderMapper {

    Order dtoToModel(OrderDto orderDto);
    OrderDto modelToDto(Order order);
    List<OrderDto> toListDto(List<Order> orders);
    @Mapping(target = "id", ignore = true)
    void updateEntityToModel(@MappingTarget Order target, OrderDto source);

    @Mapping(target = "id", source = "id")
    OrderDto requestToDto(Long id, OrderRequest orderRequest);

    OrderDto requestToDto(OrderRequest orderRequest);

    OrderResponse dtoToResponse(OrderDto clientDto);
}
