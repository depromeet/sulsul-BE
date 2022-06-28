package com.depromeet.sulsul.oauth2.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {

    int statusWhenDenied = response.getStatus();
    if (statusWhenDenied == BAD_REQUEST.value()) {
      processWithErrorResponseDto("[ERROR] jwt가 없거나 잘못 되었습니다.", BAD_REQUEST, response);
      return;
    }
    if (statusWhenDenied == UNAUTHORIZED.value()) {
      processWithErrorResponseDto("[ERROR] jwt가 만료되었습니다.", UNAUTHORIZED, response);
      return;
    }

    response.sendError(HttpServletResponse.SC_FORBIDDEN,
        accessDeniedException.getLocalizedMessage());
  }

  private void processWithErrorResponseDto(String errorMessage, HttpStatus httpStatus,
      HttpServletResponse response) throws IOException {
    String responseBodyWithJson = objectMapper.writeValueAsString(
        ResponseDto.ERROR(errorMessage, httpStatus));
    response.getWriter().write(responseBodyWithJson);
    response.setStatus(httpStatus.value());
  }
}
