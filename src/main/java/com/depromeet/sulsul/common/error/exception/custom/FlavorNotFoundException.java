package com.depromeet.sulsul.common.error.exception.custom;


import static com.depromeet.sulsul.common.error.dto.ErrorType.FLAVOR_NOT_FOUND;

import com.depromeet.sulsul.common.error.exception.BusinessException;

public class FlavorNotFoundException extends BusinessException {

  public FlavorNotFoundException() {
    super(FLAVOR_NOT_FOUND.getMessage());
  }
}
