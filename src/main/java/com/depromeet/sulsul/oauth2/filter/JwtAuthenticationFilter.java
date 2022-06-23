package com.depromeet.sulsul.oauth2.filter;

import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.oauth2.provider.JwtTokenProvider;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;
  private final MemberRepository memberRepository;

  @Value("${client.url}")
  private String urlOfEnv;

  @Value("${authentication.cookie.accessTokenCookieName}")
  private String accessTokenCookieName;

  @Value("${authentication.cookie.refreshTokenCookieName}")
  private String refreshTokenCookieName;


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    response.setHeader("Access-Control-Allow-Origin", urlOfEnv);
    response.setHeader("Access-Control-Allow-Methods", "*");
    response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Expose-Headers", "Set-Cookie, Cookie");

    if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
      response.setStatus(HttpStatus.OK.value());
      return;
    }

    String jwtToken = jwtTokenProvider.getJwtToken(request);

    if (jwtTokenProvider.validateToken(jwtToken)) {
      UsernamePasswordAuthenticationToken authentication = jwtTokenProvider.getAuthentication(jwtToken);
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }
}
