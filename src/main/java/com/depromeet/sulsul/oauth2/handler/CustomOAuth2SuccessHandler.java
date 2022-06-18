package com.depromeet.sulsul.oauth2.handler;

import com.depromeet.sulsul.common.error.exception.custom.MemberNotFoundException;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.domain.token.service.JwtTokenService;
import com.depromeet.sulsul.oauth2.CustomOAuth2User;
import com.depromeet.sulsul.oauth2.provider.JwtTokenProvider;
import com.depromeet.sulsul.util.CookieUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  @Value("${client.url}")
  private String urlOfEnv;

  private final JwtTokenProvider jwtTokenProvider;
  private final CookieUtil cookieUtil;
  private final JwtTokenService jwtTokenService;
  private final MemberRepository memberRepository;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {

    String accessToken = jwtTokenProvider.createAccessToken(authentication);
    String refreshToken = jwtTokenProvider.createRefreshToken(authentication);

    cookieUtil.addAccessTokenResponseCookie(response, accessToken);
    cookieUtil.addRefreshTokenResponseCookie(response, refreshToken);

    CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

    jwtTokenService.saveRefreshToken(accessToken, oAuth2User.getMemberId());

    Member member = memberRepository.findById(oAuth2User.getMemberId()).orElseThrow(MemberNotFoundException::new);

    String targetUrl = UriComponentsBuilder.fromUriString(
            Strings.isBlank(member.getNickName()) ? urlOfEnv + "/signup" : urlOfEnv)
        .build()
        .toUriString();

    getRedirectStrategy().sendRedirect(request, response, targetUrl);
  }
}