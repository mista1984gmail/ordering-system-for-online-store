package com.example.client.service.web.controller;

import com.example.client.service.service.ClientService;
import com.example.client.service.service.convertor.ClientMapper;
import com.example.client.service.service.dto.ClientDto;
import com.example.client.service.web.dto.request.ClientRequest;
import com.example.client.service.web.dto.response.ClientResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/client")
public class ClientController {

  private final ClientService clientService;

  private final ClientMapper clientMapper;

  @SuppressWarnings("checkstyle:WhitespaceAround")

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ClientResponse> findAll() {
    log.info("Find all clients");
    return clientService.findAll().stream()
            .map(clientMapper::dtoToResponse)
            .collect(Collectors.toList());
  }


  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public ClientDto save(
      @Valid @RequestBody ClientRequest clientRequest) {
    log.info("Save client {}", clientRequest);
    return clientService.save(
        clientMapper.requestToDto(clientRequest));
  }


  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ClientResponse findById(
      @PathVariable @NotNull @Positive Long id) {
    log.info("Find client with id: {}", id);
    return clientMapper.dtoToResponse(clientService.findById(id));
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ClientDto update(
      @Valid @RequestBody ClientRequest clientRequest,
      @PathVariable @NotNull @Positive Long id) {
    log.info("Update client: {}", id);
    return clientService.update(
        clientMapper.requestToDto(id, clientRequest));
  }

}
