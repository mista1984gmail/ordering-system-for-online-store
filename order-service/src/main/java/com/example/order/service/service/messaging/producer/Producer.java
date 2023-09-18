package com.example.order.service.service.messaging.producer;

import com.example.order.service.service.dto.OrderDto;
import com.example.order.service.service.messaging.event.OrderSendEvent;
import com.example.order.service.service.messaging.service.KafkaMessagingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    private final KafkaMessagingService kafkaMessagingService;

    public void sendEventOrderSave(OrderDto orderDto) {
        kafkaMessagingService.sendEventOrderSave(new OrderSendEvent(orderDto.getId(), orderDto.getClientId()));
        log.info("Send event order save  {}", orderDto);
    }


}
