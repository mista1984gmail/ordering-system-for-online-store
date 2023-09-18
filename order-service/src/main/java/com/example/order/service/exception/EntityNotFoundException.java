package com.example.order.service.exception;

public class EntityNotFoundException extends OrderApplicationException {
  public EntityNotFoundException(String message) {
    super(message);
  }
}
