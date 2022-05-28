package com.depromeet.sulsul.common.error;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.depromeet.sulsul.common.error.exception.BusinessException;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  protected ResponseDto<?> handleBusinessException(final BusinessException e) {
    log.error("BusinessException : {}", e.toString());
    e.printStackTrace();
    return ResponseDto.ERROR(e, INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  protected ResponseDto<?> handleException(final Exception e) {
    log.error("Exception : {}", e.toString());
    e.printStackTrace();
    return ResponseDto.ERROR(e, INTERNAL_SERVER_ERROR);
  }
}
