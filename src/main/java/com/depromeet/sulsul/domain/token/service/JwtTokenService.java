package com.depromeet.sulsul.domain.token.service;

import com.depromeet.sulsul.common.error.exception.custom.AuthenticationEntryPointException;
import com.depromeet.sulsul.common.error.exception.custom.MemberNotFoundException;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.domain.token.dto.TokenDto;
import com.depromeet.sulsul.domain.token.entity.Token;
import com.depromeet.sulsul.domain.token.repository.JwtTokenRepository;
import com.depromeet.sulsul.oauth2.provider.JwtTokenProvider;
import com.depromeet.sulsul.util.CookieUtil;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

  private final JwtTokenProvider jwtTokenProvider;
  private final JwtTokenRepository jwtTokenRepository;
  private final MemberRepository memberRepository;
  private final CookieUtil cookieUtil;

  public void saveRefreshToken(String refreshToken, Long memberId) {
    jwtTokenRepository.save(Token.of(refreshToken, memberId));
  }

  public TokenDto publishAccessToken(HttpServletResponse response, String refreshToken) {
    boolean isTokenValid = jwtTokenProvider.validateToken(refreshToken);
    if (!isTokenValid) {
      throw new AuthenticationEntryPointException();
    }

    Claims claims = jwtTokenProvider.getAllClaimsFromToken(refreshToken);

    Long memberId = Long.parseLong(claims.getSubject());
    Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

    String newAccessToken = jwtTokenProvider.createAccessToken(member);
    String newRefreshToken = jwtTokenProvider.createRefreshToken(member);

    cookieUtil.addAccessTokenResponseCookie(response, newAccessToken);
    cookieUtil.addRefreshTokenResponseCookie(response, newRefreshToken);

    return TokenDto.of(newAccessToken, newRefreshToken);
  }
}
