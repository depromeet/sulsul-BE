package com.depromeet.sulsul.oauth2.handler;

import static com.depromeet.sulsul.util.HttpResponseUtil.processWithErrorResponseDto;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.depromeet.sulsul.oauth2.filter.JwtAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {

    Integer jwtParseResultWhenDenied = Integer.parseInt(
        response.getHeader(JwtAuthenticationFilter.JWT_PARSE_RESULT));

    if (jwtParseResultWhenDenied == BAD_REQUEST.value()) {
      processWithErrorResponseDto("[ERROR] jwt가 없거나 잘못 되었습니다.", BAD_REQUEST, response);
      return;
    }

    processWithErrorResponseDto("[ERROR] jwt가 만료되었습니다.", UNAUTHORIZED, response);
  }
}
