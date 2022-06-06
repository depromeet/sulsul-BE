package com.depromeet.sulsul.oauth2.filter;

import com.depromeet.sulsul.common.error.dto.ErrorType;
import com.depromeet.sulsul.oauth2.provider.JwtTokenProvider;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@WebFilter(urlPatterns = "/api/*")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    String jwtToken = jwtTokenProvider.getJwtToken(request);

    if (!jwtTokenProvider.validateToken(jwtToken)) {
      ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, ErrorType.UNAUTHORIZED.getMessage());
      return;
    }
    UsernamePasswordAuthenticationToken authentication = jwtTokenProvider.getAuthentication(jwtToken);
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    filterChain.doFilter(request, response);
  }
}
