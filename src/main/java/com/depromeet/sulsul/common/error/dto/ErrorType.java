package com.depromeet.sulsul.common.error.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

  BEER_NOT_FOUND("일치하는 맥주정보가 없습니다."), MEMBER_NOT_FOUND("일치하는 회원정보가 없습니다."), FLAVOR_NOT_FOUND(
      "일치하는 맛 정보가 없습니다."), RECORD_NOT_FOUND("일치하는 기록 정보가 없습니다."), MEMBER_LEVEL_NOT_FOUND(
      "일치하는 멤버 레벨 정보가 없습니다."), UNAUTHORIZED("액세스 토큰이 만료되었거나 유효하지 않습니다."), UNEXPECTED_ERROR(
      "예상치 못한 에러가 발생했습니다."), IMAGE_IO_ERROR("미디어 파일 처리하는 도중 에러가 발생했습니다."), REFRESH_TOKEN_EXPIRED(
      "리프레시 토큰이 만료되었습니다."), NOT_ALLOW_TO_ACCESS("다른 사용자의 정보를 수정할 수 없습니다."), REQUEST_SIZE_EXCEED(
      "요청 파라미터 갯수를 초과할 수 없습니다.");

  private final String message;
}
