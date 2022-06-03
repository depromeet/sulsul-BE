package com.depromeet.sulsul.common.error.exception.custom;

import com.depromeet.sulsul.common.error.exception.BusinessException;

import static com.depromeet.sulsul.common.error.dto.ErrorType.RECORD_NOT_FOUND;
public class RecordNotFoundException extends BusinessException {
  public RecordNotFoundException() {
    super(RECORD_NOT_FOUND.getMessage());
  }

}
