package com.depromeet.sulsul.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

  private static final String SET_COOKIE = "Set-Cookie";

  public void addAccessTokenResponseCookie(HttpServletResponse response, String token) {
    if ("dev".equals(domain)) {
      response.addHeader(SET_COOKIE, createDevAccessTokenResponseCookie(token).toString());
    } else {
      response.addHeader(SET_COOKIE, createProdAccessTokenResponseCookie(token).toString());
    }
  }

  public void addRefreshTokenResponseCookie(HttpServletResponse response, String token) {
    if ("dev".equals(domain)) {
      response.addHeader(SET_COOKIE, createDevRefreshTokenResponseCookie(token).toString());
    } else {
      response.addHeader(SET_COOKIE, createProdRefreshTokenResponseCookie(token).toString());
    }
  }

  private ResponseCookie createProdRefreshTokenResponseCookie(String token) {
    return ResponseCookie.from(refreshTokenCookieName, token)
        .path("/")
        .secure(true)
        .sameSite("None")
        .httpOnly(true)
        .domain(domain)
        .maxAge(10)
        .build();
  }

  private ResponseCookie createProdAccessTokenResponseCookie(String token) {
    return ResponseCookie.from(accessTokenCookieName, token)
        .path("/")
        .secure(true)
        .sameSite("None")
        .httpOnly(true)
        .domain(domain)
        .maxAge(10)
        .build();
  }

  private ResponseCookie createDevRefreshTokenResponseCookie(String token) {
    return ResponseCookie.from(refreshTokenCookieName, token)
        .path("/")
        .httpOnly(true)
        .sameSite("")
        .domain(domain)
        .maxAge(10)
        .build();
  }

  private ResponseCookie createDevAccessTokenResponseCookie(String token) {
    return ResponseCookie.from(accessTokenCookieName, token)
        .path("/")
        .secure(false)
        .sameSite("")
        .domain(domain)
        .maxAge(10)
        .build();
  }

  public void deleteAccessTokenCookie(HttpServletResponse response) {
    Cookie cookie = new Cookie(accessTokenCookieName, null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);
  }

  public void deleteRefreshTokenCookie(HttpServletResponse response) {
    Cookie cookie = new Cookie(refreshTokenCookieName, null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);
  }

  public void deleteAllCookies(HttpServletRequest request, HttpServletResponse response) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++) {
        cookies[i].setMaxAge(0);
        response.addCookie(cookies[i]);
      }
    }
  }
}