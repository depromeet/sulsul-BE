package com.depromeet.sulsul.domain.token.dto;

import java.io.Serializable;

public class TokenDto implements Serializable {

  private final String accessToken;
  private final String refreshToken;

  private TokenDto(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

  public static TokenDto of(String accessToken, String refreshToken) {
    return new TokenDto(accessToken, refreshToken);
  }

  public String getAccessToken() {
    return accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }
}
