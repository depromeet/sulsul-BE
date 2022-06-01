package com.depromeet.sulsul.common.error.exception.custom;

import com.depromeet.sulsul.common.error.exception.BusinessException;

import static com.depromeet.sulsul.common.error.dto.ErrorType.BEER_NOT_FOUND;

public class BeerNotFoundException extends BusinessException {

  public BeerNotFoundException() {
    super(BEER_NOT_FOUND.getMessage());
  }
}
