package com.depromeet.sulsul.common.response.dto;

import com.depromeet.sulsul.common.error.dto.ErrorResponseDto;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseDto<T> implements Serializable {

  private static final long serialVersionUID = 2788791376175369829L;
  private boolean success;
  private T contents;
  private ErrorResponseDto error;

  private ResponseDto(T contents) {
    this.success = true;
    this.contents = contents;
  }

  private ResponseDto(ErrorResponseDto errorResponseDto) {
    this.contents = null;
    this.error = errorResponseDto;
  }

  public static <T> ResponseDto<T> from(T contents) {
    return new ResponseDto<T>(contents);
  }

  public static ResponseDto<?> ERROR(Throwable throwable, HttpStatus status) {
    return new ResponseDto(ErrorResponseDto.of(throwable, status));
  }

  public static ResponseDto<?> ERROR(String errorMessage, HttpStatus status) {
    return new ResponseDto(ErrorResponseDto.of(errorMessage, status));
  }

  public static ResponseDto<?> OK() {
    ResponseDto<Object> responseDto = new ResponseDto<>();
    responseDto.setSuccess(true);
    return responseDto;
  }
}
