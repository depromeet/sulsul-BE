package com.depromeet.sulsul.common.error.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

  BEER_NOT_FOUND("일치하는 맥주정보가 없습니다."),
  MEMBER_NOT_FOUND("일치하는 회원정보가 없습니다."),
  FLAVOR_NOT_FOUND("일치하는 맛 정보가 없습니다."),
  RECORD_NOT_FOUND("일치하는 기록 정보가 없습니다."),
  MEMBER_LEVEL_NOT_FOUND("일치하는 멤버 레벨 정보가 없습니다."),
  UNEXPECTED_ERROR("예상치 못한 에러가 발생했습니다.");

  private final String message;
}
