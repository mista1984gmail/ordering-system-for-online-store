package com.example.client.service.service.messaging.service;

import com.example.client.service.domain.entity.Client;
import com.example.client.service.service.ClientService;
import com.example.client.service.service.JDBCClientService;
import com.example.client.service.service.dto.ClientDto;
import com.example.client.service.service.messaging.event.OrderSendEvent;
import com.example.client.service.service.resttemplate.RestTemplateApiClient;
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
    private final JDBCClientService jdbcClientService;
    private final RestTemplateApiClient restTemplateApiClient;

    @Transactional
    @KafkaListener(topics = topicEventSaveOrder, groupId = kafkaConsumerGroupId, properties = {"spring.json.value.default.type=com.example.client.service.service.messaging.event.OrderSendEvent"})
    public void eventSaveOrder(OrderSendEvent orderSendEvent) {
        log.info("Message consumed {}", orderSendEvent);
        if (!clientService.isExistClient(orderSendEvent.getClientId())){
            Long savedClientId = jdbcClientService.save(new ClientDto(orderSendEvent.getClientId(), null, null, null, null, null));
            restTemplateApiClient.updateOrderNewClientId(orderSendEvent.getOrderId(), savedClientId);
        }
    }
}
