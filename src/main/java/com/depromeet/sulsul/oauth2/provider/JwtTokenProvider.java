package com.depromeet.sulsul.oauth2.provider;

import com.depromeet.sulsul.oauth2.CustomOAuth2User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

  @Value("${authentication.jwt.secretKey}")
  private String secretKey;

  @Value("${authentication.jwt.accessTokenExpirationSecond}")
  private Long accessTokenExpirationSecond;

  @Value("${authentication.jwt.refreshTokenExpirationSecond}")
  private Long refreshTokenExpirationSecond;

  @Value("${authentication.cookie.accessTokenCookieName}")
  private String accessTokenCookieName;

  public String createAccessToken(Authentication authentication) {

    CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

    return Jwts.builder()
        .setClaims(createClaims(oAuth2User))
        .setSubject(oAuth2User.getEmail())
        .setIssuedAt(new Date())
        .setExpiration(parseExpiration(accessTokenExpirationSecond))
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
        .compact();
  }

  public Claims createClaims(CustomOAuth2User oAuth2User) {

    Claims claims = Jwts.claims().setSubject(oAuth2User.getEmail());
    claims.put("memberId", oAuth2User.getMemberId());
    claims.put("role", oAuth2User.getAuthorities());

    return claims;
  }

  public String createRefreshToken(Authentication authentication) {
    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject(oAuth2User.getName())
        .setIssuedAt(new Date())
        .setExpiration(parseExpiration(refreshTokenExpirationSecond))
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
        .compact();
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
             UnsupportedJwtException | IllegalArgumentException ex) {
      ex.printStackTrace();
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
    Collection<? extends GrantedAuthority> authorities =
        Arrays.stream(new String[]{claims.get("role").toString()})
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

    return new UsernamePasswordAuthenticationToken(claims.get("memberId"), "", authorities);
  }
}
