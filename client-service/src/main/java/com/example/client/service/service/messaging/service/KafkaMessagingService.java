package com.example.client.service.service.messaging.service;

import com.example.client.service.domain.entity.ClientOrders;
import com.example.client.service.service.ClientOrdersService;
import com.example.client.service.service.ClientService;
import com.example.client.service.service.dto.ClientDto;
import com.example.client.service.service.messaging.event.OrderSendEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@AllArgsConstructor
public class KafkaMessagingService {
    private static final String topicEventSaveOrder = "${topic.send-order-to-client}";
    private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";
    private final ClientService clientService;
    private final ClientOrdersService clientOrdersService;

    @Transactional
    @KafkaListener(topics = topicEventSaveOrder, groupId = kafkaConsumerGroupId, properties = {"spring.json.value.default.type=com.example.client.service.service.messaging.event.OrderSendEvent"})
    public void eventSaveOrder(OrderSendEvent orderSendEvent) {
        log.info("Message consumed {}", orderSendEvent);
        if (!clientService.isExistClient(orderSendEvent.getClientId())){
            clientService.save(new ClientDto(orderSendEvent.getClientId(), null, null, null, null, null));
        }
            clientOrdersService.save(new ClientOrders(orderSendEvent.getClientId(), orderSendEvent.getOrderId()));
    }
}
