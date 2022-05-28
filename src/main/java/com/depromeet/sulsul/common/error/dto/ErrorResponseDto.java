package com.depromeet.sulsul.common.error.dto;

import org.springframework.http.HttpStatus;

public class ErrorResponseDto {

  private final String message;
  private final int code;

  public static ErrorResponseDto of(Throwable throwable, HttpStatus status) {
    return new ErrorResponseDto(throwable, status);
  }

  private ErrorResponseDto(Throwable throwable, HttpStatus code) {
    this(throwable.getMessage(), code);
  }

  public static ErrorResponseDto of(String errorMessage, HttpStatus status) {
    return new ErrorResponseDto(errorMessage, status);
  }

  private ErrorResponseDto(String errorMessage, HttpStatus code) {
    this.message = errorMessage;
    this.code = code.value();
  }

  public static ErrorResponseDto of(String errorMessage, int errorCode) {
    return new ErrorResponseDto(errorMessage, errorCode);
  }

  private ErrorResponseDto(String errorMessage, int errorCode) {
    this.message = errorMessage;
    this.code = errorCode;
  }

  public String getMessage() {
    return message;
  }

  public int getCode() {
    return code;
  }
}
