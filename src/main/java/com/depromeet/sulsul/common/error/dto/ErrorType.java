package com.depromeet.sulsul.common.error.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

  MEMBER_NOT_FOUND("일치하는 회원정보가 없습니다."),
  UNEXPECTED_ERROR("예상치 못한 에러가 발생했습니다.");

  private final String message;
}
