package com.example.client.service.exception;

public class EntityNotFoundException extends ClientApplicationException {
  public EntityNotFoundException(String message) {
    super(message);
  }
}
