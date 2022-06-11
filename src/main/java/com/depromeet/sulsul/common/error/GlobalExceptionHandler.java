package com.depromeet.sulsul.common.error;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.depromeet.sulsul.common.error.exception.BusinessException;
import com.depromeet.sulsul.common.error.exception.custom.AuthenticationEntryPointException;
import com.depromeet.sulsul.common.error.exception.custom.BeerNotFoundException;
import com.depromeet.sulsul.common.error.exception.custom.FlavorNotFoundException;
import com.depromeet.sulsul.common.error.exception.custom.MemberLevelNotFoundException;
import com.depromeet.sulsul.common.error.exception.custom.MemberNotFoundException;
import com.depromeet.sulsul.common.error.exception.custom.NotAllowAccessException;
import com.depromeet.sulsul.common.error.exception.custom.RecordNotFoundException;
import com.depromeet.sulsul.common.error.exception.custom.RequestSizeExceedException;
import com.depromeet.sulsul.common.error.exception.custom.UnAuthorizedException;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({RequestSizeExceedException.class, BeerNotFoundException.class,
      FlavorNotFoundException.class, MemberLevelNotFoundException.class,
      RecordNotFoundException.class, MemberNotFoundException.class,})
  protected ResponseDto<?> handleBadRequestException(final BusinessException e) {
    log.error("BusinessException : {}", e.toString());
    e.printStackTrace();
    return ResponseDto.ERROR(e, BAD_REQUEST);
  }

  @ExceptionHandler({AuthenticationEntryPointException.class, NotAllowAccessException.class,
      UnAuthorizedException.class})
  protected ResponseDto<?> handleAuthenticationException(final BusinessException e) {
    log.error("BusinessException : {}", e.toString());
    e.printStackTrace();
    return ResponseDto.ERROR(e, UNAUTHORIZED);
  }

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
