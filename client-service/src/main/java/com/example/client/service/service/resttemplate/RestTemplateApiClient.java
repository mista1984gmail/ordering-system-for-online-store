package com.example.client.service.service.resttemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@ConfigurationProperties(value = "resttemplate.order.service", ignoreUnknownFields = false)
public class RestTemplateApiClient {

  @Autowired
  private RestTemplate restTemplate;

  public final String ORDER_UNIT_PATH = "/api/v1/order/update-client-id/";

  private String apiHost;

  public void updateOrderNewClientId(Long orderId, Long clientId) {
    restTemplate.put(apiHost + ORDER_UNIT_PATH + orderId + "/" + clientId, Object.class);
  }


  public void setApiHost(String apiHost) {
    this.apiHost = apiHost;
  }
}
