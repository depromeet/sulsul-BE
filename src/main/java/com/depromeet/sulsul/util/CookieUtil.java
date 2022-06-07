package com.depromeet.sulsul.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Component
public class CookieUtil {

  @Value("${authentication.cookie.accessTokenCookieName}")
  private String accessTokenCookieName;

  @Value("${authentication.cookie.refreshTokenCookieName}")
  private String refreshTokenCookieName;

  @Value("${authentication.jwt.accessTokenExpirationSecond}")
  private Long accessTokenExpirationSecond;

  @Value("${authentication.jwt.refreshTokenExpirationSecond}")
  private Long refreshTokenExpirationSecond;

  public void addAccessTokenCookie(HttpServletResponse response, String token) {
    response.addCookie(createAccessTokenCookie(token));
  }

  public void addRefreshTokenCookie(HttpServletResponse response, String token) {
    response.addCookie(createRefreshTokenCookie(token));
  }

  private Cookie createAccessTokenCookie(String token) {

    Cookie cookie = new Cookie(accessTokenCookieName, token);
    cookie.setMaxAge((int) (accessTokenExpirationSecond / 1000));
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    cookie.setDomain("localhost:3000");


    return cookie;
  }

  private Cookie createRefreshTokenCookie(String token) {

    Cookie cookie = new Cookie(refreshTokenCookieName, token);
    cookie.setMaxAge((int) (refreshTokenExpirationSecond / 1000));
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    cookie.setDomain("localhost:3000");

    return cookie;
  }

  public void deleteAccessTokenCookie(HttpServletResponse response) {

    Cookie cookie = new Cookie(accessTokenCookieName, null);
    cookie.setMaxAge(0);
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    cookie.setDomain("localhost:3000");

    response.addCookie(cookie);
  }

  public void deleteRefreshTokenCookie(HttpServletResponse response) {

    Cookie cookie = new Cookie(refreshTokenCookieName, null);
    cookie.setMaxAge(0);
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    cookie.setDomain("localhost:3000");
    
    response.addCookie(cookie);
  }
}