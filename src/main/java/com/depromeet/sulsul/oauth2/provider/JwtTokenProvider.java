package com.depromeet.sulsul.oauth2.provider;

import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.oauth2.CustomOAuth2User;
import com.depromeet.sulsul.oauth2.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtTokenProvider {

  @Value("${authentication.jwt.secretKey}")
  private String secretKey;

  @Value("${authentication.jwt.accessTokenExpirationSecond}")
  private Long accessTokenExpirationSecond;

  @Value("${authentication.jwt.refreshTokenExpirationSecond}")
  private Long refreshTokenExpirationSecond;

  @Value("${authentication.cookie.accessTokenCookieName}")
  private String accessTokenCookieName;

  public String createRefreshToken(Authentication authentication) {

    CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject(oAuth2User.getName())
        .setClaims(createClaims(oAuth2User.getMemberId()))
        .setIssuedAt(new Date())
        .setExpiration(parseExpiration(refreshTokenExpirationSecond))
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
        .compact();
  }

  public String createRefreshToken(Member member) {

    return Jwts.builder()
        .setSubject(member.getNickname())
        .setClaims(createClaims(member.getId()))
        .setIssuedAt(new Date())
        .setExpiration(parseExpiration(refreshTokenExpirationSecond))
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
        .compact();
  }

  public String createAccessToken(Authentication authentication) {

    CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject(oAuth2User.getName())
        .setClaims(createClaims(oAuth2User.getMemberId()))
        .setIssuedAt(new Date())
        .setExpiration(parseExpiration(accessTokenExpirationSecond))
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
        .compact();
  }

  public String createAccessToken(Member member) {

    return Jwts.builder()
        .setSubject(member.getNickname())
        .setClaims(createClaims(member.getId()))
        .setIssuedAt(new Date())
        .setExpiration(parseExpiration(accessTokenExpirationSecond))
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
        .compact();
  }

  public Claims createClaims(Long memberId) {
    return Jwts.claims().setSubject(String.valueOf(memberId));
  }

  private Date parseExpiration(Long second) {
    Date now = new Date();
    long duration = now.getTime() + second;
    return new Date(duration);
  }

  public String getJwtToken(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (accessTokenCookieName.equals(cookie.getName())) {
          String accessToken = cookie.getValue();
          if (accessToken == null) {
            return null;
          }
          return accessToken;
        }
      }
    }
    return null;
  }

  public boolean validateToken(String jwtToken) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(secretKey.getBytes())
          .build()
          .parseClaimsJws(jwtToken);
      return true;
    } catch (SignatureException | MalformedJwtException | ExpiredJwtException |
        UnsupportedJwtException | IllegalArgumentException e) {
      log.error(e.getMessage(), e);
    }
    return false;
  }

  public Claims getAllClaimsFromToken(String jwtToken) {
    Claims claims = null;
    try {
      return Jwts.parserBuilder()
          .setSigningKey(secretKey.getBytes())
          .build()
          .parseClaimsJws(jwtToken)
          .getBody();
    } catch (SecurityException | MalformedJwtException | ExpiredJwtException | IllegalArgumentException |
        UnsupportedJwtException e) {
    }
    return null;
  }

  public UsernamePasswordAuthenticationToken getAuthentication(String jwtToken) {

    Claims claims = getAllClaimsFromToken(jwtToken);
    Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("USER"));
    return new UsernamePasswordAuthenticationToken(new User(claims.getSubject(), authorities), "", authorities);
  }
}
