package com.depromeet.sulsul.oauth2.filter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.depromeet.sulsul.oauth2.provider.JwtTokenProvider;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  public static final String JWT_PARSE_RESULT = "jwt-parse-result";
  private final JwtTokenProvider jwtTokenProvider;

  @Value("${client.url}")
  private String urlOfEnv;

  @Value("${authentication.cookie.accessTokenCookieName}")
  private String accessTokenCookieName;

  @Value("${authentication.cookie.refreshTokenCookieName}")
  private String refreshTokenCookieName;

  @Value("${authentication.jwt.secretKey}")
  private String secretKey;

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
      response.setIntHeader(JWT_PARSE_RESULT, BAD_REQUEST.value());
      filterChain.doFilter(request, response);
      return;
    } catch (ExpiredJwtException e) {
      response.setIntHeader(JWT_PARSE_RESULT, UNAUTHORIZED.value());
      filterChain.doFilter(request, response);
      return;
    }

    UsernamePasswordAuthenticationToken authentication = jwtTokenProvider.getAuthentication(
        jwtToken);
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    filterChain.doFilter(request, response);
  }
}
