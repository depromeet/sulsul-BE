package com.depromeet.sulsul.common.error.exception;

public class BusinessException extends RuntimeException {

  private static final long serialVersionUID = -7504178172036424153L;

  public BusinessException(String message) {
    super(message);
  }
}
