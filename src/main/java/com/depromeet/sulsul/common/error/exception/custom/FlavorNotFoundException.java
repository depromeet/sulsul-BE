package com.depromeet.sulsul.common.error.exception.custom;


import static com.depromeet.sulsul.common.error.dto.ErrorType.*;

import com.depromeet.sulsul.common.error.dto.ErrorType;

public class FlavorNotFoundException extends RuntimeException{

  public FlavorNotFoundException() {
    super(FLAVOR_NOT_FOUND.getMessage());
  }
}
