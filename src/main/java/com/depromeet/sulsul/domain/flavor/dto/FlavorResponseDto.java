package com.depromeet.sulsul.domain.flavor.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlavorResponseDto {

  private String content;
  private Long count;

  @QueryProjection
  public FlavorResponseDto(String content, Long count) {
    this.content = content;
    this.count = count;
  }
}
