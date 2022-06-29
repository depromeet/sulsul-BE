package com.depromeet.sulsul.oauth2.handler;

import com.depromeet.sulsul.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

  private final CookieUtil cookieUtil;
  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    cookieUtil.deleteRefreshTokenCookie(response);
    cookieUtil.deleteAccessTokenCookie(response);
    cookieUtil.deleteAllCookies(request,response);
  }
}
