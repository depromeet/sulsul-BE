package com.depromeet.sulsul.util;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

public class HttpResponseUtil {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static void processWithErrorResponseDto(String errorMessage, HttpStatus httpStatus,
      HttpServletResponse response) throws IOException {
    String responseBodyWithJson = objectMapper.writeValueAsString(
        ResponseDto.ERROR(errorMessage, httpStatus));
    setResponseJsonType(response);
    response.getWriter().write(responseBodyWithJson);
    response.setStatus(httpStatus.value());
  }

  public static void setResponseJsonType(HttpServletResponse response) {
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");
  }
}
