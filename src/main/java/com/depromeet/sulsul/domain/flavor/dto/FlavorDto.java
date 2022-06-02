package com.depromeet.sulsul.domain.flavor.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FlavorDto {

  private Long id;
  private String content;

  @Builder
  public FlavorDto(Long id, String content) {
    this.id = id;
    this.content = content;
  }
}