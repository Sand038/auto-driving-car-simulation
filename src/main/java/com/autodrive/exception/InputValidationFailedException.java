package com.autodrive.exception;

public class InputValidationFailedException extends RuntimeException {

  public InputValidationFailedException(String s) {
    super(s);
  }
}
