package com.depromeet.sulsul.common.error.exception.custom;

import static com.depromeet.sulsul.common.error.dto.ErrorType.NOT_ALLOW_TO_ACCESS;

import com.depromeet.sulsul.common.error.exception.BusinessException;

public class NotAllowAccessException extends BusinessException {

  public NotAllowAccessException() {
    super(NOT_ALLOW_TO_ACCESS.getMessage());
  }
}
