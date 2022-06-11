package com.depromeet.sulsul.common.error.exception.custom;

import static com.depromeet.sulsul.common.error.dto.ErrorType.REQUEST_SIZE_EXCEED;

import com.depromeet.sulsul.common.error.exception.BusinessException;

public class RequestSizeExceedException extends BusinessException {

  public RequestSizeExceedException() {
    super(REQUEST_SIZE_EXCEED.getMessage());
  }
}
