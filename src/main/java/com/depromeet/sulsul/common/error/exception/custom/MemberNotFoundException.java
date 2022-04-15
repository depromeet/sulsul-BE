package com.depromeet.sulsul.common.error.exception.custom;

import static com.depromeet.sulsul.common.error.dto.ErrorType.MEMBER_NOT_FOUND;

import com.depromeet.sulsul.common.error.exception.BusinessException;

public class MemberNotFoundException extends BusinessException {

  public MemberNotFoundException() {
    super(MEMBER_NOT_FOUND.getMessage());
  }
}
