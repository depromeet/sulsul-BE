package com.depromeet.sulsul.common.error.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  MEMBER_NOT_FOUND("일치하는 회원정보가 없습니다.");

  private final String message;
}
