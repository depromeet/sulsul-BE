package com.depromeet.sulsul.common.error.exception.custom;

import static com.depromeet.sulsul.common.error.dto.ErrorType.RECORD_NOT_FOUND;

import com.depromeet.sulsul.common.error.exception.BusinessException;

public class RecordNotFoundException extends BusinessException {
  public RecordNotFoundException() {
    super(RECORD_NOT_FOUND.getMessage());
  }

}
