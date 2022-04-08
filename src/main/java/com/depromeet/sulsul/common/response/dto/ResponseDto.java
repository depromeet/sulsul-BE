package com.depromeet.sulsul.common.response.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto<T> implements Serializable {

  private static final long serialVersionUID = 2788791376175369829L;
  private T data;

  public static <T> ResponseDto<T> of(T data) {
    return new ResponseDto<>(data);
  }
}
