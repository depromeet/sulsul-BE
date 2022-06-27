package com.depromeet.sulsul.oauth2.filter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.oauth2.provider.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
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

  @Value("${authentication.jwt.secretKey}")
  private String secretKey;

  private ObjectMapper objectMapper = new ObjectMapper();


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    response.setHeader("Access-Control-Allow-Origin", urlOfEnv);
    response.setHeader("Access-Control-Allow-Methods", "*");
    response.setHeader("Access-Control-Allow-Headers",
        "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Expose-Headers", "Set-Cookie, Cookie");

    if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
      response.setStatus(OK.value());
      return;
    }

    String jwtToken = jwtTokenProvider.getJwtToken(request);

    try {
      Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(jwtToken);
    } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
      setResponseJsonType(response);
      processWithErrorResponseDto("[ERROR] jwt가 없거나 잘못 되었습니다.", BAD_REQUEST, response);
      return;
    } catch (ExpiredJwtException e) {
      setResponseJsonType(response);
      processWithErrorResponseDto("[ERROR] jwt가 만료되었습니다.", UNAUTHORIZED, response);
      return;
    }

    UsernamePasswordAuthenticationToken authentication = jwtTokenProvider.getAuthentication(
        jwtToken);
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    filterChain.doFilter(request, response);
  }

  private void processWithErrorResponseDto(String errorMessage, HttpStatus badRequest,
      HttpServletResponse response) throws IOException {
    String responseBodyWithJson = objectMapper.writeValueAsString(
        ResponseDto.ERROR(errorMessage, badRequest));
    response.getWriter().write(responseBodyWithJson);
    response.setStatus(badRequest.value());
  }

  private void setResponseJsonType(HttpServletResponse response) {
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");
  }
}
