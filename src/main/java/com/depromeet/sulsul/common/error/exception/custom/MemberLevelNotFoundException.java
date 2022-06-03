package com.depromeet.sulsul.common.error.exception.custom;

import static com.depromeet.sulsul.common.error.dto.ErrorType.MEMBER_LEVEL_NOT_FOUND;
import com.depromeet.sulsul.common.error.exception.BusinessException;

public class MemberLevelNotFoundException extends BusinessException {
  public MemberLevelNotFoundException(){ super(MEMBER_LEVEL_NOT_FOUND.getMessage());}
}
