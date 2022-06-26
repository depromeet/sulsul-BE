package com.depromeet.sulsul.domain.member.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class NicknameRequestDto {
  @NotBlank
  @Size(min = 2, max = 15)
  private String nickname;
}
