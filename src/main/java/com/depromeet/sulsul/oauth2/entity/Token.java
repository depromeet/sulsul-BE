package com.depromeet.sulsul.oauth2.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long memberId;

  private String refreshToken;

  @Builder
  public Token(Long memberId, String refreshToken) {
    this.memberId = memberId;
    this.refreshToken = refreshToken;
  }

  public static Token of(String refreshToken, Long memberId) {
    return Token.builder()
        .refreshToken(refreshToken)
        .memberId(memberId)
        .build();
  }
}
