package com.depromeet.sulsul.common.response.dto;

import org.springframework.http.HttpStatus;

public class ErrorResponseDto {

  private final String message;
  private final int status;

  public static ErrorResponseDto of(Throwable throwable, HttpStatus status) {
    return new ErrorResponseDto(throwable, status);
  }

  private ErrorResponseDto(Throwable throwable, HttpStatus status) {
    this(throwable.getMessage(), status);
  }

  public static ErrorResponseDto of(String errorMessage, HttpStatus status) {
    return new ErrorResponseDto(errorMessage, status);
  }

  private ErrorResponseDto(String errorMessage, HttpStatus status) {
    this.message = errorMessage;
    this.status = status.value();
  }

  public static ErrorResponseDto of(String errorMessage, int errorCode) {
    return new ErrorResponseDto(errorMessage, errorCode);
  }

  private ErrorResponseDto(String errorMessage, int errorCode) {
    this.message = errorMessage;
    this.status = errorCode;
  }

  public String getMessage() {
    return message;
  }

  public int getStatus() {
    return status;
  }
}
