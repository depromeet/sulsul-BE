package com.depromeet.sulsul.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

  @Value("${client.url}")
  private String urlOfEnv;

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

  public void addAccessTokenResponseCookie(HttpServletResponse response, String token) {
    response.setHeader("Set-Cookie", createAccessTokenResponseCookie(token).toString());
  }

  public void addRefreshTokenCookie(HttpServletResponse response, String token) {
    response.addCookie(createRefreshTokenCookie(token));
  }

  private Cookie createAccessTokenCookie(String token) {
    Cookie cookie = new Cookie(accessTokenCookieName, token);
    setPropertyOfCookie(cookie, accessTokenExpirationSecond / 1000);
    return cookie;
  }

  private ResponseCookie createAccessTokenResponseCookie(String token) {
    ResponseCookie cookie = ResponseCookie.from("accessToken", token).path("/").secure(true)
        .sameSite("None").httpOnly(true).domain(urlOfEnv).maxAge(accessTokenExpirationSecond / 1000)
        .build();

    return cookie;
  }

  private Cookie createRefreshTokenCookie(String token) {

    Cookie cookie = new Cookie(refreshTokenCookieName, token);
    setPropertyOfCookie(cookie, refreshTokenExpirationSecond / 1000);

    return cookie;
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