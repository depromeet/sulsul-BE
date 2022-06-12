package com.depromeet.sulsul.common.error.exception.custom;

import static com.depromeet.sulsul.common.error.dto.ErrorType.IMAGE_IO_ERROR;

import com.depromeet.sulsul.common.error.exception.BusinessException;

public class MediaFileProcessException extends BusinessException {

  public MediaFileProcessException() {
    super(IMAGE_IO_ERROR.getMessage());
  }
}
