package com.depromeet.sulsul.oauth2.handler;

import com.depromeet.sulsul.oauth2.CustomOAuth2User;
import com.depromeet.sulsul.oauth2.provider.JwtTokenProvider;
import com.depromeet.sulsul.domain.token.service.JwtTokenService;
import com.depromeet.sulsul.util.CookieUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

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
    response.addHeader("accessToken", accessToken);
    response.addHeader("refreshToken", refreshToken);
    CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

    jwtTokenService.saveRefreshToken(accessToken, oAuth2User.getMemberId());

    String targetUrl;
    targetUrl = UriComponentsBuilder.fromUriString("https://beerair.ml")
        .build()
        .toUriString();

    getRedirectStrategy().sendRedirect(request, response, targetUrl);
  }
}
