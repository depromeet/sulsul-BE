package com.depromeet.sulsul.oauth2.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.oauth2.filter.JwtAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {

    Integer jwtParseResultWhenDenied = Integer.parseInt(
        response.getHeader(JwtAuthenticationFilter.JWT_PARSE_RESULT));

    setResponseJsonType(response);

    if (jwtParseResultWhenDenied == BAD_REQUEST.value()) {
      processWithErrorResponseDto("[ERROR] jwt가 없거나 잘못 되었습니다.", BAD_REQUEST, response);
      return;
    }
    if (jwtParseResultWhenDenied == UNAUTHORIZED.value()) {
      processWithErrorResponseDto("[ERROR] jwt가 만료되었습니다.", UNAUTHORIZED, response);
      return;
    }

    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getLocalizedMessage());
  }

  private void processWithErrorResponseDto(String errorMessage, HttpStatus httpStatus,
      HttpServletResponse response) throws IOException {
    String responseBodyWithJson = objectMapper.writeValueAsString(
        ResponseDto.ERROR(errorMessage, httpStatus));
    response.getWriter().write(responseBodyWithJson);
    response.setStatus(httpStatus.value());
  }

  private void setResponseJsonType(HttpServletResponse response) {
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");
  }
}
