package com.example.flipkartdaily.exception;

public class InvalidFilterException extends RuntimeException {
  public InvalidFilterException(String message) {
    super(message);
  }
}
