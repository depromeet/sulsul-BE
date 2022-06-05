package com.depromeet.sulsul.common.error.exception.custom;

import com.depromeet.sulsul.common.error.dto.ErrorType;
import com.depromeet.sulsul.common.error.exception.BusinessException;

public class AuthenticationEntryPointException extends BusinessException {
  public AuthenticationEntryPointException() {
    super(ErrorType.REFRESH_TOKEN_EXPIRED.getMessage());
  }
}
