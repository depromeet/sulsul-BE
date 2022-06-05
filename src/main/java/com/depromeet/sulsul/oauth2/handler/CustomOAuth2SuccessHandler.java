package com.depromeet.sulsul.oauth2.handler;

import com.depromeet.sulsul.oauth2.CustomOAuth2User;
import com.depromeet.sulsul.oauth2.provider.JwtTokenProvider;
import com.depromeet.sulsul.oauth2.service.JwtTokenService;
import com.depromeet.sulsul.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

  private final JwtTokenProvider jwtTokenProvider;
  private final CookieUtil cookieUtil;

  private final JwtTokenService jwtTokenService;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {

    String accessToken = jwtTokenProvider.createAccessToken(authentication);
    String refreshToken = jwtTokenProvider.createRefreshToken(authentication);

    cookieUtil.addAccessTokenCookie(response, accessToken);
    cookieUtil.addRefreshTokenCookie(response, refreshToken);
    CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

    jwtTokenService.saveRefreshToken(accessToken,oAuth2User.getMemberId());
  }
}
