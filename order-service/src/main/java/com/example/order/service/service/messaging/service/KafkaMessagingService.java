package com.example.order.service.service.messaging.service;

import com.example.order.service.service.messaging.event.OrderSendEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMessagingService {

    @Value("${topic.send-order-to-client}")
    private String sendEventOrderSaveTopic;
    private final KafkaTemplate<String , Object> kafkaTemplate;

    public void sendEventOrderSave(OrderSendEvent orderSendEvent) {
       kafkaTemplate.send(sendEventOrderSaveTopic, orderSendEvent);
    }

}
