package com.depromeet.sulsul.common.error.exception.custom;

import static com.depromeet.sulsul.common.error.dto.ErrorType.UNAUTHORIZED;

import com.depromeet.sulsul.common.error.exception.BusinessException;

public class UnAuthorizedException extends BusinessException {

  public UnAuthorizedException() {
    super(UNAUTHORIZED.getMessage());
  }
}
