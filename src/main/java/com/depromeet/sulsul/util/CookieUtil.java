package com.depromeet.sulsul.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

  @Value("${authentication.cookie.domain}")
  private String domain;

  @Value("${authentication.cookie.accessTokenCookieName}")
  private String accessTokenCookieName;

  @Value("${authentication.cookie.refreshTokenCookieName}")
  private String refreshTokenCookieName;

  @Value("${authentication.jwt.accessTokenExpirationSecond}")
  private Long accessTokenExpirationSecond;

  @Value("${authentication.jwt.refreshTokenExpirationSecond}")
  private Long refreshTokenExpirationSecond;

  public void addAccessTokenResponseCookie(HttpServletResponse response, String token) {
    response.addHeader("Set-Cookie", createAccessTokenResponseCookie(token).toString());
  }

  public void addRefreshTokenResponseCookie(HttpServletResponse response, String token) {
    response.addHeader("Set-Cookie", createRefreshTokenResponseCookie(token).toString());
  }

  private ResponseCookie createRefreshTokenResponseCookie(String token) {
    return ResponseCookie.from(refreshTokenCookieName, token)
        .path("/")
        .secure(true)
        .sameSite("None")
        .httpOnly(true)
        .domain(domain)
        .maxAge(refreshTokenExpirationSecond / 1000)
        .build();
  }

  private ResponseCookie createAccessTokenResponseCookie(String token) {
    return ResponseCookie.from(accessTokenCookieName, token)
        .path("/")
        .secure(true)
        .sameSite("None")
        .httpOnly(true)
        .domain(domain)
        .maxAge(accessTokenExpirationSecond / 1000)
        .build();
  }

  public void deleteAccessTokenCookie(HttpServletResponse response) {

    Cookie cookie = new Cookie(accessTokenCookieName, null);
    setPropertyOfCookie(cookie, 0L);
    response.addCookie(cookie);
  }

  public void deleteRefreshTokenCookie(HttpServletResponse response) {

    Cookie cookie = new Cookie(refreshTokenCookieName, null);
    setPropertyOfCookie(cookie, 0L);
    response.addCookie(cookie);
  }

  private void setPropertyOfCookie(Cookie cookie, long maxAge) {
    cookie.setMaxAge((int) maxAge);
    cookie.setHttpOnly(true);
    cookie.setPath("/");
  }
}