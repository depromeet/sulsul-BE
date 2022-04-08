package com.depromeet.sulsul.common.error.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto implements Serializable {

  private static final long serialVersionUID = -2511775898366527404L;
  private String message;

  public static ErrorDto of(final String message) {
    return new ErrorDto(message);
  }
}
