package com.depromeet.sulsul.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocialType {

  KAKAO("kakao"),
  NAVER("naver");

  private final String resourceServer;
}
