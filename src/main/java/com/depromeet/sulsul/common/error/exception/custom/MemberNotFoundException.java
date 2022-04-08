package com.depromeet.sulsul.common.error.exception.custom;

import com.depromeet.sulsul.common.error.dto.ErrorCode;
import com.depromeet.sulsul.common.error.exception.BusinessException;

public class MemberNotFoundException extends BusinessException {

  public MemberNotFoundException() {
    super(ErrorCode.MEMBER_NOT_FOUND.getMessage());
  }
}
